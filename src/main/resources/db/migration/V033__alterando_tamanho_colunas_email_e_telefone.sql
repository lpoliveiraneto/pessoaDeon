ALTER TABLE telefone ALTER COLUMN telefone TYPE varchar(15) USING telefone::varchar;

ALTER TABLE email ALTER COLUMN email TYPE varchar(255) USING email::varchar;