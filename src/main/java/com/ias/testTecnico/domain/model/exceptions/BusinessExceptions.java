package com.ias.testTecnico.domain.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BusinessExceptions extends RuntimeException{

    public BusinessExceptions(String message) {
        super(message);
    }

}
