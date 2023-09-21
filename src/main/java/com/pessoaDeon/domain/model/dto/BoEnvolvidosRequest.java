package com.pessoaDeon.domain.model.dto;

import lombok.Data;

@Data
public class BoEnvolvidosRequest {
    private BoDto bo;
    private EnvolvidosRequestDto envolvidos;
}
