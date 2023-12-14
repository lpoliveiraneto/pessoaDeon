CREATE TABLE public.usuario_analise (
	id_usuario_analise serial4 NOT NULL,
	data_entrada_analise timestamp NOT NULL,
	data_analise timestamp NULL,
	status bool NULL,
	fk_usuario int4 NOT NULL,
	fk_analista int4 NULL,
	fk_resposta_analise_usuario int4 NULL,
	CONSTRAINT usuario_analise_pk PRIMARY KEY (id_usuario_analise),
	CONSTRAINT usuario_analise_fk_analista FOREIGN KEY (fk_analista) REFERENCES public.analista(id_analista),
	CONSTRAINT usuario_analise_fk_resposta_analise_usuario FOREIGN KEY (fk_resposta_analise_usuario) REFERENCES public.resposta_analise_usuario(id_resposta_analise_usuario),
	CONSTRAINT usuario_analise_fk_usuario FOREIGN KEY (fk_usuario) REFERENCES public.usuarios(id_usuario)
);