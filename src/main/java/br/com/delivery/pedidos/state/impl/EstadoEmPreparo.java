package br.com.delivery.pedidos.state.impl;

import br.com.delivery.pedidos.model.Pedido;
import br.com.delivery.pedidos.state.PedidoState;
import org.springframework.stereotype.Component;

@Component("EM_PREPARO")
public class EstadoEmPreparo implements PedidoState {
    @Override
    public void aoEntrarNoEstado(Pedido pedido) {
        System.out.println("--- Pedido em preparo. Cozinha notificada. ---");
    }
}
