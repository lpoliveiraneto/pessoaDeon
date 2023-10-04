ALTER TABLE public.endereco RENAME COLUMN fk_tipo_local TO fk_tipo_moradia;
ALTER TABLE public.endereco DROP CONSTRAINT endereco_fk_tipo_local;
ALTER TABLE public.endereco ADD CONSTRAINT endereco_fk_tipo_moradia FOREIGN KEY (fk_tipo_moradia) REFERENCES listas.tipo_moradia(id);

ALTER TABLE public.endereco_envolvido ALTER COLUMN fk_bairro DROP NOT NULL;
ALTER TABLE public.endereco_envolvido ALTER COLUMN fk_estado DROP NOT NULL;
ALTER TABLE public.endereco_envolvido ALTER COLUMN fk_cidade DROP NOT NULL;
ALTER TABLE public.endereco_envolvido ALTER COLUMN fk_tipo_local DROP NOT NULL;
ALTER TABLE public.endereco_envolvido ALTER COLUMN cep DROP NOT NULL;

ALTER TABLE public.envolvido ALTER COLUMN fk_endereco_envolvido DROP NOT NULL;

