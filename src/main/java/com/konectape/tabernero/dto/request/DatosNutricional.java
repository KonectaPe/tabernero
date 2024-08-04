package com.konectape.tabernero.dto.request;

import jakarta.validation.constraints.NotBlank;

public record DatosNutricional(
        @NotBlank
        String energetic,
        @NotBlank
        String totalfat,
        @NotBlank
        String saturatedfat,
        @NotBlank
        String carbohydrate,
        @NotBlank
        String protein,
        @NotBlank
        String sodium
) {
}
