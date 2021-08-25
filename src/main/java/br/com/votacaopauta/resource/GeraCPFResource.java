package br.com.votacaopauta.resource;

import br.com.votacaopauta.dto.CPFGeradoDTO;
import br.com.votacaopauta.entity.Voto;
import br.com.votacaopauta.service.ApiCPFGeneratorConsumer;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
/**
 * @author Cristian
 * */
@Path("/geraCPF")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GeraCPFResource {

    @Inject
    ApiCPFGeneratorConsumer apiCPFGeneratorConsumer;

    @GET
    @Transactional
    @Tag(name="geraCPF")
    @Operation(summary = "Retorna um CPF aleatório gerado pela API https://gerador.app")
    @APIResponse(responseCode = "200",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(
                            implementation = Voto.class,
                            type = SchemaType.ARRAY)))
    public CPFGeradoDTO gerar() {
        return apiCPFGeneratorConsumer.getCPF();
    }

}
