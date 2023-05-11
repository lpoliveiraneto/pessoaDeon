package com.pessoaDeon.domain.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pessoaDeon.domain.model.Email;
import com.pessoaDeon.domain.model.Endereco;
import com.pessoaDeon.domain.model.Logradouro;
import com.pessoaDeon.domain.model.Pessoa;
import com.pessoaDeon.domain.model.Telefone;
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
		endereco.setAtual(dto.getEnderecoAtual());
		endereco.setNumero(dto.getNumero());
		endereco.setReferencia(dto.getReferencia());
		endereco.setPessoa(pessoa);
		endereco.setLogradouro(logradouro);
		return enderecoService.salvarEndereco(endereco);
	}
	
	@Transactional
	private Telefone salvarTelefone(CadastroRequestDto cadastroRequestDto, Pessoa telefonePessoa) {
		Telefone telefone = new Telefone();
		telefone.setTelefone(cadastroRequestDto.getTelefone());
		telefone.setAtual(cadastroRequestDto.getTelefoneAtual());
		telefone.setPessoa(telefonePessoa);
		var telefoneSave = contatoService.saveContato(telefone);
		return telefoneSave;
	}
	
	@Transactional
	private Email salvarEmail(CadastroRequestDto dto, Pessoa pessoa) {
//		Email email = modelMapper.map(dto, Email.class);
		Email email = new Email();
		email.setEmail(dto.getEmail());
		email.setAtual(dto.getEmailAtual());
		email.setPessoa(pessoa);
		var emailSave = emailService.salvarEmail(email);
		return emailSave;
	}
}
