package com.konectape.tabernero.model;

import com.konectape.tabernero.dto.request.DatosBebida;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "bebidas")
@Entity(name = "bebida")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Bebida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String image;
    private String title;
    private LocalDateTime creation;
    @Embedded
    private Ingrediente ingrediente;
    @Embedded
    private Nutricional nutricional;

    public Bebida(DatosBebida datosBebida) {
        this.image = datosBebida.image();
        this.title = datosBebida.title();
        this.creation = LocalDateTime.now();
        this.ingrediente = new Ingrediente(datosBebida.datosIngrediente());
        this.nutricional = new Nutricional(datosBebida.datosNutricional());
    }
}
