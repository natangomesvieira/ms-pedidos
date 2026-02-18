package br.com.delivery.pedidos.model;

import lombok.Data;
import java.math.BigDecimal;
import jakarta.persistence.*;

@Entity
@Data
@Table(name = "tb_item_pedido")
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long idProduto;

    @Column(nullable = false, length = 150)
    private String nomeProduto;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valorUnidade;

    @Column(length = 255)
    private String observacao;
}
