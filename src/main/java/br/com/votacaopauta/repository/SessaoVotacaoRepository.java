package br.com.votacaopauta.repository;

import br.com.votacaopauta.entity.Pauta;
import br.com.votacaopauta.entity.SessaoVotacao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class SessaoVotacaoRepository implements PanacheRepository<SessaoVotacao> {

}
