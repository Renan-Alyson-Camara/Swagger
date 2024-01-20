package br.com.cop.swagger.controller;

import br.com.cop.swagger.model.Perfil;
import br.com.cop.swagger.repository.PerfilRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/perfil")
@SecurityRequirement(name = "Bearer Authentication")
public class PerfilController {

    private final PerfilRepository perfilRepository;
    @Autowired
    public PerfilController(PerfilRepository perfilRepository) {
        this.perfilRepository = perfilRepository;
    }

    @PostMapping
    public ResponseEntity<Perfil> cadastrar(@RequestBody Perfil perfil){
        return new ResponseEntity<>(perfilRepository.save(perfil), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<Perfil>> buscar(){
        return new ResponseEntity<>(perfilRepository.findAll(), HttpStatus.OK);
    }
}
