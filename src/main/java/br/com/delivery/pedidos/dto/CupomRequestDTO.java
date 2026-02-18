package br.com.delivery.pedidos.dto;

import br.com.delivery.pedidos.enums.TipoDesconto;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;


@Data
@Schema(description = "Dados para criação de um novo cupom")
public class CupomRequestDTO {

    @Schema(description = "Código identificador do cupom (único)", example = "VERAO2026")
    @NotBlank(message = "O código do cupom é obrigatório e não pode estar em branco")
    private String codigo;

    @Schema(description = "Valor do desconto (percentual ou fixo)", example = "15.00")
    @NotNull(message = "O valor do desconto é obrigatório")
    @Positive(message = "O valor deve ser maior que zero")
    private BigDecimal valor;

    @Schema(
            description = "Data e hora de expiração do cupom",
            example = "2026-12-31T23:59:59",
            type = "string",
            format = "date-time"
    )
    @NotNull(message = "A data de validade é obrigatória")
    private LocalDateTime validade;

    @Schema(description = "Define se o desconto é fixo em reais ou percentual", example = "PERCENTUAL")
    @NotNull(message = "O tipo de desconto é obrigatório")
    private TipoDesconto tipo;

}
