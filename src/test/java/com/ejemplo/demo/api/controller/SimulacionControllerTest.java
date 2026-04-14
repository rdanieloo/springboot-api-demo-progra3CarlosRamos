//Programacion3
//CarlosRam0s
//09052314141

package com.ejemplo.demo.api.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SimulacionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // PASO 8: caso exitoso
    @Test
    @DisplayName("POST /simulaciones/prestamo debe calcular cuota correctamente")
    void debeCalcularPrestamo() throws Exception {
        mockMvc.perform(post("/api/v1/simulaciones/prestamo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "monto": 10000,
                                    "tasaAnual": 12,
                                    "meses": 12
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cuotaMensual").exists())
                .andExpect(jsonPath("$.totalPagar").exists())
                .andExpect(jsonPath("$.interesTotal").exists());
    }

    // PASO: caso invalido
    @Test
    @DisplayName("POST /simulaciones/prestamo con monto negativo debe retornar 400")
    void debeRechazarMontoInvalido() throws Exception {
        mockMvc.perform(post("/api/v1/simulaciones/prestamo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "monto": -500,
                                    "tasaAnual": 12,
                                    "meses": 12
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.codigo").value("VALIDATION_ERROR"));
    }
}