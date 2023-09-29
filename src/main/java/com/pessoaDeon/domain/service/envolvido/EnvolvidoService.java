package com.pessoaDeon.domain.service.envolvido;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pessoaDeon.domain.exception.EnvolvidoNotFoundException;
import com.pessoaDeon.domain.model.dto.EnvolvidoDto;
import com.pessoaDeon.domain.model.envolvido.EnderecoEnvolvido;
import com.pessoaDeon.domain.model.envolvido.Envolvido;
import com.pessoaDeon.domain.model.envolvido.Envolvimento;
import com.pessoaDeon.domain.repository.envolvido.EnvolvidoRepository;
import com.pessoaDeon.domain.repository.envolvido.EnvolvimentoRepository;
import com.pessoaDeon.domain.service.PessoaService;
import java.util.List;

@Service
public class EnvolvidoService {

    @Autowired
    private EnvolvidoRepository envolvidoRepository;

    @Autowired
    private PessoaService pessoaService;
    
    @Autowired
    private EnvolvimentoRepository envolvimentoRepository;
    
//    @Autowired
//    private EntityManager entityManager;

    public Envolvido buscarEnvolvido(Integer idEnvolvido){
        Envolvido envolvido = envolvidoRepository.findById(idEnvolvido).orElseThrow(() ->
                new EnvolvidoNotFoundException("Envolvido não Encontrado = "  + idEnvolvido));
        return envolvido;
    }

    @Transactional
    public Envolvido salvarEnvolvido(EnvolvidoDto envolvidoDto){
        var envolvido = mapearEnvolvido (envolvidoDto);
        return envolvidoRepository.save(envolvido);
    }

    private Envolvido mapearEnvolvido(EnvolvidoDto envolvidoDto) {
         Envolvido envolvido = new Envolvido();

         envolvido.setNome(envolvidoDto.getNome() != null ? envolvidoDto.getNome() : null);
         envolvido.setNomeMae(envolvidoDto.getNomeMae() != null ? envolvidoDto.getNomeMae() : null);
         envolvido.setNomePai(envolvidoDto.getNomePai() != null ? envolvidoDto.getNomePai() : null);
         envolvido.setAlcunha(envolvidoDto.getAlcunha() != null ? envolvidoDto.getAlcunha() : null);
         envolvido.setDataNascimento(envolvidoDto.getDataNascimento() != null ? envolvidoDto.getDataNascimento() : null);
         envolvido.setSexo(envolvidoDto.getSexo() != null ? envolvidoDto.getSexo() : null);
         envolvido.setNomeSocial(envolvidoDto.getNomeSocial() != null ? envolvidoDto.getNomeSocial() : null);
         envolvido.setOrientacaoSexual(envolvidoDto.getOrientacaoSexual() != null ? envolvidoDto.getOrientacaoSexual() : null);
         envolvido.setIdentidadeGenero(envolvidoDto.getIdentidadeGenero() != null ? envolvidoDto.getIdentidadeGenero() : null);
         envolvido.setDeficiencia(envolvidoDto.getDeficiencia() != null ? envolvidoDto.getDeficiencia() : null);
         envolvido.setCorPele(envolvidoDto.getCorPele() != null ? envolvidoDto.getCorPele() : null);
         envolvido.setPais(envolvidoDto.getPais() != null ? envolvidoDto.getPais() : null) ;
         envolvido.setEstadoNaturalidade(envolvidoDto.getEstadoNaturalidade() != null ? envolvidoDto.getEstadoNaturalidade() : null);
         envolvido.setCidadeNaturalidade(envolvidoDto.getCidadeNaturalidade() != null ? envolvidoDto.getCidadeNaturalidade() : null);
         envolvido.setEmail(envolvidoDto.getEmail() != null ? envolvidoDto.getEmail() : null);
         envolvido.setTelefone(envolvidoDto.getTelefone() != null ? envolvidoDto.getTelefone() : null);
         envolvido.setProfissao(envolvidoDto.getProfissao() != null ? envolvidoDto.getProfissao() : null);
         envolvido.setEstadoCivil(envolvidoDto.getEstadoCivil() != null ? envolvidoDto.getEstadoCivil() : null);
         envolvido.setEscolaridade(envolvidoDto.getEscolaridade() != null ? envolvidoDto.getEscolaridade() : null);
         envolvido.setNumeroDocumento(envolvidoDto.getNumeroDocumento() != null ? envolvidoDto.getNumeroDocumento() : null);

         if(envolvidoDto.getFk_pessoa() != null){
             envolvido.setPessoa(pessoaService.buscarPessoa(envolvidoDto.getFk_pessoa()).get());
         }

         if(envolvidoDto.getEnderecoEnvolvido() != null){
             EnderecoEnvolvido endereco = new EnderecoEnvolvido();
             endereco.setCep(envolvidoDto.getEnderecoEnvolvido().getCep() != null ? envolvidoDto.getEnderecoEnvolvido().getCep() : null);
             endereco.setComplemento(envolvidoDto.getEnderecoEnvolvido().getComplemento() != null ? envolvidoDto.getEnderecoEnvolvido().getComplemento() : null);
             endereco.setNumeroLocal(envolvidoDto.getEnderecoEnvolvido().getNumero()!= null ? envolvidoDto.getEnderecoEnvolvido().getNumero() : null);
             endereco.setReferencia(envolvidoDto.getEnderecoEnvolvido().getReferencia() != null ? envolvidoDto.getEnderecoEnvolvido().getReferencia() : null);
             endereco.setBairro(envolvidoDto.getEnderecoEnvolvido().getBairro() != null ? envolvidoDto.getEnderecoEnvolvido().getBairro() : null);
             endereco.setCidade(envolvidoDto.getEnderecoEnvolvido().getCidade() != null ? envolvidoDto.getEnderecoEnvolvido().getCidade() : null);
             endereco.setEstado(envolvidoDto.getEnderecoEnvolvido().getEstado() != null ? envolvidoDto.getEnderecoEnvolvido().getEstado() : null);
             endereco.setTipoLocal(envolvidoDto.getEnderecoEnvolvido().getTipoLocal() != null ? envolvidoDto.getEnderecoEnvolvido().getTipoLocal() : null);
             envolvido.setEnderecoEnvolvido(endereco);
         }

         return envolvido;
    }
    
    public List<Envolvimento> getEnvolvimentosBo(Integer idBo) {
    	return envolvimentoRepository.findByNaturezaBoBoIdBo(idBo);
    }
}
