//Programacion3
//CarlosRam0s
//09052314141

package com.ejemplo.demo.domain.service;

import com.ejemplo.demo.api.dto.SaludoResponse;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class SaludoService {

    public SaludoResponse crearSaludo(String nombre) {
        String nombreNormalizado = normalizarNombre(nombre);
        String mensaje = "Hola, %s. Bienvenido a Spring Boot 3!".formatted(nombreNormalizado);
        return new SaludoResponse(mensaje, Instant.now());
    }

    /*
    PASO 4 (EJERCICIO):
    - Modifica esta logica para personalizar el formato del nombre.
    - Ideas:
      1) Primera letra mayuscula y resto minuscula.
      2) Rechazar nombres con numeros.
      3) Agregar prefijo "Estudiante".
    */
    String normalizarNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return "Mundo";
        }

        // 2) Rechazar nombres con numeros
        if (nombre.matches(".*\\d.*")) {
            throw new IllegalArgumentException("El nombre no puede contener numeros");
        }

        // 1) Primera letra mayuscula y resto minuscula
        String recortado = nombre.trim();
        String nombreFormateado = recortado.substring(0, 1).toUpperCase()
                                + recortado.substring(1).toLowerCase();

        // 3) Agregar prefijo "Estudiante"
        return "Estudiante " + nombreFormateado;
    }
}