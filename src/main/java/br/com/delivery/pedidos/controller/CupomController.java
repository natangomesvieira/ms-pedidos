package br.com.delivery.pedidos.controller;

import br.com.delivery.pedidos.dto.CupomRequestDTO;
import br.com.delivery.pedidos.model.Cupom;
import br.com.delivery.pedidos.service.CupomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cupom")
@Tag(name = "Cupons", description = "Gerenciamento de cupons de desconto para pedidos")
public class CupomController {

    private final CupomService cupomService;

    @Operation(
            summary = "Cadastra um novo cupom",
            description = "Cria um novo cupom de desconto que poderá ser aplicado em futuros pedidos."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cupom criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados do cupom inválidos ou código já existente"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao salvar o cupom")
    })
    @PostMapping
    public ResponseEntity<Cupom> inserirCupom(@RequestBody CupomRequestDTO cupomRequestDTO) {
        Cupom cupom = cupomService.inserirCupom(cupomRequestDTO);
        return ResponseEntity.ok(cupom);
    }

    @Operation(
            summary = "Lista todos os cupons",
            description = "Retorna uma lista de todos os cupons cadastrados, ativos e inativos."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de cupons recuperada com sucesso",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Cupom.class)))
            ),
            @ApiResponse(responseCode = "204", description = "Nenhum cupom encontrado")
    })
    @GetMapping
    public ResponseEntity<List<Cupom>> listarCupons(){
        List<Cupom> cupons = cupomService.listarCupons();

        if (cupons.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(cupons);
    }
}
