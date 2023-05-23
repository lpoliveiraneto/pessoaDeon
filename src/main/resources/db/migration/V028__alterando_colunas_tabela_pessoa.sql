
ALTER TABLE pessoa RENAME COLUMN deficiencia TO fk_deficiencia;
ALTER TABLE pessoa ALTER COLUMN fk_deficiencia TYPE int4 USING fk_deficiencia::int4;

ALTER TABLE pessoa RENAME COLUMN escolaridade TO fk_escolaridade;
ALTER TABLE pessoa ALTER COLUMN fk_escolaridade TYPE int4 USING fk_escolaridade::int4;

ALTER TABLE pessoa RENAME COLUMN identidade_genero TO fk_identidade_genero;
ALTER TABLE pessoa ALTER COLUMN fk_identidade_genero TYPE int4 USING fk_identidade_genero::int4;

ALTER TABLE pessoa RENAME COLUMN orientacao_sexual TO fk_orientacao_sexual;
ALTER TABLE pessoa ALTER COLUMN fk_orientacao_sexual TYPE int4 USING fk_orientacao_sexual::int4;

ALTER TABLE pessoa RENAME COLUMN sexo TO fk_sexo;
ALTER TABLE pessoa ALTER COLUMN fk_sexo TYPE int4 USING fk_sexo::int4;

ALTER TABLE pessoa RENAME COLUMN cor_pele TO fk_raca;
ALTER TABLE pessoa ALTER COLUMN fk_raca TYPE int4 USING fk_raca::int4;

ALTER TABLE pessoa ADD COLUMN fk_estado_civil int4;

ALTER TABLE pessoa ADD COLUMN fk_profissao int4;

