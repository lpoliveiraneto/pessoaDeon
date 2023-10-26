package com.pessoaDeon.domain.model.dto.bo;

import com.pessoaDeon.domain.model.dto.EnvolvidosRequestDto;
import lombok.Data;

@Data
public class BoEnvolvidosRequest {
    private BoDto bo;
    private EnvolvidosRequestDto envolvidos;
}
