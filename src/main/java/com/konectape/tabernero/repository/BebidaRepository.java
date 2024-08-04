package com.konectape.tabernero.repository;

import com.konectape.tabernero.model.Bebida;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BebidaRepository extends JpaRepository<Bebida, Long> {
    Optional<Bebida> findByImage(String image);
    Optional<Bebida> findByTitle(String title);
}
