package br.com.cop.swagger.service;

import br.com.cop.swagger.dto.request.UsuarioRequest;
import br.com.cop.swagger.dto.response.ContaCorrenteResponse;
import br.com.cop.swagger.dto.response.SaldoResponse;
import br.com.cop.swagger.model.ContaCorrente;
import br.com.cop.swagger.dto.request.ContaCorrenteRequest;
import br.com.cop.swagger.model.Perfil;
import br.com.cop.swagger.model.Usuario;
import br.com.cop.swagger.repository.ContaRepository;
import br.com.cop.swagger.repository.PerfilRepository;
import br.com.cop.swagger.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Random;

@Service
public class ContaService {
    private final ContaRepository contaRepository;
    private final UsuarioRepository usuarioRepository;
    private final PerfilRepository perfilRepository;
    private final ModelMapper mapper;

    @Autowired
    public ContaService(ContaRepository contaRepository, UsuarioRepository usuarioRepository, PerfilRepository perfilRepository, ModelMapper mapper) {
        this.contaRepository = contaRepository;
        this.usuarioRepository = usuarioRepository;
        this.perfilRepository = perfilRepository;
        this.mapper = mapper;
    }

    public ContaCorrenteResponse criarConta(ContaCorrenteRequest conta) {
        int random = new Random().nextInt(0, 99999999);
        Usuario usuarioCadastrado = cadastrarUsuario(conta.getUsuario());
        ContaCorrente contaCorrente = ContaCorrente
                .builder()
                .usuario(usuarioCadastrado)
                .agencia("0001")
                .numeroConta(random + "-3")
                .saldo(BigDecimal.TEN)
                .build();
        contaRepository.save(contaCorrente);
        return mapper.map(contaCorrente, ContaCorrenteResponse.class);
    }

    public SaldoResponse buscarSaldo(Long id) {
        ContaCorrente contaCorrente = contaRepository.findById(id).orElseThrow(() -> new RuntimeException("Id não encontrado"));
        return mapper.map(contaCorrente, SaldoResponse.class);
    }

    public String deletar(Long id) {
        contaRepository.deleteById(id);
        return "Conta deletado com sucesso!";
    }
    private Usuario cadastrarUsuario(UsuarioRequest form) {
        Usuario usuarioConvertido = form.converter();
        Usuario usuario = mapper.map(usuarioConvertido, Usuario.class);
        Perfil perfil = perfilRepository.findByNome("ROLE_USER");
        usuario.addRole(perfil);
        usuarioRepository.save(usuario);
        return usuario;
    }
}
