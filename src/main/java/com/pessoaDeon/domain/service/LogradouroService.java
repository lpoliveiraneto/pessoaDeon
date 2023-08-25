package com.pessoaDeon.domain.service;

import com.pessoaDeon.domain.model.endereco.Logradouro;

import com.pessoaDeon.domain.model.dto.LogradouroDto;
import com.pessoaDeon.domain.repository.endereco.LogradouroRepository;
import com.pessoaDeon.domain.repository.listas.estado.EstadoRepository;


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
    
    @Autowired
    private BairroService bairroService;
    
    @Autowired
    private EstadoRepository estadoRepository;
    
    @Autowired
    private CidadeService cidadeService;

    private final String viaCepUrl = "https://viacep.com.br/ws/";

    @Transactional
    public Logradouro getByCep(String cep){
        Optional<Logradouro> logradouro =  logradouroRepository.findByCepAndCepDesconhecidoFalse(cep);

        if(logradouro.isPresent()){
            return logradouro.get();
        }else {
            Logradouro logradouroViaCep = getLogradouroByCep(cep);
            if(!logradouroViaCep.getErro() && logradouroViaCep.getCep() != null) {
            	logradouroRepository.save(logradouroViaCep);
            }
            return logradouroViaCep;
        }
    }

//    private Logradouro getLogradouroByCep(String cep){
//        try{
//            Logradouro buscaLogradouro = http.getForObject(this.getUriCEPAPI(cep), Logradouro.class);
//            return buscaLogradouro;
//        }catch (RuntimeException e){
//            throw new IllegalStateException("Houve um erro ao tentar recuperar o endereço pelo cep");
//        }
//    }
    
    private Logradouro getLogradouroByCep(String cep){
    	try{
    		LogradouroDto buscaLogradouro = http.getForObject(this.getUriCEPAPI(cep), LogradouroDto.class);
    		Logradouro logradouro = new Logradouro();
    		if (!buscaLogradouro.getErro()) {
	    		logradouro.setCep(buscaLogradouro.getCep());
	    		logradouro.setComplemento(buscaLogradouro.getComplemento());
	    		logradouro.setIbge(buscaLogradouro.getIbge());
	    		logradouro.setLogradouro(buscaLogradouro.getLogradouro());
	    		logradouro.setEstado(estadoRepository.findByUf(buscaLogradouro.getUf()));
	    		logradouro.setCidade(cidadeService.findByCidadeAndCodigoIbge(Integer.parseInt(buscaLogradouro.getIbge())));
	    		logradouro.setBairro(bairroService.findByBairroPorIdCidadeNome(logradouro.getCidade().getIdCidade(), buscaLogradouro.getBairro()));
    		}
    		return logradouro;
    	}catch (RuntimeException e){
    		throw new IllegalStateException("Houve um erro ao tentar recuperar o endereço pelo cep");
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

    @Transactional
	public Logradouro save(Logradouro logradouro) {
    	logradouro.setCepDesconhecido(true);
		return logradouroRepository.save(logradouro);
	}
    
}
