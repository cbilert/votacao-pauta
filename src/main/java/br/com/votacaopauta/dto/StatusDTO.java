package br.com.votacaopauta.dto;

import java.io.Serializable;

public class StatusDTO implements Serializable {
    String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
