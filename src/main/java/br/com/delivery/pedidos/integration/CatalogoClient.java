package br.com.delivery.pedidos.integration;

import br.com.delivery.pedidos.dto.ProdutoDTO;

public interface CatalogoClient {
    ProdutoDTO buscarProdutoPorId(Long id);
}