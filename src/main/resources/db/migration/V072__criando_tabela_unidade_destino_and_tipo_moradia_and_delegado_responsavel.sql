CREATE TABLE listas.unidade_destino (
	id_unidade serial4 NOT NULL,
	descricao varchar NOT NULL,
	fk_unidade_sigma int4 NOT NULL,
	sigla varchar NULL,
	CONSTRAINT unidade_destino_pk PRIMARY KEY (id_unidade)
);

CREATE TABLE listas.tipo_moradia (
	id serial4 NOT NULL,
	descricao varchar NULL,
	valor varchar NULL,
	CONSTRAINT tipo_moradia_pk PRIMARY KEY (id)
);

CREATE TABLE public.delegado_responsavel (
	id serial4 NOT NULL,
	nome varchar NULL,
	matricula varchar NULL,
	data_cadastro timestamp(0) NULL,
	status boolean NULL,
	fk_func_sigma int4 NULL,
	CONSTRAINT delegado_responsavel_pk PRIMARY KEY (id)
);