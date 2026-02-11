package br.com.delivery.pedidos.strategy;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component("PORCENTAGEM")
public class DescontoPorcentagem implements DescontoStrategy {
    @Override
    public BigDecimal calcularDesconto(BigDecimal valorTotalPedido, BigDecimal valorDoCupom) {
        return valorTotalPedido.multiply(valorDoCupom)
                .divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
    }
}
