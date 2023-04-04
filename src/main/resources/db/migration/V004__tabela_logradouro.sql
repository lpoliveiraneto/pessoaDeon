CREATE TABLE logradouro(
    id_logradouro serial4 NOT NULL,
    cep varchar(10) NOT NULL,
    logradouro varchar(50) NOT NULL,
    bairro varchar(50) NOT NULL,
    complemento varchar(30) NULL,
    fk_estado int4 NOT NULL,
    fk_cidade int4 NOT NULL,
    CONSTRAINT logradouro_pkey PRIMARY KEY (id_logradouro),
    FOREIGN KEY (fk_estado) REFERENCES estado(id_estado),
    FOREIGN KEY (fk_cidade) REFERENCES cidade(id_cidade)
);