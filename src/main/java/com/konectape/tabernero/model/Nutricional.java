package com.konectape.tabernero.model;

import com.konectape.tabernero.dto.request.DatosNutricional;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Nutricional {
    private String energetic;
    private String totalfat;
    private String saturatedfat;
    private String carbohydrate;
    private String protein;
    private String sodium;
    private String amount;

    public Nutricional(DatosNutricional datosNutricional) {
        this.energetic = datosNutricional.energetic();
        this.totalfat = datosNutricional.totalfat();
        this.saturatedfat = datosNutricional.saturatedfat();
        this.carbohydrate = datosNutricional.carbohydrate();
        this.protein = datosNutricional.protein();
        this.sodium = datosNutricional.sodium();
        this.amount = "Por 100 ML";
    }
}
