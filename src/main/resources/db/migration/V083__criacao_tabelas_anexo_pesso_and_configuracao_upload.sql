
CREATE TABLE public.configuracao_upload (
	id serial NOT NULL,
	"path" varchar NOT NULL,
	descricao varchar NOT NULL,
	status bool NULL,
	CONSTRAINT configuracao_upload_pk PRIMARY KEY (id)
);

CREATE TABLE public.anexo_pessoa (
	id serial NOT NULL,
	data_upload timestamp(0) NULL,
	fk_pessoa int4 NULL,
	caminho varchar NULL,
	nome_arquivo varchar NULL,
	tipo_arquivo varchar NULL,
	fk_config_upload int4 NULL,
	CONSTRAINT anexo_pessoa_pk PRIMARY KEY (id),
	CONSTRAINT anexo_pessoa_fk FOREIGN KEY (fk_pessoa) REFERENCES public.pessoa(id_pessoa),
	CONSTRAINT anexo_pessoa_fk_1 FOREIGN KEY (fk_config_upload) REFERENCES public.configuracao_upload(id)
);

ALTER TABLE public.bo_analise ALTER COLUMN status DROP NOT NULL;
ALTER TABLE public.bo_analise ALTER COLUMN fk_respota_analise_bo DROP NOT NULL;

ALTER TABLE public.analista_perfis RENAME COLUMN fk_perfil TO perfis_id_perfil;
ALTER TABLE public.analista_perfis RENAME COLUMN fk_analista TO analista_id_analista;
