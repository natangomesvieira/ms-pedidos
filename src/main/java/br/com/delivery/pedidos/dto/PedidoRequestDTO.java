package br.com.delivery.pedidos.dto;

import lombok.Data;

import java.util.List;

@Data
public class PedidoRequestDTO {
    private List<ItemRequest> itens;
    private String nomeCliente;
    private String cupom;

    @Data
    public static class ItemRequest {
        private Long idProduto;
        private Integer quantidade;
        private String observacao;
    }
}
