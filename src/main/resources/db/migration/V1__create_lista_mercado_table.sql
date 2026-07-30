CREATE TABLE lista_mercado(
    id BIGSERIAL PRIMARY KEY,
    item_name VARCHAR(255),
    is_finished BOOLEAN DEFAULT FALSE,
    quantity DOUBLE PRECISION,
    category VARCHAR(255),
    medium_price DOUBLE PRECISION
);