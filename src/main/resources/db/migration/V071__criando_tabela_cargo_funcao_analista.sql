
CREATE TABLE public.cargo (
	id_cargo serial4 NOT NULL,
	descricao varchar(255) NULL,
	CONSTRAINT cargo_pkey PRIMARY KEY (id_cargo)
);

CREATE TABLE public.funcao (
	id_funcao serial4 NOT NULL,
	descricao varchar(255) NULL,
	CONSTRAINT funcao_pkey PRIMARY KEY (id_funcao)
);

CREATE TABLE public.analista (
	id_analista bigserial NOT NULL,
	matricula varchar(255) NULL,
	nome varchar(255) NOT NULL,
	status bool NULL,
	fk_cargo int4 NULL,
	fk_funcao int4 NULL,
	CONSTRAINT analista_pkey PRIMARY KEY (id_analista),
	CONSTRAINT analista_fk_cargo_analista_fkey FOREIGN KEY (fk_cargo) REFERENCES public.cargo(id_cargo),
	CONSTRAINT analista_fk_funcao_analista_fkey FOREIGN KEY (fk_funcao) REFERENCES public.funcao(id_funcao)
);

ALTER TABLE public.bo_deon ADD numero_bo int4 NULL;
ALTER TABLE public.bo_deon ADD status boolean NULL;