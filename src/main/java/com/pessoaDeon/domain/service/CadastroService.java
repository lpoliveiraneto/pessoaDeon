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

import com.pessoaDeon.domain.model.Bairro;
import com.pessoaDeon.domain.model.Cidade;
import com.pessoaDeon.domain.model.Email;
import com.pessoaDeon.domain.model.Endereco;
import com.pessoaDeon.domain.model.Logradouro;
import com.pessoaDeon.domain.model.Pessoa;
import com.pessoaDeon.domain.model.Telefone;
import com.pessoaDeon.domain.model.dto.BairroDto;
import com.pessoaDeon.domain.model.dto.CadastroRequestDto;

@Service
public class CadastroService {

	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
    private LogradouroService logradouroService;
    
    @Autowired
    private EnderecoService enderecoService;
    
    @Autowired
    private ContatoService contatoService;
    
    @Autowired
    private EmailService emailService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Transactional
	public Pessoa salvar(CadastroRequestDto cadastroRequestDto){
		Pessoa pessoa = modelMapper.map(cadastroRequestDto, Pessoa.class);
		var pessoaSave = pessoaService.salvarPessoaDeon(pessoa);
		
		if(pessoaSave != null) {
			Logradouro logradouro = modelMapper.map(cadastroRequestDto, Logradouro.class);
			if(logradouro.getCep() != null) {
				var logradouroSave = logradouroService.getByCep(logradouro.getCep());
				this.salvarEndereco(cadastroRequestDto, pessoaSave, logradouroSave);
			}
			this.salvarTelefone(cadastroRequestDto, pessoaSave);
			this.salvarEmail(cadastroRequestDto, pessoaSave);
		}
		return pessoaSave;
	}
	
	@Transactional
	private Endereco salvarEndereco(CadastroRequestDto dto, Pessoa pessoa, Logradouro logradouro) {
		Endereco endereco = new Endereco();
		endereco.setAtual(true);
		endereco.setNumero(dto.getNumero());
		endereco.setReferencia(dto.getReferencia());
		endereco.setPessoa(pessoa);
		endereco.setLogradouro(logradouro);
		return enderecoService.salvarEndereco(endereco);
	}
	
	@Transactional
	private Telefone salvarTelefone(CadastroRequestDto requestDto, Pessoa pessoa) {
		Telefone telefone = new Telefone();
		telefone.setTelefone(requestDto.getTelefone());
		telefone.setAtual(true);
		telefone.setPessoa(pessoa);
		if (requestDto.getTipoWhatsapp() != null) {
			telefone.setTipowhatsapp(requestDto.getTipoWhatsapp());
		} else {
			telefone.setTipowhatsapp(false);
		}
		if (requestDto.getTipoTelegram() != null) {
			telefone.setTipotelegram(requestDto.getTipoTelegram());
		} else {
			telefone.setTipotelegram(false);
		}
		var telefoneSave = contatoService.saveContato(telefone);
		return telefoneSave;
	}
	
	@Transactional
	private Email salvarEmail(CadastroRequestDto dto, Pessoa pessoa) {
		Email email = new Email();
		email.setEmail(dto.getEmail());
		email.setAtual(true);
		email.setPessoa(pessoa);
		var emailSave = emailService.salvarEmail(email);
		return emailSave;
	}

}
