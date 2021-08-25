package br.com.votacaopauta.service;

import br.com.votacaopauta.dto.CPFGeradoDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.enterprise.context.RequestScoped;
import java.io.IOException;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RequestScoped
public class ApiCPFGeneratorConsumer {

    public CPFGeradoDTO getCPF() {
        try {
            return new ObjectMapper().readValue(getCPFFromAPI(), CPFGeradoDTO.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getCPFFromAPI() {
        String retorno = "";
        try {
            HttpClient httpClient = HttpClient.newBuilder()
                    .version(HttpClient.Version.HTTP_1_1)
                    .proxy(ProxySelector.getDefault())
                    .build();

            HttpRequest mainRequest = HttpRequest.newBuilder().GET()
                    .uri(URI.create("https://gerador.app/api/cpf/generate"))
                    .header("Accept", "application/json")
                    .header("Authorization", "Bearer eg2JfpdvKPRvBSsfUeLvnq6m7XLW2FkyBUIYzpWy")
                    .build();
            HttpResponse<String> response = httpClient.send(mainRequest, HttpResponse.BodyHandlers.ofString());
            retorno = response.body();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return retorno;
    }
}
