CREATE TABLE USUARIOS_PERFIS(
    id serial4 NOT NULL,
    usuario_id_usuario int4 NOT NULL,
    perfis_id_perfil int4 NOT NULL,
    CONSTRAINT usuarios_perfis_pkey PRIMARY KEY (id),
    FOREIGN KEY (usuario_id_usuario) REFERENCES usuarios(id_usuario),
    FOREIGN KEY (perfis_id_perfil) REFERENCES perfis(id_perfil)
);