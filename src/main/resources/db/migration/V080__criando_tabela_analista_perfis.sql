CREATE TABLE ANALISTA_PERFIS(
    id serial4 NOT NULL,
    fk_analista int4 NOT NULL,
    fk_perfil int4 NOT NULL,
    CONSTRAINT analista_perfis_pkey PRIMARY KEY (id),
    FOREIGN KEY (fk_analista) REFERENCES analista(id_analista),
    FOREIGN KEY (fk_perfil) REFERENCES perfis(id_perfil)
);