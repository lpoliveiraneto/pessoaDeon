ALTER TABLE pessoa ADD FOREIGN KEY (fk_raca) REFERENCES raca(id_raca);    

ALTER TABLE pessoa ADD FOREIGN KEY (fk_escolaridade) REFERENCES escolaridade(id_escolaridade);

ALTER TABLE pessoa ADD FOREIGN KEY (fk_orientacao_sexual) REFERENCES orientacao_sexual(id_orientacao_sexual);

ALTER TABLE pessoa ADD FOREIGN KEY (fk_identidade_genero) REFERENCES identidade_genero(id_identidade_genero);

ALTER TABLE pessoa ADD FOREIGN KEY (fk_sexo) REFERENCES sexo(id_sexo);

ALTER TABLE pessoa ADD FOREIGN KEY (fk_estado_civil) REFERENCES estado_civil(id_estado_civil);

ALTER TABLE pessoa ADD FOREIGN KEY (fk_deficiencia) REFERENCES deficiencia(id_deficiencia);

ALTER TABLE pessoa ADD FOREIGN KEY (fk_profissao) REFERENCES profissao(id_profissao);