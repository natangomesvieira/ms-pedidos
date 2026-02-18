package br.com.delivery.pedidos.integration;

import br.com.delivery.pedidos.dto.RelatorioRequestDTO;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class RelatorioClientMock implements RelatorioClient {

    @Override
    public void enviarParaRelatorio(RelatorioRequestDTO pedido) {
        System.out.println("--- [INTEGRAÇÃO] Enviando pedido " + pedido.getStatus() + " para Relatórios ---");
        System.out.println("Payload: Valor Total R$ " + pedido.getTotalComDesconto());
    }
}