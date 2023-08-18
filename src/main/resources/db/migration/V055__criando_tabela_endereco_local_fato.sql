CREATE TABLE endereco_local_fato(
    id_endereco_local serial4 NOT NULL,
    cep varchar(10) NOT NULL,
    complemento varchar(30) NULL,
    numero_local int NOT NULL,
    logradouro varchar NOT NULL,   
   	fk_bairro int4 NOT NULL,    
    fk_estado int4 NOT NULL,
    fk_cidade int4 NOT NULL,
    fk_tipo_local int4 NOT NULL,
    fk_bo int4 NOT NULL,
    CONSTRAINT endereco_local_fato_pkey PRIMARY KEY (id_endereco_local),
    FOREIGN KEY (fk_cidade) REFERENCES listas.bairro(id_bairro),
    FOREIGN KEY (fk_cidade) REFERENCES listas.cidade(id_cidade),
    FOREIGN KEY (fk_estado) REFERENCES listas.estado(id_estado),
    FOREIGN KEY (fk_tipo_local) REFERENCES listas.tipoLocal(id_tipo_local),
    FOREIGN KEY (fk_bo) REFERENCES bo_deon(id_bo)
);