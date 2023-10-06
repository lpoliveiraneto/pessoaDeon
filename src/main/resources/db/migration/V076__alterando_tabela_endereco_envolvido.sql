ALTER TABLE public.endereco_envolvido DROP CONSTRAINT endereco_envolvido_fk_tipo_local;
ALTER TABLE public.endereco_envolvido DROP COLUMN fk_tipo_local;
ALTER TABLE public.endereco_envolvido ADD fk_tipo_moradia int4 NULL;
ALTER TABLE public.endereco_envolvido ADD CONSTRAINT endereco_envolvido_fk_tipo_moradia FOREIGN KEY (fk_tipo_moradia) REFERENCES listas.tipo_moradia(id);
