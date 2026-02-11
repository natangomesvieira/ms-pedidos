package br.com.delivery.pedidos.strategy;

import br.com.delivery.pedidos.model.TipoDesconto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DescontoFactory {

    private final Map<String, DescontoStrategy> estrategias;

    public DescontoStrategy getStrategy(TipoDesconto tipo) {
        DescontoStrategy strategy = estrategias.get(tipo.toString());

        if (strategy == null) {
            throw new IllegalArgumentException("Nenhuma estratégia encontrada para o tipo: " + tipo);
        }

        return strategy;

    }
}
