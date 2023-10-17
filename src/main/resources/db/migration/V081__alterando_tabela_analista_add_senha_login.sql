ALTER TABLE public.analista ADD login varchar(255) NOT NULL;
ALTER TABLE public.analista ADD senha varchar(255) NOT NULL;
ALTER TABLE public.analista ADD assinatura varchar(255) NOT NULL;
ALTER TABLE public.analista ADD data_alteracao timestamp;
ALTER TABLE public.analista ADD data_cadastro timestamp;
ALTER TABLE public.analista ADD fk_func_sigma int4 NOT NULL;
ALTER TABLE public.analista ADD fk_pessoa int4 NOT NULL;
ALTER TABLE public.analista ADD CONSTRAINT analista_fk_pessoa_fkey FOREIGN KEY (fk_pessoa) REFERENCES public.pessoa(id_pessoa);