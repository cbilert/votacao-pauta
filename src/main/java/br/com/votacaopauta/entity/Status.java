package br.com.votacaopauta.entity;

import java.io.Serializable;

public class Status implements Serializable {
    String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
