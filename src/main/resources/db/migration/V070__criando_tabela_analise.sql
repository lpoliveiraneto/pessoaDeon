CREATE TABLE public.analise (
	id_bo_analise serial4 NOT NULL,
	data_analise timestamp NOT NULL,
	hora_analise timestamp NOT NULL,
	data_final_analise timestamp NOT NULL,
	fk_bo int4 NOT NULL,
	fk_analista int4 NOT NULL,
	FOREIGN KEY (fk_bo) REFERENCES public.bo_deon(id_bo);
);
