package br.com.votacaopauta.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
@Entity
public class Associado extends PanacheEntity {

    @Column(name = "cpf")
    private Long cpf;

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }
}
