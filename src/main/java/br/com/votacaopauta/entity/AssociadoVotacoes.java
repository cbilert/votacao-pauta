package br.com.votacaopauta.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
@Entity
public class AssociadoVotacoes extends PanacheEntity {

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
}
