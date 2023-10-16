ALTER TABLE public.bo_analise ADD fk_respota_analise_bo int4 NOT NULL;
ALTER TABLE public.bo_analise ADD CONSTRAINT bo_analise_fk FOREIGN KEY (fk_respota_analise_bo) REFERENCES public.resposta_analise_bo(id_resposta_analise_bo);
