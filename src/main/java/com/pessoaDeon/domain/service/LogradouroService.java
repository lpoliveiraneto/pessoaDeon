package com.pessoaDeon.domain.service;

import com.pessoaDeon.domain.model.Logradouro;
import com.pessoaDeon.domain.repository.LogradouroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class LogradouroService {

    @Autowired
    private RestTemplate http;
    @Autowired
    private LogradouroRepository logradouroRepository;

    private final String viaCepUrl = "https://viacep.com.br/ws/";

    @Transactional
    public Logradouro getByCep(String cep){
        Optional<Logradouro> logradouro =  logradouroRepository.findByCep(cep);

        if(logradouro.isPresent()){
            return logradouro.get();
        }else {
            Logradouro logradouroViaCep = getLogradouroByCep(cep);
            if(!logradouroViaCep.getErro()) {
            	logradouroRepository.save(logradouroViaCep);
            }
            return logradouroViaCep;
        }
    }

    private Logradouro getLogradouroByCep(String cep){
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
