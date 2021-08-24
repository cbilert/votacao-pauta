package br.com.votacaopauta.resource;

import br.com.votacaopauta.entity.Pauta;
import br.com.votacaopauta.repository.PautaRepository;
import br.com.votacaopauta.service.SessaoVotacaoService;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/pauta")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PautaResource {

    @Inject
    PautaRepository pautaRepository;

    @Inject
    SessaoVotacaoService sessaoVotacaoService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pauta> list() {
        return pautaRepository.listAll();
    }

    @GET
    @Path("/{id}")
    public Pauta get(@PathParam("id") Long id) {
        return pautaRepository.findById(id);
    }

    @POST
    @Path("/{tempoSessao}")
    @Transactional
    public Response create(Pauta pauta, Long tempoSessao) {
        pautaRepository.persist(pauta);
        sessaoVotacaoService.init(pauta,tempoSessao);
        return Response.created(URI.create("/pauta/"+pauta.getId())).build();
    }

}
