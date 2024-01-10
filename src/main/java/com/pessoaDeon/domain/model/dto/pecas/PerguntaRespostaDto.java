package com.pessoaDeon.domain.model.dto.pecas;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PerguntaRespostaDto {
    private String blocoDescricao;
    private List<TipoPerguntaPecaDto> tipoPerguntaPeca;
}
