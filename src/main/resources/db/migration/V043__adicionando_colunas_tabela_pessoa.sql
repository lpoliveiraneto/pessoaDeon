ALTER TABLE pessoa ADD COLUMN numero_documento varchar(255) NOT NULL;

ALTER TABLE pessoa ADD COLUMN fk_tipo_documento int4 NOT NULL;

ALTER TABLE pessoa DROP COLUMN cpf CASCADE;