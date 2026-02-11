package br.com.delivery.pedidos.state;

import br.com.delivery.pedidos.model.Pedido;

public interface PedidoState {
    void aoEntrarNoEstado(Pedido pedido);
}
