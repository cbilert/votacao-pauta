package br.com.votacaopauta.resource;

import br.com.votacaopauta.entity.*;
import br.com.votacaopauta.repository.*;
import br.com.votacaopauta.service.AssociadoService;
import br.com.votacaopauta.service.SessaoVotacaoService;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.ProxySelector;
import java.net.URI;
import java.util.List;

@Path("/votar")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VotoResource {

    @Inject
    VotoRepository votoRepository;

    @RestClient
    AssociadoService associadoService;

    @Inject
    AssociadoRepository associadoRepository;

    @Inject
    PautaRepository pautaRepository;

    @Inject
    AssociadoVotacaoRepository associadoVotacaoRepository;

    @Inject
    SessaoVotacaoRepository sessaoVotacaoRepository;

    @POST
    @Path("/{idPauta}/{cpfAssociado}/{votoValue}")
    @Transactional
    public Response votar(@PathParam("idPauta") Long idPauta, @PathParam("cpfAssociado") String cpfAssociado, @PathParam("votoValue") String votoValue) {
        Associado associado = verificaAssociado(cpfAssociado);
        if(associado != null) {
            Pauta pauta = pautaRepository.findById(idPauta);

            Boolean associadoNaoVotouNessaPauta = associadoVotacaoRepository.find("associado.cpf=?1 and pauta.id=?2",cpfAssociado,idPauta).count() == 0;
            Boolean sessaoAindaEstaAberta = sessaoVotacaoRepository.find("pauta.id=?1 and encerramento is null").count() == 1;
            if (associadoNaoVotouNessaPauta && sessaoAindaEstaAberta) {
                AssociadoVotacoes associadoVotacoes = new AssociadoVotacoes();
                associadoVotacoes.setAssociado(associado);
                associadoVotacoes.setPauta(pauta);
                associadoVotacaoRepository.persist(associadoVotacoes);

                Voto voto1 = new Voto();
                voto1.setVoto(strVotoToBoolean(votoValue));
                voto1.setPauta(pauta);
                votoRepository.persist(voto1);
                return  Response.ok().build();
            }
        }
        return Response.ok().build();
    }

    private boolean strVotoToBoolean(String votoValue) {
        return "S".equals(votoValue) || "SIM".equalsIgnoreCase(votoValue);
    }

    public Associado verificaAssociado(String cpfAssociado) {
        String status = associadoService.getStatusCPF(cpfAssociado);
        Associado associado = associadoRepository.list("cpf",cpfAssociado).get(0);
        if(associado != null) {
            return associado;
        } else {
            Associado associado1 = new Associado();
            associado1.setCpf(cpfAssociado);
            associadoRepository.persist(associado1);
            return associado1;
        }
    }

}
