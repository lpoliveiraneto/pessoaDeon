package com.pessoaDeon.api.controller.listas.corRaca;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pessoaDeon.domain.model.Raca;
import com.pessoaDeon.domain.repository.listas.raca.RacaRepository;

@RestController
@RequestMapping("api/v1/racas")
public class CorRacaController {

	@Autowired
	private RacaRepository racaRepository;
	
//    @GetMapping
//    public List<EnumToObject> listarPele(){
//        List<CorPele> listaEnum = Arrays.asList(CorPele.values());
//        Map<String, String > listaCorPele = new HashMap<>();
//        listaEnum.forEach(p ->{
//            listaCorPele.put(p.toString(), p.getDescricao());
//        });
//        List<EnumToObject> lista = new ArrayList<>();
//        for(String chave : listaCorPele.keySet()) {
//        	EnumToObject novoEnum = new EnumToObject();
//        	novoEnum.setKey(chave);
//        	novoEnum.setValue(listaCorPele.get(chave));
//        	lista.add(novoEnum);
//        }
//        return lista;
//    }
	
	@GetMapping("/lista")
	public List<Raca> listarRacaCor(){
		return racaRepository.findAll() ;
	}
}
