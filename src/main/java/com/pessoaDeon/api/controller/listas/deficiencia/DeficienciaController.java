package com.pessoaDeon.api.controller.listas.deficiencia;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pessoaDeon.domain.model.Deficiencia;
import com.pessoaDeon.domain.repository.listas.deficiencia.DeficienciaRepository;

@RestController
@RequestMapping("api/v1/deficiencias")
public class DeficienciaController {
		
	@Autowired
	private DeficienciaRepository deficienciaRepository;
	
//    @GetMapping
//    public List<EnumToObject> listarDeficiencias(){
//        List<Deficiencia> listaEnum = Arrays.asList(Deficiencia.values());
//        Map<String, String> listaDeficiencias = new HashMap<>();
//        listaEnum.forEach(d -> {
//            listaDeficiencias.put(d.toString(), d.getDescricao());
//        });
//        List<EnumToObject> lista = new ArrayList<>();
//        for(String key : listaDeficiencias.keySet()) {
//        	EnumToObject to = new EnumToObject();
//        	to.setKey(key);
//        	to.setValue(listaDeficiencias.get(key));
//        	lista.add(to);
//        }
//        return lista;
//    }
	
	@GetMapping("/lista")
	public List<Deficiencia> listarDeficiencias(){
		return deficienciaRepository.findAll();
	}
}
