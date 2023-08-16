package com.pessoaDeon.api.controller.listas.estadoCivil;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pessoaDeon.domain.model.EstadoCivil;
import com.pessoaDeon.domain.repository.listas.estadoCivil.EstadoCivilRepository;

@RestController
@RequestMapping("api/v1/lista/estadoCivil")
public class EstadoCivilController {
	
	@Autowired
	private EstadoCivilRepository estadoCivilRepository;

//	@GetMapping
//	public List<EnumToObject> listarEstadoCivil(){
//		List<EstadoCivil> list = Arrays.asList(EstadoCivil.values());
//		Map<String, String> listaEstadoCivil = new HashMap<>();
//		list.forEach(lista -> {
//			listaEstadoCivil.put(lista.toString(), lista.getDescricao());
//		});
//		
//		List<EnumToObject> lista = new ArrayList<>();
//        for(String chave : listaEstadoCivil.keySet()) {
//        	EnumToObject novoEnum = new EnumToObject();
//        	novoEnum.setKey(chave);
//        	novoEnum.setValue(listaEstadoCivil.get(chave));
//        	lista.add(novoEnum);
//        }
//        return lista;
//	}
	
//	@GetMapping("/lista")
	@GetMapping
	public List<EstadoCivil> listarEstadoCivil(){
		return estadoCivilRepository.findAll();
	}
	
}
