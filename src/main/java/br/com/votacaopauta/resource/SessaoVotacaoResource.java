package br.com.votacaopauta.resource;

import br.com.votacaopauta.entity.Pauta;
import br.com.votacaopauta.entity.SessaoVotacao;
import br.com.votacaopauta.repository.PautaRepository;
import br.com.votacaopauta.repository.SessaoVotacaoRepository;
import br.com.votacaopauta.service.SessaoVotacaoService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/sessaoVotacao")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SessaoVotacaoResource {

    @Inject
    SessaoVotacaoRepository sessaoVotacaoRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<SessaoVotacao> list() {
        return sessaoVotacaoRepository.listAll();
    }

    @GET
    @Path("/{id}")
    public SessaoVotacao get(@PathParam("id") Long id) {
        return sessaoVotacaoRepository.findById(id);
    }

}
