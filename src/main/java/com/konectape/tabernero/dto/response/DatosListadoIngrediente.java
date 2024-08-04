package com.konectape.tabernero.dto.response;

import com.konectape.tabernero.model.Bebida;
import com.konectape.tabernero.model.Ingrediente;

public record DatosListadoIngrediente(
        String characteristics
) {
    public DatosListadoIngrediente(Ingrediente ingrediente) {
        this(ingrediente.getCharacteristics());
    }
}
