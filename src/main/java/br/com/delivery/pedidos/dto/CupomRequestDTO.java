package br.com.delivery.pedidos.dto;

import br.com.delivery.pedidos.enums.TipoDesconto;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CupomRequestDTO {

    private String codigo;
    private BigDecimal valor;
    private LocalDateTime validade;
    private TipoDesconto tipo;

}
