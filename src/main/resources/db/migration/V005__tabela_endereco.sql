CREATE TABLE ENDERECO(
    id_endereco serial4 NOT NULL,
    numero int NOT NULL,
    referencia varchar(50) NULL,
    atual boolean NOT NULL,
    fk_pessoa int4 NOT NULL,
    fk_logradouro int4 NOT NULL,
    CONSTRAINT endereco_pkey PRIMARY KEY (id_endereco),
    FOREIGN KEY (fk_logradouro) REFERENCES logradouro(id_logradouro)
);