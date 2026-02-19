-- 1. Limpeza
TRUNCATE TABLE tb_item_pedido, tb_pedido, tb_cupom RESTART IDENTITY CASCADE;

-- 2. Cupons
INSERT INTO tb_cupom (codigo, valor, validade, tipo) VALUES
                                                         ('DESC10', 10.00, '2026-12-31 23:59:59', 'VALOR_FIXO'),
                                                         ('DESC50', 50.00, '2026-06-01 12:00:00', 'PORCENTAGEM'),
                                                         ('DESC15', 15.00, '2026-03-15 08:30:00', 'VALOR_FIXO'),
                                                         ('DESC20', 20.00, '2026-04-12 23:59:59', 'PORCENTAGEM');