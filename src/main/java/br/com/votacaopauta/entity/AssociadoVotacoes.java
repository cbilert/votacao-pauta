package br.com.votacaopauta.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class AssociadoVotacoes implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "associado")
    private Associado associado;

    @ManyToOne
    @JoinColumn(name = "pauta")
    private Pauta pauta;

    public Associado getAssociado() {
        return associado;
    }

    public void setAssociado(Associado associado) {
        this.associado = associado;
    }

    public Pauta getPauta() {
        return pauta;
    }

    public void setPauta(Pauta pauta) {
        this.pauta = pauta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
