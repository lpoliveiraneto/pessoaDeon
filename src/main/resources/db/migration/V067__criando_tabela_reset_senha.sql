CREATE TABLE public.reset_senha (
	id serial NOT NULL,
	codigo varchar NULL,
	data_solicitacao timestamp(0) NULL,
	expiracao_codigo timestamp(0) NULL,
	fk_usuario int4 NOT NULL,
	CONSTRAINT reset_senha_pk PRIMARY KEY (id)
);