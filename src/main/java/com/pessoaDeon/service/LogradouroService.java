package com.pessoaDeon.service;

import com.pessoaDeon.model.Logradouro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class LogradouroService {

    @Autowired
    private RestTemplate http;
    private static final String viaCepUrl = "https://viacep.com.br/ws/";


    public Logradouro getLogradouroByCep(String cep){
        try{
            Logradouro buscaLogradouro = http.getForObject(this.getUriCEPAPI(cep), Logradouro.class);
            return buscaLogradouro;
        }catch (RuntimeException e){
            throw new IllegalStateException("Houve um erro ao tentar recuperar o endereço pelo cep: "+e.getMessage());
        }
    }

    private String getUriCEPAPI(String cep){
        cep = this.cepTratamento(cep);
        return this.viaCepUrl+cep+"/json/";
    }

    private String cepTratamento(String cep) {
        cep = cep.replace(".", "");
        cep = cep.replace("-", "");
        return cep;
    }
}
