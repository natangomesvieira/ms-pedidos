package br.com.delivery.pedidos.strategy;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component("VALOR_FIXO")
public class DescontoValorFixo implements DescontoStrategy {
    @Override
    public BigDecimal calcularDesconto(BigDecimal valorTotalPedido, BigDecimal valorDoCupom) {
        if (valorDoCupom.compareTo(valorTotalPedido) > 0) {
            return valorTotalPedido;
        }
        return valorDoCupom;
    }
}
