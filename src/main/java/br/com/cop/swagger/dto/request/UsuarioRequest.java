package br.com.cop.swagger.dto.request;

import br.com.cop.swagger.model.Usuario;
import br.com.cop.swagger.repository.UsuarioRepository;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotBlank;

@Data
public class UsuarioRequest {
    @NotBlank
    private String nome;
    @NotBlank
    private String email;
    @NotBlank
    private String senha;

    public Usuario converter() {
        BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
        String senhaCriptografada = bCrypt.encode(senha);
        return new Usuario(nome,email,senhaCriptografada);
    }


    public boolean isRepeatVerify(UsuarioRepository usuarioRepository) {
        return usuarioRepository.findByEmail(email).isPresent();
    }
}
