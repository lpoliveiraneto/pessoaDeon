ALTER TABLE natureza_deon RENAME COLUMN id_natureza_deon TO id_natureza;
ALTER TABLE natureza_deon ADD COLUMN codigo varchar NOT NULL;
ALTER TABLE natureza_deon ADD COLUMN status boolean NOT NULL;
ALTER TABLE natureza_deon ALTER COLUMN tipo_natureza TYPE varchar(50);
