package br.com.delivery.pedidos.model;

import br.com.delivery.pedidos.enums.TipoDesconto;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "tb_cupom")
public class Cupom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    private String codigo;

    @Column(nullable = false)
    private BigDecimal valor;

    @Column(nullable = false)
    private LocalDateTime validade;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoDesconto tipo;
}
