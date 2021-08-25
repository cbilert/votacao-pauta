package br.com.votacaopauta.resource;

import br.com.votacaopauta.entity.Voto;
import br.com.votacaopauta.service.ApiCPFGeneratorConsumer;
import br.com.votacaopauta.service.VotoService;
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
import java.net.URI;

@Path("/votar")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VotoResource {

    @Inject
    VotoService votoService;

    @Inject
    ApiCPFGeneratorConsumer apiCPFGeneratorConsumer;

    @POST
    @Path("/{idPauta}/{cpfAssociado}/{votoValue}")
    @Transactional
    @Tag(name="votar")
    @Operation(summary = "Retorna o status da votação de um associado pelo cpf")
    @APIResponse(responseCode = "200",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(
                            implementation = Voto.class,
                            type = SchemaType.STRING)))
    public Response votar(@PathParam("idPauta") Long idPauta, @PathParam("cpfAssociado") String cpfAssociado, @PathParam("votoValue") String votoValue) {
        return votoService.computarVoto(idPauta,cpfAssociado,votoValue);
    }

    @POST
    @Path("/{idPauta}/cpfAleatorio/{votoValue}")
    @Transactional
    @Tag(name="votar")
    @Operation(summary = "Retorna o status da votação de um associado aleatório")
    @APIResponse(responseCode = "200",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(
                            implementation = Voto.class,
                            type = SchemaType.STRING)))
    public Response votar(@PathParam("idPauta") Long idPauta, @PathParam("votoValue") String votoValue) {
        String cpfAssociado = apiCPFGeneratorConsumer.getCPF().getNumber();
        return votoService.computarVoto(idPauta,cpfAssociado,votoValue);
    }

}
