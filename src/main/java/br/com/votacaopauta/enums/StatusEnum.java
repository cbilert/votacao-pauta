package br.com.votacaopauta.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum StatusEnum {
    ABLE_TO_VOTE("ABLE_TO_VOTE"),
    UNABLE_TO_VOTE("UNABLE_TO_VOTE"),
    ERROR_ON_VOTE("ERROR_ON_VOTE"),
    VOTE_COMPUTED("VOTE_COMPUTED"),
    ASSOCIATED_HAS_VOTED("ASSOCIATED_HAS_VOTED"),
    ASSOCIATED_EXISTS("ASSOCIATED_EXISTS");

    private String status;

    StatusEnum(String status) {
        this.status = status;
    }

    @JsonValue
    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return status;
    }
}
