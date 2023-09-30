package com.example.Tasks;

import org.springframework.http.HttpStatus;

public class SuccessDTO {
    //trimitem status de OK pt cand mesajul se executa cu succes
    private final Integer statusCode= HttpStatus.OK.value();

    private final String statusMessage= HttpStatus.OK.getReasonPhrase();

    public Integer getStatusCode() {
        return statusCode;
    }

}
