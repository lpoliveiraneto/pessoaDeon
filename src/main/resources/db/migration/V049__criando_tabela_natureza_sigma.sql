CREATE TABLE natureza_sigma(
    id_natureza serial4 NOT NULL,
    codigo varchar NOT NULL,
    glossario varchar NOT NULL,
    nome varchar NOT NULL,
    tipo_natureza varchar NOT NULL,
    CONSTRAINT natureza_pkey PRIMARY KEY (id_natureza)
);