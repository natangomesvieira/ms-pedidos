package br.com.delivery.pedidos.integration;

import br.com.delivery.pedidos.dto.RelatorioRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Profile("prod")
@RequiredArgsConstructor
public class RelatorioClientHttp implements RelatorioClient {

    private final RestTemplate restTemplate;
    @Value("${integration.relatorios.url}")
    private String urlRelatorio;

    @Override
    public void enviarParaRelatorio(RelatorioRequestDTO pedido) {
        try {
            restTemplate.postForLocation(urlRelatorio, pedido);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao comunicar com o Relatório: " + e.getMessage());
        }
    }
}
