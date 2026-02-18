-- 1. Limpeza
TRUNCATE TABLE tb_item_pedido, tb_pedido, tb_cupom RESTART IDENTITY CASCADE;

-- 2. Cupons
INSERT INTO tb_cupom (codigo, valor, validade, tipo) VALUES
                                                         ('DESC10', 10.00, '2026-12-31 23:59:59', 'VALOR_FIXO'),
                                                         ('DESC50', 50.00, '2026-06-01 12:00:00', 'PORCENTAGEM'),
                                                         ('DESC15', 15.00, '2026-03-15 08:30:00', 'VALOR_FIXO'),
                                                         ('DESC20', 20.00, '2026-04-12 23:59:59', 'PORCENTAGEM');

-- 3. Inserção de Pedidos
INSERT INTO tb_pedido (id, nome_cliente, endereco_cliente, status, data_pedido, forma_pagamento, cupom, valor_desconto, valor_total, total_com_desconto)
VALUES
    (1, 'João Silva', 'Rua das Flores, 123', 'AGUARDANDO_PAGAMENTO', NOW(), 'PIX', 'DESC10', 10.00, 100.00, 90.00),
    (2, 'Maria Souza', 'Av. Central, 456', 'EM_PREPARO', NOW(), 'CARTAO_CREDITO', NULL, 0.00, 50.00, 50.00);

-- 4. Inserção de Itens
INSERT INTO tb_item_pedido (pedido_id, id_produto, nome_produto, quantidade, valor_unidade, observacao)
VALUES
    (1, 101, 'Pizza de Calabresa', 2, 40.00, 'Sem cebola'),
    (1, 102, 'Pizza de Mussarela', 1, 20.00, ''),
    (2, 103, 'Pizza de Frango', 1, 50.00, 'Pizza Família');

-- 5. AJUSTE DA SEQUENCE
SELECT setval(pg_get_serial_sequence('tb_pedido', 'id'), coalesce(max(id), 1)) FROM tb_pedido;