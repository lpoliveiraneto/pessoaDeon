
ALTER TABLE public.analise ADD CONSTRAINT analise_fk FOREIGN KEY (fk_analista) REFERENCES public.analista(id_analista);

ALTER TABLE public.analise ADD status bool NULL;
ALTER TABLE public.analise RENAME COLUMN data_final_analise TO data_entrada_analise;
ALTER TABLE public.analise DROP COLUMN hora_analise;

ALTER TABLE public.analise RENAME TO bo_analise;