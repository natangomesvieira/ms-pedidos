package br.com.delivery.pedidos.state;

import br.com.delivery.pedidos.dto.RelatorioRequestDTO;
import br.com.delivery.pedidos.integration.RelatorioClient;
import br.com.delivery.pedidos.mapper.PedidoMapper;
import br.com.delivery.pedidos.model.Pedido;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("CONCLUIDO")
@RequiredArgsConstructor
public class EstadoConcluido implements PedidoState {

    private final RelatorioClient relatorioClient;
    private final PedidoMapper pedidoMapper;

    @Override
    public void aoEntrarNoEstado(Pedido pedido) {
        System.out.println("--- Pedido CONCLUÍDO. Iniciando integrações... ---");

        RelatorioRequestDTO dto = pedidoMapper.pedidoToRelatorioDTO(pedido);

        relatorioClient.enviarParaRelatorio(dto);
    }
}
