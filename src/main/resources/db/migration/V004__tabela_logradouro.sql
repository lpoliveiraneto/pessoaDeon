CREATE TABLE logradouro(
    id_logradouro int NOT NULL AUTO_INCREMENT,
    cep varchar(10) NOT NULL,
    logradouro var char(50) NOT NULL,
    bairro varchar(50) NOT NULL,
    complemento varchar(30) NULL,
    fk_estado int4 NOT NULL,
    fk_cidade int4 NOT NULL,
    CONSTRAINT logradouro_pkey PRIMARY KEY (id_email),
    FOREIGN KEY (fk_estado) REFERENCES estado(id_estado),
    FOREIGN KEY (fk_cidade) REFERENCES cidade(id_cidade)
);