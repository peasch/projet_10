package com.peasch.webbooks.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class BookNotFoundException extends Exception {
    public BookNotFoundException(String non_autorise) {
        super(non_autorise);
    }
}
