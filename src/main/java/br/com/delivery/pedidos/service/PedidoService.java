package br.com.delivery.pedidos.service;

import br.com.delivery.pedidos.dto.PedidoRequestDTO;
import br.com.delivery.pedidos.dto.ProdutoDTO;
import br.com.delivery.pedidos.integration.CatalogoClient;
import br.com.delivery.pedidos.model.Cupom;
import br.com.delivery.pedidos.model.ItemPedido;
import br.com.delivery.pedidos.model.Pedido;
import br.com.delivery.pedidos.model.StatusPedido;
import br.com.delivery.pedidos.repository.CupomRepository;
import br.com.delivery.pedidos.repository.PedidoRepository;
import br.com.delivery.pedidos.state.PedidoState;
import br.com.delivery.pedidos.strategy.DescontoFactory;
import br.com.delivery.pedidos.strategy.DescontoStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final CupomRepository cupomRepository;
    private final DescontoFactory descontoFactory;
    private final CatalogoClient catalogoClient;
    private final Map<String, PedidoState> estados;

    public Pedido criarPedido(PedidoRequestDTO request) {
        Pedido pedido = new Pedido();
        pedido.setNomeCliente(request.getNomeCliente());
        pedido.setStatus(StatusPedido.AGUARDANDO_PAGAMENTO);

        BigDecimal subtotal = processarItens(request.getItens(), pedido);
        pedido.setValorTotal(subtotal);

        BigDecimal desconto = calcularDesconto(request.getCupom(), subtotal);
        pedido.setValorDesconto(desconto);
        pedido.setTotalComDesconto(subtotal.subtract(desconto));

        if (request.getCupom() != null)
            pedido.setCupom(request.getCupom());

        return pedidoRepository.save(pedido);
    }

    public void atualizarStatus(Long id, StatusPedido novoStatus) {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow();

        pedido.setStatus(novoStatus);
        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        PedidoState state = estados.get(novoStatus.name());

        if (state != null) {
            state.aoEntrarNoEstado(pedidoSalvo);
        } else {
            throw new IllegalArgumentException("Nenhuma ação definida para o estado: " + novoStatus);
        }

    }

    private BigDecimal processarItens(List<PedidoRequestDTO.ItemRequest> itensRequest, Pedido pedido) {
        BigDecimal total = BigDecimal.ZERO;

        for (PedidoRequestDTO.ItemRequest itemSolicitado : itensRequest) {

            ProdutoDTO produto = catalogoClient.buscarProdutoPorId(itemSolicitado.getIdProduto());

            ItemPedido item = new ItemPedido();
            item.setIdProduto(produto.getId());
            item.setNomeProduto(produto.getNome());
            item.setValorUnidade(produto.getPreco());
            item.setQuantidade(itemSolicitado.getQuantidade());
            item.setObservacao(itemSolicitado.getObservacao());

            pedido.getItens().add(item);

            total = total.add(produto.getPreco().multiply(new BigDecimal(itemSolicitado.getQuantidade())));
        }
        return total;
    }

    private BigDecimal calcularDesconto(String codigoCupom, BigDecimal subtotal) {
        if (codigoCupom == null || codigoCupom.isBlank()) {
            return BigDecimal.ZERO;
        }

        Cupom cupom = cupomRepository.findByCodigo(codigoCupom)
                .orElseThrow(() -> new IllegalArgumentException("Cupom não encontrado: " + codigoCupom));

        if (LocalDateTime.now().isAfter(cupom.getValidade())) {
            throw new IllegalArgumentException("O cupom " + codigoCupom + " venceu em " + cupom.getValidade());
        }

        DescontoStrategy strategy = descontoFactory.getStrategy(cupom.getTipo());

        return strategy.calcularDesconto(subtotal, cupom.getValor());
    }
}
