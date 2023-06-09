package com.pessoaDeon.api.controller.listas.escolaridade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pessoaDeon.domain.model.Escolaridade;
import com.pessoaDeon.domain.repository.listas.escolaridade.EscolaridadeRepository;


@RestController
@RequestMapping("api/v1/escolaridade")
public class EscolaridadeController {

	@Autowired
	private EscolaridadeRepository escolaridadeRepository;
//	@GetMapping
//    public List<EnumToObject> listarEscolaridade(){
//        List<Escolaridade> list = Arrays.asList(Escolaridade.values());
//        Map<String, String> listaEscolaridade = new HashMap<>();
//        list.forEach(lista -> {
//        	listaEscolaridade.put(lista.toString(), lista.getDescricao());
//        });
//        List<EnumToObject> lista = new ArrayList<>();
//        for(String chave : listaEscolaridade.keySet()) {
//        	EnumToObject novoEnum = new EnumToObject();
//        	novoEnum.setKey(chave);
//        	novoEnum.setValue(listaEscolaridade.get(chave));
//        	lista.add(novoEnum);
//        }
//        return lista;
//    }
	
	@GetMapping("/lista")
	public List<Escolaridade> listarEscolaridade(){
		return escolaridadeRepository.findAll();
	}
}
