package br.com.delivery.pedidos.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Data
@Schema(description = "Representação de um produto no sistema")
public class ProdutoDTO {

    @Schema(description = "ID único do produto (gerado pelo banco)", example = "1")
    @NotNull(message = "O ID do produto é obrigatório")
    private Long idProduto;

    @Schema(description = "Nome comercial do produto", example = "Hambúrguer Artesanal")
    @NotBlank(message = "O nome do produto é obrigatório")
    private String nomeProduto;

    @Schema(description = "Preço unitário de venda", example = "35.90")
    @NotNull(message = "O preço é obrigatório")
    @Positive(message = "O preço deve ser maior que zero")
    private BigDecimal precoProduto;

    @Schema(description = "Descrição detalhada dos ingredientes ou características", example = "Pão brioche, carne 180g, queijo cheddar e bacon.")
    @NotBlank(message = "A descrição do produto é obrigatória")
    private String descricaoProduto;

    @Schema(description = "Indica se o produto está disponível para venda no cardápio", example = "true")
    @NotNull(message = "O status ativo/inativo é obrigatório")
    private Boolean ativo;
}
