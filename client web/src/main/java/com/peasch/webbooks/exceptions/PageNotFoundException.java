package com.peasch.webbooks.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PageNotFoundException extends Exception {
    public PageNotFoundException(String page_non_trouvée) {
        super(page_non_trouvée);
    }
}
