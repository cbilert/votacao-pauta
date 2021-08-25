package br.com.votacaopauta.resource;

import br.com.votacaopauta.entity.Pauta;
import br.com.votacaopauta.entity.Voto;
import br.com.votacaopauta.repository.PautaRepository;
import br.com.votacaopauta.service.SessaoVotacaoService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
/**
 * @author Cristian
 * */
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
    @Tag(name="pauta")
    @Operation(summary = "Retorna todas as pautas cadastradas")
    @APIResponse(responseCode = "200",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(
                            implementation = Voto.class,
                            type = SchemaType.ARRAY)))
    public List<Pauta> list() {
        return pautaRepository.listAll();
    }

    @GET
    @Path("/{id}")
    @Tag(name="pauta")
    @Operation(summary = "Retorna a pauta cadastrada por id.")
    @APIResponse(responseCode = "200",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(
                            implementation = Voto.class,
                            type = SchemaType.OBJECT)))
    public Pauta get(@PathParam("id") Long id) {
        return pautaRepository.findById(id);
    }

    @POST
    @Path("/{tempoSessao}")
    @Transactional
    @Tag(name="pauta")
    @Operation(summary = "Cria uma pauta e abre uma sessão de votação por um tempo especificado em milissegundos.")
    @APIResponse(responseCode = "200",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(
                            implementation = Voto.class,
                            type = SchemaType.DEFAULT)))
    public Response create(Pauta pauta, Long tempoSessao) {
        pautaRepository.persist(pauta);
        sessaoVotacaoService.init(pauta,tempoSessao);
        return Response.ok().entity(get(pauta.getId())).build();
    }
    @POST
    @Transactional
    @Tag(name="pauta")
    @Operation(summary = "Cria uma pauta e abre uma sessão de votação por um tempo de 1 minuto.")
    @APIResponse(responseCode = "200",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(
                            implementation = Voto.class,
                            type = SchemaType.DEFAULT)))
    public Response createSemTempo(Pauta pauta) {
        pautaRepository.persist(pauta);
        sessaoVotacaoService.init(pauta,null);
        return Response.ok().entity(get(pauta.getId())).build();
    }
}
