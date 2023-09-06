CREATE TABLE endereco_envolvido(
    id_endereco_envolvido serial4 NOT NULL,
    cep varchar(10) NOT NULL,
    complemento varchar(30),
    numero_local int,
    logradouro varchar,
    referencia varchar(255),   
   	fk_bairro int4 NOT NULL,    
    fk_estado int4 NOT NULL,
    fk_cidade int4 NOT NULL,
    fk_tipo_local int4 NOT NULL,
	fk_envolvido int4 NOT NULL,
    CONSTRAINT endereco_envolvido_pkey PRIMARY KEY (id_endereco_envolvido),
    FOREIGN KEY (fk_bairro) REFERENCES listas.bairro(id_bairro),
    FOREIGN KEY (fk_cidade) REFERENCES listas.cidade(id_cidade),
    FOREIGN KEY (fk_estado) REFERENCES listas.estado(id_estado),
    FOREIGN KEY (fk_tipo_local) REFERENCES listas.tipoLocal(id_tipo_local),
    FOREIGN KEY (fk_envolvido) REFERENCES envolvido(id_envolvido)
);