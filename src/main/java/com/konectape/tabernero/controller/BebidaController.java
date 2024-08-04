package com.konectape.tabernero.controller;

import com.konectape.tabernero.dto.request.DatosBebida;
import com.konectape.tabernero.dto.response.DatosDetalleBebida;
import com.konectape.tabernero.dto.response.DatosListadoBebida;
import com.konectape.tabernero.model.Bebida;
import com.konectape.tabernero.repository.BebidaRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/tabernero")
public class BebidaController {

    private final BebidaRepository bebidaRepository;

    public BebidaController(BebidaRepository bebidaRepository) {
        this.bebidaRepository = bebidaRepository;
    }

    @PostMapping
    public ResponseEntity<DatosBebida> addBebida(@RequestBody @Validated DatosBebida datosBebida, UriComponentsBuilder uriComponentsBuilder, HttpServletRequest request) {
        request.setAttribute("datosBebida", datosBebida);
        Bebida bebida = bebidaRepository.save(new Bebida(datosBebida));
        URI uri = uriComponentsBuilder.path("/tabernero/{id}").buildAndExpand(bebida.getId()).toUri();
        return ResponseEntity.created(uri).body(datosBebida);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoBebida>> listarBebidas(Pageable paginacion) {
        return ResponseEntity.ok(bebidaRepository.findAll(paginacion).map(DatosListadoBebida::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosDetalleBebida> detalleBebida(@PathVariable Long id) {
        Bebida bebida = bebidaRepository.getReferenceById(id);
        DatosDetalleBebida datosDetalleBebida = new DatosDetalleBebida(bebida);
        return ResponseEntity.ok(datosDetalleBebida);
    }
}
