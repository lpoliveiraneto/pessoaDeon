CREATE TABLE titulo_aviso (
	id_titulo serial4 NOT NULL,
	nome varchar NOT NULL,
	fk_natureza_deon int4 NOT NULL,
	CONSTRAINT titulo_aviso_pkey PRIMARY KEY (id_titulo),
	FOREIGN KEY (fk_natureza_deon) REFERENCES natureza_deon(id_natureza)
);

CREATE TABLE descricao_titulo(
	id_descricao_titulo serial4 NOT NULL,
	nome varchar NOT NULL,
	fk_titulo_aviso int4 NOT NULL,
	CONSTRAINT descricao_titulo_pkey PRIMARY KEY (id_descricao_titulo),
	FOREIGN KEY (fk_titulo_aviso) REFERENCES titulo_aviso(id_titulo)
);