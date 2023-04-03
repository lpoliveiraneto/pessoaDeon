CREATE TABLE ENDERECO(
    id_endereco int NOT NULL AUTO_INCREMENT,
    numero int NOT NULL,
    referencia varchar(50) NULL,
    atual boolean NOT NULL,
    fk_pessoa bigint(20) NOT NULL,
    fk_logradouro int NOT NULL,
    CONSTRAINT endereco_pkey PRIMARY KEY (id_endereco),
    FOREIGN KEY (fk_pessoa) REFERENCES pessoa(id_pessoa),
    FOREIGN KEY (fk_logradouro) REFERENCES logradouro(id_logradouro)
);