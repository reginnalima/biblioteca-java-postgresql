-- EXERCÍCIO 2 — Modelagem do banco (PostgreSQL)
-- Ordem importa: item e usuario primeiro; emprestimo depende das duas.

DROP TABLE IF EXISTS emprestimo;
DROP TABLE IF EXISTS item;
DROP TABLE IF EXISTS usuario;

CREATE TABLE item (
    id         INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    codigo     VARCHAR(10)  NOT NULL UNIQUE,
    titulo     VARCHAR(150) NOT NULL,
    tipo       VARCHAR(10)  NOT NULL CHECK (tipo IN ('livro', 'revista')),
    autor      VARCHAR(100),          -- revistas podem não ter autor
    edicao     INTEGER,
    disponivel BOOLEAN      NOT NULL DEFAULT TRUE
);

CREATE TABLE usuario (
    id           INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome         VARCHAR(100) NOT NULL,
    tipo         VARCHAR(10)  NOT NULL CHECK (tipo IN ('aluno', 'professor')),
    limite_itens INTEGER      NOT NULL CHECK (limite_itens > 0)
);

CREATE TABLE emprestimo (
    id                      INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    item_id                 INTEGER NOT NULL REFERENCES item(id),
    usuario_id              INTEGER NOT NULL REFERENCES usuario(id),
    data_retirada           DATE    NOT NULL,
    data_devolucao_prevista DATE    NOT NULL,
    data_devolucao          DATE,                      -- sem NOT NULL: vazia até o item voltar
    valor_multa             NUMERIC(8,2) NOT NULL DEFAULT 0
);

-- DADOS DE TESTE

INSERT INTO item (codigo, titulo, tipo, autor, edicao, disponivel) VALUES
    ('L001', 'Java: Como Programar',        'livro',   'Deitel',          10, TRUE),
    ('L002', 'Banco de Dados Relacional',   'livro',   'C. J. Date',       8, FALSE),
    ('L003', 'Estruturas de Dados',         'livro',   'Cormen',           3, TRUE),
    ('R001', 'Superinteressante',           'revista', NULL,             450, TRUE),
    ('R002', 'Galileu',                     'revista', NULL,             380, TRUE);

INSERT INTO usuario (nome, tipo, limite_itens) VALUES
    ('Maria',  'aluno',     3),
    ('Carlos', 'professor', 5);

-- Empréstimo já devolvido (revista, 7 dias, 3 dias de atraso => 3 x 1,00)
INSERT INTO emprestimo (item_id, usuario_id, data_retirada, data_devolucao_prevista, data_devolucao, valor_multa)
VALUES (4, 1, '2026-09-01', '2026-09-08', '2026-09-11', 3.00);

-- Empréstimo em aberto (livro, 14 dias, data_devolucao fica NULL)
INSERT INTO emprestimo (item_id, usuario_id, data_retirada, data_devolucao_prevista, data_devolucao, valor_multa)
VALUES (2, 2, '2026-10-20', '2026-11-03', NULL, 0);
