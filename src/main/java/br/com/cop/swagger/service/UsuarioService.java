package br.com.cop.swagger.service;

import br.com.cop.swagger.dto.request.UsuarioRequest;
import br.com.cop.swagger.model.Perfil;
import br.com.cop.swagger.model.Usuario;
import br.com.cop.swagger.repository.PerfilRepository;
import br.com.cop.swagger.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final PerfilRepository perfilRepository;
    private final ModelMapper modelMapper;
    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, PerfilRepository perfilRepository, ModelMapper modelMapper) {
        this.usuarioRepository = usuarioRepository;
        this.perfilRepository = perfilRepository;
        this.modelMapper = modelMapper;
    }

    public Usuario cadastrar(UsuarioRequest form) {
        Usuario usuarioConvertido = form.converter();
        Usuario usuario = modelMapper.map(usuarioConvertido, Usuario.class);
        Perfil perfil = perfilRepository.findByNome("ROLE_USER");
        usuario.addRole(perfil);
        usuarioRepository.save(usuario);
        return usuario;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(s);
        if (usuario.isPresent()){
            return usuario.get();
        }
        throw new UsernameNotFoundException("User Not Found");
    }

    public List<Usuario> buscarTudo() {
        return usuarioRepository.findAll();
    }
}
