package com.konectape.tabernero.dto.response;

import com.konectape.tabernero.model.Bebida;

import java.time.LocalDateTime;

public record DatosListadoBebida(
        Long id,
        String image,
        String title,
        LocalDateTime creation
) {
    public DatosListadoBebida(Bebida bebida) {
        this(
                bebida.getId(),
                bebida.getImage(),
                bebida.getTitle(),
                bebida.getCreation()
        );
    }
}
