package com.pessoaDeon.api.controller.listas.bairro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pessoaDeon.domain.model.listas.Bairro;
import com.pessoaDeon.domain.service.BairroService;

@RestController
@RequestMapping("api/v1/lista/bairros")
public class BairroController {

	@Autowired
	private BairroService bairroService;
	
    @GetMapping("/{idCidade}")
    public List<Bairro> bairrosPorCidade(@PathVariable("idCidade") Integer idCidade){
    	return bairroService.findByBairroPorIdCidade(idCidade);
    }
    
    /**
     * @author lpjef
     *	Metodo que busca em uma API externa a lista de bairros por idCidade e os salva no banco de dados.
     */
    @GetMapping("/bairro/salvar/{idEstado}")
	public ResponseEntity<?> testeSalvarBairro(@PathVariable(name = "idEstado") Integer idEstado){
		var x = bairroService.listaBairroPorCidade(idEstado);
		return ResponseEntity.status(HttpStatus.OK).body(x);
	}
    
}

