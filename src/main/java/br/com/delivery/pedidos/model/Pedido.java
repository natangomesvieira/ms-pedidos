package br.com.delivery.pedidos.model;

import br.com.delivery.pedidos.enums.FormaPagamento;
import br.com.delivery.pedidos.enums.StatusPedido;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeCliente;

    private String enderecoCliente;

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    private LocalDateTime dataPedido = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "pedido_id")
    private List<ItemPedido> itens = new ArrayList<>();

    private String cupom;

    private BigDecimal valorDesconto = BigDecimal.ZERO;

    private BigDecimal valorTotal = BigDecimal.ZERO;

    private BigDecimal totalComDesconto = BigDecimal.ZERO;
}
