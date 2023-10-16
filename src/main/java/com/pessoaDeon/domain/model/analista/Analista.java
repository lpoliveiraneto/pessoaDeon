package com.pessoaDeon.domain.model.analista;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pessoaDeon.domain.model.pessoa.Pessoa;
import com.pessoaDeon.domain.model.security.Perfil;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Analista implements UserDetails{
    /**
	 * 
	 */
	private static final long serialVersionUID = -4575354473625350361L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAnalista;

    @OneToOne
    @JoinColumn(name = "fk_pessoa")
    private Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name = "fk_cargo")
    private Cargo cargo;

    @ManyToOne
    @JoinColumn(name = "fk_funcao")
    private Funcao funcao;

    private String matricula;

    @Column(name = "fk_func_sigma")
    private Integer funcionarioSigma;

    @JsonFormat(shape=JsonFormat.Shape.STRING, locale = "pt-BR", timezone = "Brazil/East", pattern = "yyyy-MM-dd HH:mm:ss")
//    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime data_cadastro;

    private Boolean status = false;
    
    private String login;
    
    private String senha;
    
    @JsonFormat(shape=JsonFormat.Shape.STRING, locale = "pt-BR", timezone = "Brazil/East", pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime data_alteracao;
    
    private String assinatura;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "analista_perfis")
    private List<Perfil> perfis = new ArrayList<>();

    public void adicionarPerfil(Perfil perfil){
        perfis.add(perfil);
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.perfis;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
}
