package br.com.votacaopauta.service;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 * @author Cristian
 * @apiNote usa os recursos do quarkus para consumo de API conforme configuração no application.properties
* */
@RegisterRestClient
public interface ApiCPFConsumer {

    /**
     *
     * @param cpf recebe um cpf para validação na api https://user-info.herokuapp.com/users/{cpf}
     * @return retorna um json com o status do cpf
     */
    @GET
    @Path("/{cpf}")
    String getStatusCPF(@PathParam("cpf") String cpf);
}
