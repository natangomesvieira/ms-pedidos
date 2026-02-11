package br.com.delivery.pedidos.integration.impl;

import br.com.delivery.pedidos.dto.ProdutoDTO;
import br.com.delivery.pedidos.integration.CatalogoClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Profile("prod")
@RequiredArgsConstructor
public class CatalogoClientHttp implements CatalogoClient {

    private final RestTemplate restTemplate;
    @Value("${integration.catalogo.url}")
    private String urlCatalogo;

    @Override
    public ProdutoDTO buscarProdutoPorId(Long id) {
        try {
            return restTemplate.getForObject(urlCatalogo + id, ProdutoDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao comunicar com o Catálogo: " + e.getMessage());
        }
    }
}