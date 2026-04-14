//Programacion3
//CarlosRam0s
//09052314141

package com.ejemplo.demo.api.dto;

import java.math.BigDecimal;

public record PrestamoResponse(
        BigDecimal cuotaMensual,
        BigDecimal interesTotal,
        BigDecimal totalPagar
) {}