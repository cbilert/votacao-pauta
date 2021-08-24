package br.com.votacaopauta.repository;

import br.com.votacaopauta.entity.Associado;
import br.com.votacaopauta.entity.AssociadoVotacoes;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AssociadoVotacaoRepository implements PanacheRepository<AssociadoVotacoes> {

}
