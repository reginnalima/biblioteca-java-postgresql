-- EXERCÍCIO 3 — Consultas SQL

-- 1. Todo o acervo: código, título, tipo e disponibilidade
SELECT codigo, titulo, tipo, disponivel
FROM item
ORDER BY codigo;

-- 2. Empréstimos em aberto, com nome do usuário e título do item
SELECT u.nome AS usuario,
       i.titulo AS item,
       e.data_retirada,
       e.data_devolucao_prevista
FROM emprestimo e
JOIN usuario u ON u.id = e.usuario_id
JOIN item    i ON i.id = e.item_id
WHERE e.data_devolucao IS NULL;

-- 3. Total de multas acumuladas por usuário
--    (LEFT JOIN + COALESCE para listar também quem nunca pagou multa)
SELECT u.nome,
       COALESCE(SUM(e.valor_multa), 0) AS total_multas
FROM usuario u
LEFT JOIN emprestimo e ON e.usuario_id = u.id
GROUP BY u.id, u.nome
ORDER BY total_multas DESC;

-- 4. Itens que nunca foram emprestados
SELECT i.codigo, i.titulo, i.tipo
FROM item i
LEFT JOIN emprestimo e ON e.item_id = i.id
WHERE e.id IS NULL;
