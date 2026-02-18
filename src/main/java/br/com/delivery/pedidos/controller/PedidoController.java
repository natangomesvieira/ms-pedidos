package br.com.delivery.pedidos.controller;

import br.com.delivery.pedidos.dto.PedidoRequestDTO;
import br.com.delivery.pedidos.model.Pedido;
import br.com.delivery.pedidos.enums.StatusPedido;
import br.com.delivery.pedidos.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pedidos")
@Tag(name = "Pedidos", description = "Endpoints para criação e acompanhamento de pedidos")
public class PedidoController {

    private final PedidoService service;

    @Operation(summary = "Lista todos os pedidos",
            description = "Retorna uma lista completa com todos os pedidos cadastrados na base de dados, sem filtros ou paginação.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Sucesso - Lista de pedidos retornada.",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Pedido.class)))
            ),
            @ApiResponse(responseCode = "204", description = "Nenhum pedido encontrado", content = @Content)
    })
    @GetMapping("/listar")
    public ResponseEntity<List<Pedido>> listarPedidos() {
        List<Pedido> pedidos = service.listarPedidos();

        if (pedidos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(pedidos);
    }

    @Operation(summary = "Realiza um novo pedido", description = "Recebe os dados do carrinho e processa a criação de um novo pedido no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pedido criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados da requisição inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao processar o pedido")
    })
    @PostMapping("/criar")
    public ResponseEntity<Pedido> realizarPedido(@Valid @RequestBody PedidoRequestDTO request) {
        Pedido novoPedido = service.criarPedido(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPedido);
    }

    @Operation(summary = "Atualiza o status de um pedido", description = "Altera o estado atual de um pedido existente (ex: de PENDENTE para CANCELADO).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Status atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado"),
            @ApiResponse(responseCode = "422", description = "Mudança de status inválida ou não permitida")
    })
    @PostMapping("/atualizar-status")
    public ResponseEntity<Void> atualizarStatusPedido(
            @Parameter(description = "ID único do pedido", example = "1") @RequestParam Long id,
            @Parameter(description = "Novo status desejado") @RequestParam StatusPedido novoStatus) {
        service.atualizarStatus(id, novoStatus);
        return ResponseEntity.ok().build();
    }
}
