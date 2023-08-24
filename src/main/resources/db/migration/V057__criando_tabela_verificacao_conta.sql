CREATE TABLE public.verificacao_conta (
	id serial NOT NULL,
	codigo varchar NULL,
	data_solicitacao timestamp(0) NULL,
	expiracao_codigo timestamp(0) NULL,
	fk_usuario int4 NOT NULL,
	CONSTRAINT verificacao_conta_pk PRIMARY KEY (id),
	FOREIGN KEY (fk_usuario) REFERENCES usuarios(id_usuario)
);

ALTER TABLE usuarios ADD COLUMN conta_ativa bool NULL DEFAULT false;