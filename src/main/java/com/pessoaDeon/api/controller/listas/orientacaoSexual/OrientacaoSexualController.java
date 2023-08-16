package com.pessoaDeon.api.controller.listas.orientacaoSexual;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pessoaDeon.domain.model.OrientacaoSexual;
import com.pessoaDeon.domain.repository.listas.orientacaoSexual.OrientacaoSexualRepository;

@RestController
@RequestMapping("api/v1/lista/orientacaoSexual")
public class OrientacaoSexualController {

	@Autowired
	private OrientacaoSexualRepository oRepository;
//	@GetMapping
//    public List<EnumToObject> listarDeficiencias(){
//        List<OrientacaoSexual> listaEnum = Arrays.asList(OrientacaoSexual.values());
//        Map<String, String> listaOrientacaoSexual = new HashMap<>();
//        listaEnum.forEach(d -> {
//        	listaOrientacaoSexual.put(d.toString(), d.getDescricao());
//        });
//
//        List<EnumToObject> lista = new ArrayList<>();
//        for(String chave : listaOrientacaoSexual.keySet()) {
//        	EnumToObject novoEnum = new EnumToObject();
//        	novoEnum.setKey(chave);
//        	novoEnum.setValue(listaOrientacaoSexual.get(chave));
//        	lista.add(novoEnum);
//        }
//        return lista;
//        
//    }
	
//	@GetMapping("/lista")
	@GetMapping
	public List<OrientacaoSexual> listarOrientacaoSexual() {
		return oRepository.findAll();
	}
}
