package com.pessoaDeon.api.controller.listas.identidadeGenero;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pessoaDeon.domain.model.IdentidadeGenero;
import com.pessoaDeon.domain.repository.listas.identidadeGenero.IdentidadeGeneroRepository;

@RestController
@RequestMapping("api/v1/identidadeGenero")
public class IdentidadeGeneroController {

	@Autowired
	private IdentidadeGeneroRepository identidadeGeneroRepository;
//	@GetMapping
//    public List<EnumToObject> listarDeficiencias(){
//        List<IdentidadeGenero> listaEnum = Arrays.asList(IdentidadeGenero.values());
//        Map<String, String> listaIdentidadeGenero = new HashMap<>();
//        listaEnum.forEach(lista -> {
//        	listaIdentidadeGenero.put(lista.toString(), lista.getDescricao());
//        });
//        
//        List<EnumToObject> lista = new ArrayList<>();
//        for(String chave : listaIdentidadeGenero.keySet()) {
//        	EnumToObject novoEnum = new EnumToObject();
//        	novoEnum.setKey(chave);
//        	novoEnum.setValue(listaIdentidadeGenero.get(chave));
//        	lista.add(novoEnum);
//        }
//        return lista;
//        
//    }
	@GetMapping("/lista")
	public List<IdentidadeGenero> listarIdentidadeGenero(){
		return identidadeGeneroRepository.findAll();
	}
}
