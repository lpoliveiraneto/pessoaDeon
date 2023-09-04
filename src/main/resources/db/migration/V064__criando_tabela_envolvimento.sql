CREATE TABLE public.envolvimento (
	id_envolvimento serial4 NOT NULL,
	fk_natureza_bo serial4 NOT NULL,
	fk_envolvido serial4 NOT NULL,
	fk_tipo_participacao serial4 NOT NULL,
	CONSTRAINT envolvimento_pkey PRIMARY KEY (id_envolvimento),
	CONSTRAINT envolvimento_fk_natureza_bo FOREIGN KEY (fk_natureza_bo) REFERENCES public.natureza_bo(id),
	CONSTRAINT envolvimento_fk_envolvido FOREIGN KEY (fk_envolvido) REFERENCES public.envolvido(id_envolvido),
	CONSTRAINT envolvimento_fk_tipo_participacao FOREIGN KEY (fk_tipo_participacao) REFERENCES public.tipo_participacao(id_tipo_participacao)

);