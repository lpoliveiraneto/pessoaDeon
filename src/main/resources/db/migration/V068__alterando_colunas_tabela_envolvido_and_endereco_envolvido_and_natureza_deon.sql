ALTER TABLE public.endereco_envolvido DROP CONSTRAINT endereco_envolvido_fk_envolvido_fkey;
ALTER TABLE public.endereco_envolvido DROP COLUMN fk_envolvido;

ALTER TABLE public.envolvido ADD COLUMN fk_endereco_envolvido int4 NOT NULL;
ALTER TABLE public.envolvido ADD CONSTRAINT fk_endereco_envolvido FOREIGN KEY (fk_endereco_envolvido) REFERENCES public.endereco_envolvido(id_endereco_envolvido);

ALTER TABLE public.natureza_deon ALTER COLUMN codigo DROP NOT NULL;
