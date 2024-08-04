package com.konectape.tabernero.model;

import com.konectape.tabernero.dto.request.DatosIngrediente;
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
public class Ingrediente {
    private String characteristics;

    public Ingrediente(DatosIngrediente datosIngrediente) {
        this.characteristics = datosIngrediente.characteristics();
    }
}
