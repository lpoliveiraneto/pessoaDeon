CREATE SCHEMA listas AUTHORIZATION postgres;

ALTER TABLE public.deficiencia SET SCHEMA listas;
ALTER TABLE public.escolaridade SET SCHEMA listas;
ALTER TABLE public.estado_civil SET SCHEMA listas;
ALTER TABLE public.identidade_genero SET SCHEMA listas;
ALTER TABLE public.orientacao_sexual SET SCHEMA listas;
ALTER TABLE public.profissao SET SCHEMA listas;
ALTER TABLE public.raca SET SCHEMA listas;
ALTER TABLE public.sexo SET SCHEMA listas;
ALTER TABLE public.tipolocal SET SCHEMA listas;

ALTER TABLE public.pais SET SCHEMA listas;
ALTER TABLE public.estado SET SCHEMA listas;
ALTER TABLE public.cidade SET SCHEMA listas;
ALTER TABLE public.bairro SET SCHEMA listas;



