package com.konectape.tabernero.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosBebida(
        @NotBlank
        String image,
        @NotBlank
        String title,
        @NotNull
        @Valid
        DatosIngrediente datosIngrediente,
        @NotNull
        @Valid
        DatosNutricional datosNutricional
) {
}
