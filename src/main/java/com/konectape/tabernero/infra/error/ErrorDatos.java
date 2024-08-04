package com.konectape.tabernero.infra.error;

import org.springframework.validation.FieldError;

public record ErrorDatos(
        String campo,
        String error
) {
    public ErrorDatos(FieldError error) {
        this(error.getField(), error.getDefaultMessage());
    }
}
