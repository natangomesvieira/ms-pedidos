package br.com.delivery.pedidos.strategy;

import java.math.BigDecimal;

public interface DescontoStrategy {
    BigDecimal calcularDesconto(BigDecimal valorTotalPedido, BigDecimal valorDoCupom);
}
