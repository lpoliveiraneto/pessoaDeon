CREATE TABLE public.peca (
	id serial4 NOT NULL,
	narrativa text NULL,
	status bool NULL,
	data_criacao timestamp NULL,
	fk_bo int4 NULL,
	fk_tipo_peca int4 NULL,
	CONSTRAINT peca_pkey PRIMARY KEY (id),
	CONSTRAINT peca_fk_bo_fkey FOREIGN KEY (fk_bo) REFERENCES public.bo_deon(id_bo)
);

CREATE TABLE public.tipo_peca (
	id_tipo serial4 NOT NULL,
	descricao varchar NULL,
	CONSTRAINT tipo_peca_pk PRIMARY KEY (id_tipo)
);

CREATE SCHEMA formulario_risco AUTHORIZATION postgres;

CREATE TABLE formulario_risco.bloco_perguntas (
	id serial4 NOT NULL,
	descricao varchar(255) NULL,
	CONSTRAINT bloco_perguntas_pkey PRIMARY KEY (id)
);
CREATE TABLE formulario_risco.respostas_formulario_peca (
	id serial4 NOT NULL,
	fk_pergunta_resposta int4 NULL,
	fk_peca int4 NULL,
	observacao varchar(1000) NULL,
	CONSTRAINT respostas_formulario_peca_pkey PRIMARY KEY (id)
);
CREATE TABLE formulario_risco.tipo_pergunta_peca (
	id serial4 NOT NULL,
	pergunta varchar(500) NULL,
	ativo bool NULL,
	fk_bloco int4 NULL,
	CONSTRAINT tipo_pergunta_peca_pkey PRIMARY KEY (id),
	CONSTRAINT tipo_pergunta_peca_bloco_perguntas_fk FOREIGN KEY (fk_bloco) REFERENCES formulario_risco.bloco_perguntas(id)
);

CREATE TABLE formulario_risco.tipo_resposta_peca (
	id serial4 NOT NULL,
	resposta varchar(255) NULL,
	ativo bool NULL,
	precisa_especificar bool NULL,
	CONSTRAINT tipo_resposta_peca_pkey PRIMARY KEY (id)
);

CREATE TABLE formulario_risco.pergunta_resposta (
	id serial4 NOT NULL,
	fk_pergunta int4 NULL,
	fk_resposta int4 NULL,
	ativa bool NULL,
	CONSTRAINT pergunta_resposta_pkey PRIMARY KEY (id),
	CONSTRAINT pergunta_resposta_tipo_pergunta_peca_fk FOREIGN KEY (fk_pergunta) REFERENCES formulario_risco.tipo_pergunta_peca(id),
	CONSTRAINT pergunta_resposta_tipo_resposta_peca_fk FOREIGN KEY (fk_resposta) REFERENCES formulario_risco.tipo_resposta_peca(id)
);

CREATE SCHEMA mpu AUTHORIZATION postgres;

CREATE TABLE mpu.requerimento_mpu (
	id serial4 NOT NULL,
	descricao text NULL,
	especificar bool NULL,
	fk_titulo_requerimento int4 NULL,
	status_ativo bool NULL,
	CONSTRAINT requerimento_mpu_pkey PRIMARY KEY (id)
);
CREATE TABLE mpu.titulo_requerimento_mpu (
	id serial4 NOT NULL,
	descricao text NULL,
	status_ativo bool NULL,
	CONSTRAINT titulo_requerimento_mpu_pkey PRIMARY KEY (id)
);
