package br.com.delivery.pedidos.state;

import br.com.delivery.pedidos.dto.RelatorioRequestDTO;
import br.com.delivery.pedidos.integration.RelatorioClient;
import br.com.delivery.pedidos.model.Pedido;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("CANCELADO")
@RequiredArgsConstructor
public class EstadoCancelado implements PedidoState {

    private final RelatorioClient relatorioClient;

    @Override
    public void aoEntrarNoEstado(Pedido pedido) {
        System.out.println("--- Pedido CANCELADO. Iniciando integrações... ---");

        RelatorioRequestDTO dto = new RelatorioRequestDTO();
        dto.setDataPedido(pedido.getDataPedido());
        dto.setValorPedido(pedido.getTotalComDesconto());
        dto.setStatus(pedido.getStatus());

        relatorioClient.enviarParaRelatorio(dto);
    }

}
