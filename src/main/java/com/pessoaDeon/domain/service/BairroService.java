package com.pessoaDeon.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.pessoaDeon.domain.model.listas.Bairro;
import com.pessoaDeon.domain.model.listas.Cidade;
import com.pessoaDeon.domain.model.dto.BairroDto;
import com.pessoaDeon.domain.repository.listas.bairro.BairroRepository;

@Service
public class BairroService {
	
	@Autowired
	private BairroRepository bairroRepository;
	
	@Autowired
    private CidadeService cidadeService;
	
	@Autowired
    private RestTemplate http;
	
	@Autowired
	private ModelMapper modelMapper;

	@Transactional
	public Bairro save(Bairro bairro) {
		return bairroRepository.save(bairro);
	} 
	
	public List<Bairro> findByBairroPorIdCidade(Integer idCidade) {
		return bairroRepository.findByCidadeIdCidade(idCidade);
	}

	public Bairro findByBairroPorIdCidadeNome(Integer idCidade, String descricao) {
		return bairroRepository.findByCidadeIdCidadeAndDescricao(idCidade, descricao);
	}
	/**
	 * @author Jeff Andrade
	 *
	 *	Este conjunto de metodos faz um get para API enderecos.metheora.com
	 *	e retorna uma lista de bairros por idCidade, e em seguida
	 *	os salva no banco da dados.
	 *
	 */
	
	private final String apiUrl = "http://enderecos.metheora.com/api/cidade/";
	
	private String getUriCEPAPI(Integer idCidade){
        return this.apiUrl+idCidade+"/bairros/";
    }
	
	private List<BairroDto> getBairroByIdCidade(Integer idCidade) {
		List<Object> listaBairrosApi = new ArrayList<>(); 
		try {
				ResponseEntity<List<Object>> response = http.exchange(
						getUriCEPAPI(idCidade), 
						HttpMethod.GET,
						null,
						new ParameterizedTypeReference<List<Object>>() {});
				if(response != null && response.hasBody()){
					listaBairrosApi = response.getBody();
				}
			} catch (RestClientException e) {
				e.printStackTrace();
			}

		List<BairroDto> lista = new ArrayList<>();
		listaBairrosApi.forEach(l -> {
			BairroDto bairroDto = modelMapper.map(l, BairroDto.class);
			lista.add(bairroDto);
			
		});
		return lista; 
		
	}

	public Object listaBairroPorCidade(Integer idEstado) {
		List<Cidade> listaCidades = cidadeService.findByEstadoIdEstado(idEstado);
		
		List<BairroDto> listaBairrosDto = new ArrayList<>();
		
		listaCidades.forEach(l -> {
			listaBairrosDto.addAll(getBairroByIdCidade(l.getIdCidade()));
		});
		
		listaBairrosDto.forEach(b -> {
			Bairro bairro = new Bairro();
			bairro.setIdBairroApi(b.getId());
			bairro.setDescricao(b.getNome());
			bairro.setCidade(cidadeService.findByCidadeAndCodigoIbge(Integer.parseInt(b.getCidade().getCodigoIBGE())));
			this.save(bairro);
		});
		return listaBairrosDto;
	}

}
