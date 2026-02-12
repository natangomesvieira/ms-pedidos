package br.com.delivery.pedidos.integration.impl;

import br.com.delivery.pedidos.dto.ProdutoDTO;
import br.com.delivery.pedidos.integration.CatalogoClient;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
@Profile("dev")
public class CatalogoClientMock implements CatalogoClient {

    @Override
    public ProdutoDTO buscarProdutoPorId(Long id) {
        ProdutoDTO dto = new ProdutoDTO();
        dto.setIdProduto(id);

        return switch (id.intValue()) {
            case 1 -> {
                dto.setNomeProduto("Pizza de Calabresa");
                dto.setPrecoProduto(new BigDecimal("45.00"));
                yield dto;
            }
            case 2 -> {
                dto.setNomeProduto("Coca-Cola 2L");
                dto.setPrecoProduto(new BigDecimal("10.00"));
                yield dto;
            }
            default -> {
                dto.setNomeProduto("Produto Genérico");
                dto.setPrecoProduto(new BigDecimal("15.00"));
                yield dto;
            }
        };
    }
}
