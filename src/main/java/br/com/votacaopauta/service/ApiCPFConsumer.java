package br.com.votacaopauta.service;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@RegisterRestClient
public interface ApiCPFConsumer {

    @GET
    @Path("/{cpf}")
    String getStatusCPF(@PathParam("cpf") String cpf);
}
