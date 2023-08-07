CREATE TABLE natureza_deon(
    id_natureza_deon serial4 NOT NULL,
    nome varchar NOT NULL,
    descricao varchar NOT NULL,
    path_svg varchar NOT NULL,
    tipo_natureza BIT NOT NULL,
    fk_natureza_sigma int4 NOT NULL,
    CONSTRAINT natureza_deon_pkey PRIMARY KEY (id_natureza_deon),
    FOREIGN KEY (fk_natureza_sigma) REFERENCES natureza_sigma(id_natureza)
);