package com.konectape.tabernero.infra.error;

import com.konectape.tabernero.dto.request.DatosBebida;
import com.konectape.tabernero.model.Bebida;
import com.konectape.tabernero.repository.BebidaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private final BebidaRepository bebidaRepository;

    public GlobalExceptionHandler(BebidaRepository bebidaRepository) {
        this.bebidaRepository = bebidaRepository;
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String nombre = ex.getName();
        String tipo = ex.getRequiredType().getSimpleName();
        Object valor = ex.getValue();
        String mensaje = String.format("El parámetro '%s' debe ser de tipo %s, pero se recibió: %s", nombre, tipo, valor);
        ErrorResponse errorResponse = new ErrorResponse(mensaje);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFound(EntityNotFoundException ex) {
        String mensaje = "El recurso solicitado no fue encontrado";
        ErrorResponse errorResponse = new ErrorResponse(mensaje);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorDatos>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<ErrorDatos> errors = ex.getFieldErrors().stream().map(ErrorDatos::new).toList();
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleIntegrityValidation(SQLIntegrityConstraintViolationException ex, HttpServletRequest request) {
        DatosBebida datosBebida = (DatosBebida) request.getAttribute("datosBebida");
        Optional<Bebida> bebidaImage = bebidaRepository.findByImage(datosBebida.image());
        Optional<Bebida> bebidaTitle = bebidaRepository.findByTitle(datosBebida.title());
        StringBuilder errorIntegrity = new StringBuilder();
        if (bebidaImage.isPresent()) {
            errorIntegrity.append("Esta imagen ya está guardada, pruebe con una URL diferente. ");
        }
        if (bebidaTitle.isPresent()) {
            errorIntegrity.append("Este título ya está guardado, pruebe con uno diferente. ");
        }

        if (!errorIntegrity.isEmpty()) {
            ErrorResponse errorResponse = new ErrorResponse(errorIntegrity.toString().trim());
            return ResponseEntity.badRequest().body(errorResponse);
        }
        ErrorResponse errorResponse = new ErrorResponse("Error de integridad desconocido");
        return ResponseEntity.badRequest().body(errorResponse);
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorResponse> handleValidationExceptions(Exception ex) {
//        String mensaje = "Upss :(!! Ha ocurrido un error en el servidor";
//        ErrorResponse errorResponse = new ErrorResponse(mensaje);
//        return ResponseEntity.badRequest().body(errorResponse);
//    }
}
