package br.com.votacaopauta.repository;

import br.com.votacaopauta.entity.Voto;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class VotoRepository implements PanacheRepository<Voto> {

    public Long countVotosPositivos(Long idPauta) {
        return count("voto = ?1 and pauta.id = ?2",true, idPauta);
    }

    public Long countVotosNegativos(Long idPauta) {
        return count("voto = ?1 and pauta.id = ?2",false, idPauta);
    }
}
