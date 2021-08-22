package br.com.votacaopauta.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class SessaoVotacao extends PanacheEntity {

    @OneToOne
    @JoinColumn(name = "pauta")
    private Pauta pauta;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "abertura")
    private Date abertura;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "encerramento")
    private Date encerramento;

    public Pauta getPauta() {
        return pauta;
    }

    public void setPauta(Pauta pauta) {
        this.pauta = pauta;
    }

    public Date getAbertura() {
        return abertura;
    }

    public void setAbertura(Date abertura) {
        this.abertura = abertura;
    }

    public Date getEncerramento() {
        return encerramento;
    }

    public void setEncerramento(Date encerramento) {
        this.encerramento = encerramento;
    }
}
