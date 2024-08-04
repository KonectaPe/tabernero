package com.konectape.tabernero.dto.response;

import com.konectape.tabernero.model.Bebida;

import java.time.LocalDateTime;

public record DatosDetalleBebida(
        Long id,
        String image,
        String title,
        LocalDateTime creation,
        DatosListadoIngrediente datosListadoIngrediente,
        DatosListadoNutricional datosListadoNutricional
) {

    public DatosDetalleBebida(Bebida bebida) {
        this(
                bebida.getId(),
                bebida.getImage(),
                bebida.getTitle(),
                bebida.getCreation(),
                new DatosListadoIngrediente(bebida.getIngrediente()),
                new DatosListadoNutricional(bebida.getNutricional())
        );
    }
}
