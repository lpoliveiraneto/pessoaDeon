CREATE TABLE pessoa(
    id_pessoa bigint(20) NOT NULL AUTO_INCREMENT,
    nome varchar(255) NOT NULL,
    nome_mae varchar(255) NOT NULL,
    nome_pai varchar(255),
    alcunha varchar(50),
    cpf varchar(12) NOT NULL,
    data_nascimento DATE NOT NULL,
    sexo varchar(10) NOT NULL,
    nome_social varchar(255),
    orientacao_sexual varchar(10) NOT NULL
    identidade_genero varchar(10) NOT NULL,
    deficiencia varchar(20) NOT NULL,
    cor_pele varchar(20) NOT NULL,
    fk_pais int4 NOT NULL,
    fk_estado int4 NOT NULL,
    fk_cidade int4 NOT NULL,
    fk_endereco int NOT NULL,
    CONSTRAINT pessoa_pkey PRIMARY KEY (id_pessoa),
    FOREIGN KEY (fk_pais) REFERENCES pais(id_pais),
    FOREIGN KEY (fk_estado) REFERENCES estado(id_estado),
    FOREIGN KEY (fk_cidade) REFERENCES cidade(id_cidade),
    FOREIGN KEY (fk_endereco) REFERENCES endereco(id_endereco)
);