package com.konectape.tabernero.dto.request;

import jakarta.validation.constraints.NotBlank;

public record DatosIngrediente(
        @NotBlank
        String characteristics
) {
}
