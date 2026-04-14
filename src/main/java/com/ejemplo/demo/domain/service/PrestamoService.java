//Programacion3
//CarlosRam0s
//09052314141

package com.ejemplo.demo.domain.service;

import com.ejemplo.demo.api.dto.PrestamoRequest;
import com.ejemplo.demo.api.dto.PrestamoResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@Service
public class PrestamoService {

    public PrestamoResponse calcular(PrestamoRequest request) {
        BigDecimal P = request.monto();
        BigDecimal tasaAnual = request.tasaAnual();
        int n = request.meses();

        // Tasa mensual = tasaAnual / 12 / 100
        BigDecimal r = tasaAnual
                .divide(BigDecimal.valueOf(12), 10, RoundingMode.HALF_UP)
                .divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_UP);

        // ES la Formula: cuota = P * (r * (1+r)^n) / ((1+r)^n - 1)
        BigDecimal unoPlusR = BigDecimal.ONE.add(r);
        BigDecimal unoPlusRn = unoPlusR.pow(n, new MathContext(10));

        BigDecimal numerador = P.multiply(r.multiply(unoPlusRn));
        BigDecimal denominador = unoPlusRn.subtract(BigDecimal.ONE);

        BigDecimal cuotaMensual = numerador
                .divide(denominador, 2, RoundingMode.HALF_UP);

        BigDecimal totalPagar = cuotaMensual
                .multiply(BigDecimal.valueOf(n))
                .setScale(2, RoundingMode.HALF_UP);

        BigDecimal interesTotal = totalPagar
                .subtract(P)
                .setScale(2, RoundingMode.HALF_UP);

        return new PrestamoResponse(cuotaMensual, interesTotal, totalPagar);
    }
}