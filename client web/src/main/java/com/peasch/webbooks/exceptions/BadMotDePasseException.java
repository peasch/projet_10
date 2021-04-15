package com.peasch.webbooks.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadMotDePasseException extends Exception {
    public BadMotDePasseException(String mot_de_passe_incorrect) {
        super(mot_de_passe_incorrect);
    }
}
