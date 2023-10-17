package com.pessoaDeon.domain.model.security;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

@Table(name="perfis")
@Entity(name="Perfil")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="idPerfil")
public class Perfil implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_perfil")
    private Long id;
    
    @Column(name="descricao")
    private String nome;

    @Override
    public String getAuthority() {
        return nome;
    }
}
