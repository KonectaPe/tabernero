package com.konectape.tabernero.dto.response;

import com.konectape.tabernero.model.Nutricional;

public record DatosListadoNutricional(
        String energetic,
        String totalfat,
        String saturatedfat,
        String carbohydrate,
        String protein,
        String sodium,
        String amount
) {
    public DatosListadoNutricional(Nutricional nutricional) {
        this(nutricional.getEnergetic(), nutricional.getTotalfat(), nutricional.getSaturatedfat(), nutricional.getCarbohydrate(), nutricional.getProtein(), nutricional.getSodium(), nutricional.getAmount());
    }
}
