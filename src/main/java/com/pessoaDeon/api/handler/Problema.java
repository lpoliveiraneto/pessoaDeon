package com.pessoaDeon.api.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Problema {
    private LocalDateTime data;
    private String message;
    private String exception;
}
