package br.com.cop.swagger.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String senha;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "Usuario_Perfil", joinColumns = {
            @JoinColumn(name = "usuario_id", referencedColumnName = "id")}
            , inverseJoinColumns = {
            @JoinColumn(name = "perfil_id", referencedColumnName = "id")
    })
    private List<Perfil> perfis = new ArrayList<>();

    public Usuario(String nome, String email, String senhaCriptografada) {
        this.nome = nome;
        this.email = email;
        this.senha = senhaCriptografada;
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
        return this.nome;
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

    public void addRole(Perfil perfil) {
        this.perfis.add(perfil);
    }
}
