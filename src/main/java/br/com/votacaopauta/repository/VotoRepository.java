package br.com.votacaopauta.repository;

import br.com.votacaopauta.entity.SessaoVotacao;
import br.com.votacaopauta.entity.Voto;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class VotoRepository implements PanacheRepository<Voto> {

}
