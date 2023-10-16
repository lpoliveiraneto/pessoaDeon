CREATE TABLE public.resposta_analise_bo (
	id_resposta_analise_bo int4 NOT NULL,
	descricao varchar NOT NULL,
	status bool NOT NULL,
	CONSTRAINT resposta_analise_bo_pk PRIMARY KEY (id_resposta_analise_bo)
);

CREATE TABLE public.resposta_analise_usuario (
	id_resposta_analise_usuario int4 NOT NULL,
	descricao varchar NOT NULL,
	status bool NOT NULL,
	CONSTRAINT resposta_analise_usuario_pk PRIMARY KEY (id_resposta_analise_usuario)
);