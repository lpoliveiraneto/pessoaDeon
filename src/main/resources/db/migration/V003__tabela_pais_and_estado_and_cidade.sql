CREATE TABLE pais (
	id_pais serial4 NOT NULL,
	descricao varchar(255) NULL,
	CONSTRAINT pais_pkey PRIMARY KEY (id_pais)
);

CREATE TABLE estado (
	id_estado serial4 NOT NULL,
	descricao varchar(255) NULL,
	uf varchar(255) NULL,
	CONSTRAINT estado_pkey PRIMARY KEY (id_estado)
);

CREATE TABLE cidade (
    id_cidade serial4 NOT NULL,
    descricao varchar(255) NULL,
    fk_estado int4 NULL,
    cod_ibge int4 NULL,
    eh_capital bool NULL,
    latitude float8 NULL,
    longitude float8 NULL,
    CONSTRAINT cidade_pkey PRIMARY KEY (id_cidade),
    FOREIGN KEY (fk_estado) REFERENCES estado(id_estado)
);
