CREATE TABLE public.foto_perfil (
	id serial NOT NULL,
	data_upload timestamp(0) NULL,
	fk_pessoa int4 NULL,
	caminho varchar NULL,
	nome_arquivo varchar NULL,
	tipo_arquivo varchar NULL,
	fk_config_upload int4 NULL,
	CONSTRAINT foto_perfil_pk PRIMARY KEY (id),
	CONSTRAINT foto_perfil_fk FOREIGN KEY (fk_pessoa) REFERENCES pessoa(id_pessoa),
	CONSTRAINT foto_perfil_fk_1 FOREIGN KEY (fk_config_upload) REFERENCES configuracao_upload(id)
);

ALTER TABLE public.configuracao_upload ADD tipo_arquivo varchar NOT NULL;