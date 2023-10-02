ALTER TABLE public.tipo_participacao ADD valor varchar NULL;

ALTER TABLE public.endereco_local_fato ALTER COLUMN numero_local TYPE varchar USING numero_local::varchar;

ALTER TABLE public.endereco_envolvido ALTER COLUMN numero_local TYPE varchar USING numero_local::varchar;

ALTER TABLE public.usuarios ADD fk_pessoa int4 NOT NULL;

ALTER TABLE public.pessoa DROP COLUMN usuario_id;

ALTER TABLE public.bo_deon ALTER COLUMN status TYPE varchar(5) USING status::varchar;

