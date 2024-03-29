CREATE TABLE public.envolvido (
	id_envolvido serial4 NOT NULL,
	nome varchar(255),
	nome_mae varchar(255),
	nome_pai varchar(255) NULL,
	alcunha varchar(50) NULL,
	data_nascimento date,
	fk_sexo int4 NULL,
	nome_social varchar(255) NULL,
	fk_orientacao_sexual int4 NULL,
	fk_identidade_genero int4 NULL,
	fk_deficiencia int4 NULL,
	fk_raca int4 NULL,
	fk_pais int4 NULL,
	fk_estado int4 NULL,
	fk_cidade int4 NULL,
	email varchar(255) NULL,
	telefone varchar(255) NULL,
	fk_profissao int4 NULL,
	fk_estado_civil int4 NULL,
	fk_escolaridade int4 NULL,
	numero_documento varchar(255) NULL,
	fk_tipo_documento int4 NULL,
	fk_pessoa int4 NULL,
	CONSTRAINT envolvido_pkey PRIMARY KEY (id_envolvido),
	CONSTRAINT envolvido_fk_cidade_fkey FOREIGN KEY (fk_cidade) REFERENCES listas.cidade(id_cidade),
	CONSTRAINT envolvido_fk_deficiencia_fkey FOREIGN KEY (fk_deficiencia) REFERENCES listas.deficiencia(id_deficiencia),
	CONSTRAINT envolvido_fk_escolaridade_fkey FOREIGN KEY (fk_escolaridade) REFERENCES listas.escolaridade(id_escolaridade),
	CONSTRAINT envolvido_fk_estado_civil_fkey FOREIGN KEY (fk_estado_civil) REFERENCES listas.estado_civil(id_estado_civil),
	CONSTRAINT envolvido_fk_estado_fkey FOREIGN KEY (fk_estado) REFERENCES listas.estado(id_estado),
	CONSTRAINT envolvido_fk_identidade_genero_fkey FOREIGN KEY (fk_identidade_genero) REFERENCES listas.identidade_genero(id_identidade_genero),
	CONSTRAINT envolvido_fk_orientacao_sexual_fkey FOREIGN KEY (fk_orientacao_sexual) REFERENCES listas.orientacao_sexual(id_orientacao_sexual),
	CONSTRAINT envolvido_fk_pais_fkey FOREIGN KEY (fk_pais) REFERENCES listas.pais(id_pais),
	CONSTRAINT envolvido_fk_profissao_fkey FOREIGN KEY (fk_profissao) REFERENCES listas.profissao(id_profissao),
	CONSTRAINT envolvido_fk_raca_fkey FOREIGN KEY (fk_raca) REFERENCES listas.raca(id_raca),
	CONSTRAINT envolvido_fk_sexo_fkey FOREIGN KEY (fk_sexo) REFERENCES listas.sexo(id_sexo),
	CONSTRAINT envolvido_fk_tipo_documento_fkey FOREIGN KEY (fk_tipo_documento) REFERENCES listas.tipo_documento(id_tipo_documento),
	CONSTRAINT envolvido_fk_pessoa FOREIGN KEY (fk_pessoa) REFERENCES public.pessoa(id_pessoa)
);
