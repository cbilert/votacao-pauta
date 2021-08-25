package br.com.votacaopauta.service;

import br.com.votacaopauta.entity.*;
import br.com.votacaopauta.enums.StatusEnum;
import br.com.votacaopauta.repository.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

@ApplicationScoped
public class VotoService {

    @RestClient
    AssociadoService associadoService;

    @Inject
    AssociadoRepository associadoRepository;

    @Inject
    VotoRepository votoRepository;

    @Inject
    PautaRepository pautaRepository;

    @Inject
    AssociadoVotacaoRepository associadoVotacaoRepository;

    @Inject
    SessaoVotacaoRepository sessaoVotacaoRepository;

    private StatusEnum verificaAssociado(String cpfAssociado){
        String statusStr = associadoService.getStatusCPF(cpfAssociado);
        Status status;
        try {
            status = new ObjectMapper().readValue(statusStr, Status.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return StatusEnum.ERROR_ON_VOTE;
        }
        return StatusEnum.valueOf(status.getStatus());
    }

    private Associado cadastraAssociado(String cpfAssociado) {
        Associado associado;
        associado = new Associado();
        associado.setCpf(cpfAssociado);
        associadoRepository.persist(associado);
        return associado;
    }

    public Response computarVoto(Long idPauta, String cpfAssociado, String votoValue) {
        StatusEnum statusVerificacao = verificaAssociado(cpfAssociado);
        if (StatusEnum.ABLE_TO_VOTE.getStatus().equals(statusVerificacao.getStatus())) {
            Associado associado = associadoRepository.find("cpf", cpfAssociado).firstResult();

            if (associado == null) {
                cadastraAssociado(cpfAssociado);
            }

            Pauta pauta = pautaRepository.findById(idPauta);
            Boolean associadoNaoVotouNessaPauta = associadoVotacaoRepository.find("associado.cpf=?1 and pauta.id=?2",cpfAssociado,idPauta).count() == 0;
            Boolean sessaoAindaEstaAberta = sessaoVotacaoRepository.find("pauta.id=?1 and encerramento = null", idPauta).count() == 1;

            if (associadoNaoVotouNessaPauta && sessaoAindaEstaAberta) {
                AssociadoVotacoes associadoVotacoes = new AssociadoVotacoes();
                associadoVotacoes.setAssociado(associado);
                associadoVotacoes.setPauta(pauta);
                associadoVotacaoRepository.persist(associadoVotacoes);

                Voto voto = new Voto();
                voto.setVoto(strVotoToBoolean(votoValue));
                voto.setPauta(pauta);
                votoRepository.persist(voto);

                return Response.ok().entity(StatusEnum.VOTE_COMPUTED).build();
            } else {
                return Response.ok().entity(StatusEnum.ASSOCIATED_HAS_VOTED).build();
            }
        }
        return Response.serverError().entity(statusVerificacao).build();
    }

    private boolean strVotoToBoolean(String votoValue) {
        return "S".equals(votoValue) || "SIM".equalsIgnoreCase(votoValue);
    }
}
