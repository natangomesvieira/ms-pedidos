package br.com.delivery.pedidos.integration;

import br.com.delivery.pedidos.dto.RelatorioRequestDTO;

public interface RelatorioClient {
    void enviarParaRelatorio(RelatorioRequestDTO pedido);
}