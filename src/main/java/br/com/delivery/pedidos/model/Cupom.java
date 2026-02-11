package br.com.delivery.pedidos.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
public class Cupom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String codigo;

    private BigDecimal valor;

    private LocalDateTime validade;

    @Enumerated(EnumType.STRING)
    private TipoDesconto tipo;
}
