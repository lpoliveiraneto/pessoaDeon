ALTER TABLE raca ADD CONSTRAINT raca_pkey PRIMARY KEY (id_raca);

ALTER TABLE escolaridade ADD CONSTRAINT escolaridade_pkey PRIMARY KEY (id_escolaridade);

ALTER TABLE sexo ADD CONSTRAINT sexo_pkey PRIMARY KEY (id_sexo);

ALTER TABLE deficiencia ADD CONSTRAINT deficiencia_pkey PRIMARY KEY (id_deficiencia);

ALTER TABLE estado_civil ADD CONSTRAINT estaco_civil_pkey PRIMARY KEY (id_estado_civil);

ALTER TABLE identidade_genero ADD CONSTRAINT identidade_genero_pkey PRIMARY KEY (id_identidade_genero);
	
ALTER TABLE orientacao_sexual ADD CONSTRAINT orientacao_sexual_pkey PRIMARY KEY (id_orientacao_sexual);