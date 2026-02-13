package br.com.delivery.pedidos.state;

import br.com.delivery.pedidos.model.Pedido;
import org.springframework.stereotype.Component;

@Component("SAIU_PARA_ENTREGA")
public class EstadoSaiuParaEntrega implements PedidoState {
    @Override
    public void aoEntrarNoEstado(Pedido pedido) {
        System.out.println("--- O pedido saiu para entrega. Usuário notificado. ---");
    }
}
