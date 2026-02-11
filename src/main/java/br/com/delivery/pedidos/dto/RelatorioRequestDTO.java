package br.com.delivery.pedidos.dto;

import br.com.delivery.pedidos.model.StatusPedido;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class RelatorioRequestDTO {
    private LocalDateTime dataPedido;
    private BigDecimal valorPedido;
    private StatusPedido status;
}
