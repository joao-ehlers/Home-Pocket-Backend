CREATE TABLE lista_mercado(
    id BIGSERIAL PRIMARY KEY,
    nome_item VARCHAR(255),
    st_finalizado BOOLEAN DEFAULT FALSE,
    quantidade DOUBLE PRECISION,
    categoria VARCHAR(255),
    preco_medio DOUBLE PRECISION
);