package br.com.votacaopauta.entity;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class SessaoVotacao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "pauta")
    private Pauta pauta;

    @Column(name = "abertura")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime abertura;

    @Column(name = "encerramento")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime encerramento;

    @Column(name = "votosSim")
    private Long votosSim;

    @Column(name = "votosNao")
    private Long votosNao;

    public Pauta getPauta() {
        return pauta;
    }

    public void setPauta(Pauta pauta) {
        this.pauta = pauta;
    }

    public LocalDateTime getAbertura() {
        return abertura;
    }

    public void setAbertura(LocalDateTime abertura) {
        this.abertura = abertura;
    }

    public LocalDateTime getEncerramento() {
        return encerramento;
    }

    public void setEncerramento(LocalDateTime encerramento) {
        this.encerramento = encerramento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVotosSim() {
        if (votosSim == null) votosSim = 0L;
        return votosSim;
    }

    public void setVotosSim(Long votosSim) {
        this.votosSim = votosSim;
    }

    public Long getVotosNao() {
        if (votosNao ==null) votosNao = 0L;
        return votosNao;
    }

    public void setVotosNao(Long votosNao) {
        this.votosNao = votosNao;
    }

    public Long getTotalVotos() {
        return getVotosSim() + getVotosNao();
    }
}
