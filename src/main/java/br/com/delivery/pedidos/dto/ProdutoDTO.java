package br.com.delivery.pedidos.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProdutoDTO {
    private Long idProduto;
    private String nomeProduto;
    private BigDecimal precoProduto;
    private String descricaoProduto;
    private Boolean ativo;
}
