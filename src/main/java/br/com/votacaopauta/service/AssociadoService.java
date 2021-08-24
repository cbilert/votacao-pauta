package br.com.votacaopauta.service;

import br.com.votacaopauta.entity.Associado;
import br.com.votacaopauta.repository.AssociadoRepository;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

@RegisterRestClient
public interface AssociadoService {

    @GET
    @Path("/{cpf}")
    String getStatusCPF(@PathParam("cpf") String cpf);
}
