package br.com.delivery.pedidos.mapper;

import br.com.delivery.pedidos.dto.RelatorioRequestDTO;
import br.com.delivery.pedidos.model.ItemPedido;
import br.com.delivery.pedidos.model.Pedido;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PedidoMapper {

    RelatorioRequestDTO pedidoToRelatorioDTO(Pedido pedido);

}
