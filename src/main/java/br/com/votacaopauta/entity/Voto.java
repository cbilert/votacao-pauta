package br.com.votacaopauta.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
@Entity
public class Voto extends PanacheEntity {

    @ManyToOne
    @JoinColumn(name = "pauta")
    private Pauta pauta;

    @Column(name = "voto")
    private boolean voto;

    public Pauta getPauta() {
        return pauta;
    }

    public void setPauta(Pauta pauta) {
        this.pauta = pauta;
    }

    public boolean isVoto() {
        return voto;
    }

    public void setVoto(boolean voto) {
        this.voto = voto;
    }
}
