package br.com.votacaopauta.repository;

import br.com.votacaopauta.entity.Associado;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AssociadoRepository implements PanacheRepository<Associado> {

}
