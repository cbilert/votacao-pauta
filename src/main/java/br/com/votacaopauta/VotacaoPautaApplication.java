package br.com.votacaopauta;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.servers.Server;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.ws.rs.core.Application;

@OpenAPIDefinition(
        tags = {
                @Tag(name = "geraCPF", description = "Consulta API do https://gerador.app para gerar um CPF aleatóriamente."),
                @Tag(name = "pauta", description = "Cria uma pauta e ja abre uma sessão de votação por um tempo determinado em milissegundos ou 1 min por default."),
                @Tag(name = "sessaoVotacao", description = "Retorna as sessões criadas"),
                @Tag(name = "votar", description = "Permite a votação do associado a uma pauta aberta. Para fins de teste e pra nao ficar inventando CPFs, pode usar o cpfAleatorio para a votação."),
        },
        info = @Info(
                title = "API para votação de pautas pelos associados",
                version = "1.0.0",
                contact = @Contact(
                        name = "Fale com o Dev",
                        url = "https://github.com/cbilert")),
        servers = {
                @Server(url = "http://localhost:8080"),
                @Server(url = "https://votacao-pauta.herokuapp.com/"),
        })
public class VotacaoPautaApplication extends Application {
}
