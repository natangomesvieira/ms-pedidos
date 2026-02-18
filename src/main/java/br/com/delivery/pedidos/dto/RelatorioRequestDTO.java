package br.com.delivery.pedidos.dto;

import br.com.delivery.pedidos.enums.FormaPagamento;
import br.com.delivery.pedidos.enums.StatusPedido;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Schema(description = "Representação completa do pedido enviada para sincronização com o microserviço de relatórios")
public class RelatorioRequestDTO {

    @Schema(description = "Identificador único do pedido", example = "1050")
    private Long id;

    @Schema(description = "Nome do cliente que realizou o pedido", example = "João da Silva")
    private String nomeCliente;

    @Schema(description = "Endereço completo para entrega", example = "Rua das Flores, 123, Bairro Central")
    private String enderecoCliente;

    @Schema(description = "Status atual do processamento do pedido", example = "CONCLUIDO")
    private StatusPedido status;

    @Schema(description = "Data e hora oficial da criação do pedido", example = "2026-02-16T19:00:00")
    private LocalDateTime dataPedido;

    @Schema(description = "Método de pagamento selecionado", example = "PIX")
    private FormaPagamento formaPagamento;

    @Schema(description = "Lista detalhada dos itens contidos no pedido")
    private List<ItemRelatorioDTO> itens;

    @Schema(description = "Código do cupom aplicado, se houver", example = "PROMO10")
    private String cupom;

    @Schema(description = "Valor total de descontos subtraídos", example = "15.00")
    private BigDecimal valorDesconto;

    @Schema(description = "Soma bruta dos valores dos itens (sem descontos)", example = "150.00")
    private BigDecimal valorTotal;

    @Schema(description = "Valor final líquido a ser pago pelo cliente", example = "135.00")
    private BigDecimal totalComDesconto;

    @Data
    @Schema(description = "Detalhes de um item específico para fins de registro histórico")
    public static class ItemRelatorioDTO {

        @Schema(description = "ID de referência do produto original", example = "55")
        private Long idProduto;

        @Schema(description = "Nome do produto no momento da compra", example = "Hambúrguer Gourmet")
        private String nomeProduto;

        @Schema(description = "Quantidade adquirida", example = "2")
        private Integer quantidade;

        @Schema(description = "Preço unitário praticado na data da venda", example = "35.00")
        private BigDecimal valorUnidade;

        @Schema(description = "Observações específicas do cliente para este item", example = "Sem cebola")
        private String observacao;
    }
}