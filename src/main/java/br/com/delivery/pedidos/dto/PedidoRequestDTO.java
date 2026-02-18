package br.com.delivery.pedidos.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

@Data
@Schema(description = "Objeto de requisição para criação de um novo pedido")
public class PedidoRequestDTO {

    @Schema(description = "Lista de produtos incluídos no pedido")
    @NotEmpty(message = "O pedido deve conter pelo menos um item")
    @JsonProperty(required = true)
    @Valid
    private List<ItemRequest> itens;

    @Schema(description = "Nome completo do cliente", example = "João Silva")
    @NotBlank(message = "O nome do cliente é obrigatório")
    @JsonProperty(required = true)
    private String nomeCliente;

    @Schema(description = "Endereço do cliente", example = "Rua das flores, Centro, S/N")
    @NotBlank(message = "O endereço é obrigatório")
    @JsonProperty(required = true)
    private String enderecoCliente;

    @Schema(description = "Código do cupom de desconto (opcional)", example = "NATAL10")
    @JsonProperty(required = true)
    private String cupom;

    @Data
    @Schema(description = "Detalhes de um item específico do pedido")
    public static class ItemRequest {

        @Schema(description = "ID único do produto cadastrado", example = "101")
        @NotNull(message = "O ID do produto é obrigatório")
        @JsonProperty(required = true)
        private Long idProduto;

        @Schema(description = "Quantidade do produto", example = "2")
        @NotNull(message = "A quantidade é obrigatória")
        @Positive(message = "A quantidade deve ser no mínimo 1")
        @JsonProperty(required = true)
        private Integer quantidade;

        @Schema(description = "Observações adicionais", example = "Sem cebola")
        @JsonProperty(required = true)
        private String observacao;
    }
}
