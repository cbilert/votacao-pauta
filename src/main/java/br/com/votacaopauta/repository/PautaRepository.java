package br.com.votacaopauta.repository;

import br.com.votacaopauta.entity.Pauta;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class PautaRepository  implements PanacheRepository<Pauta> {

    public Pauta findByDescricao(String descricao) {
        return find("descricao",descricao).firstResult();
    }

    public List<Pauta> listByDescricao() {
        return listAll(Sort.ascending("descricao"));
    }
}
