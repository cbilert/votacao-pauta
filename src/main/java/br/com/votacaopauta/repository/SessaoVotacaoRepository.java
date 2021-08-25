package br.com.votacaopauta.repository;

import br.com.votacaopauta.entity.SessaoVotacao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SessaoVotacaoRepository implements PanacheRepository<SessaoVotacao> {

}
