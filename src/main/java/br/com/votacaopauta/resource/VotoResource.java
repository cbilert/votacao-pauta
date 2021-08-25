package br.com.votacaopauta.resource;

import br.com.votacaopauta.service.VotoService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/votar")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VotoResource {

    @Inject
    VotoService votoService;

    @POST
    @Path("/{idPauta}/{cpfAssociado}/{votoValue}")
    @Transactional
    public Response votar(@PathParam("idPauta") Long idPauta, @PathParam("cpfAssociado") String cpfAssociado, @PathParam("votoValue") String votoValue) {
        return votoService.computarVoto(idPauta,cpfAssociado,votoValue);
    }

}
