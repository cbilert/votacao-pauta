package br.com.votacaopauta.resource;

import br.com.votacaopauta.entity.SessaoVotacao;
import br.com.votacaopauta.entity.Voto;
import br.com.votacaopauta.repository.SessaoVotacaoRepository;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
/**
 * @author Cristian
 * */
@Path("/sessaoVotacao")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SessaoVotacaoResource {

    @Inject
    SessaoVotacaoRepository sessaoVotacaoRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Tag(name="sessaoVotacao")
    @Operation(summary = "Retorna todas as sessões de votação")
    @APIResponse(responseCode = "200",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(
                            implementation = Voto.class,
                            type = SchemaType.STRING)))
    public List<SessaoVotacao> list() {
        return sessaoVotacaoRepository.listAll();
    }

    @GET
    @Path("/{id}")
    @Tag(name="sessaoVotacao")
    @Operation(summary = "Retorna a sessão de votação por id")
    @APIResponse(responseCode = "200",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(
                            implementation = Voto.class,
                            type = SchemaType.STRING)))
    public SessaoVotacao get(@PathParam("id") Long id) {
        return sessaoVotacaoRepository.findById(id);
    }

}
