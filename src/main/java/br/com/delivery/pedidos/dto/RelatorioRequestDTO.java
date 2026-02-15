package br.com.delivery.pedidos.dto;

import br.com.delivery.pedidos.enums.StatusPedido;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class RelatorioRequestDTO {
    private Long id;
    private LocalDateTime dataPedido;
    private BigDecimal valorPedido;
    private StatusPedido status;
}
