package br.com.delivery.pedidos.controller;

import br.com.delivery.pedidos.dto.PedidoRequestDTO;
import br.com.delivery.pedidos.model.Pedido;
import br.com.delivery.pedidos.model.StatusPedido;
import br.com.delivery.pedidos.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService service;

    @PostMapping
    public ResponseEntity<Pedido> realizarPedido(@RequestBody PedidoRequestDTO request) {
        Pedido novoPedido = service.criarPedido(request);
        return ResponseEntity.ok(novoPedido);
    }

    @PostMapping("atualizar-status")
    public ResponseEntity<Pedido> atualizarStatusPedido(@RequestParam Long id, @RequestParam StatusPedido novoStatus) {
        service.atualizarStatus(id, novoStatus);
        return ResponseEntity.ok().build();
    }
}
