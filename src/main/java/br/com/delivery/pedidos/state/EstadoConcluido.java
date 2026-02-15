package br.com.delivery.pedidos.state;

import br.com.delivery.pedidos.dto.RelatorioRequestDTO;
import br.com.delivery.pedidos.integration.RelatorioClient;
import br.com.delivery.pedidos.model.Pedido;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("CONCLUIDO")
@RequiredArgsConstructor
public class EstadoConcluido implements PedidoState {

    private final RelatorioClient relatorioClient;

    @Override
    public void aoEntrarNoEstado(Pedido pedido) {
        System.out.println("--- Pedido CONCLUÍDO. Iniciando integrações... ---");

        RelatorioRequestDTO dto = new RelatorioRequestDTO();
        dto.setId(pedido.getId());
        dto.setDataPedido(pedido.getDataPedido());
        dto.setValorPedido(pedido.getTotalComDesconto());
        dto.setStatus(pedido.getStatus());

        relatorioClient.enviarParaRelatorio(dto);
    }
}
