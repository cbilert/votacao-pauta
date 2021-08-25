package br.com.votacaopauta.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CPFGeradoDTO {

    @JsonProperty("status")
    private boolean status;

    @JsonProperty("number")
    private String number;

    @JsonProperty("number_formatted")
    private String numberFormatted;

    @JsonProperty("message")
    private String message;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumberFormatted() {
        return numberFormatted;
    }

    public void setNumberFormatted(String numberFormatted) {
        this.numberFormatted = numberFormatted;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
