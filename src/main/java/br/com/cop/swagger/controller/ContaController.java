package br.com.cop.swagger.controller;

import br.com.cop.swagger.dto.response.ContaCorrenteResponse;
import br.com.cop.swagger.dto.response.SaldoResponse;
import br.com.cop.swagger.model.ContaCorrente;
import br.com.cop.swagger.dto.request.ContaCorrenteRequest;
import br.com.cop.swagger.service.ContaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/conta")
public class ContaController {

    private final ContaService contaService;
    @Autowired
    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @PostMapping
    public ResponseEntity<ContaCorrenteResponse> criarConta(@RequestBody @Valid ContaCorrenteRequest conta){
        return new ResponseEntity<>(contaService.criarConta(conta), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<SaldoResponse> buscarSaldo(@PathVariable Long id){
        return new ResponseEntity<>(contaService.buscarSaldo(id), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<String> deletarUsuario(@PathVariable Long id){
        return new ResponseEntity<>(contaService.deletar(id), HttpStatus.OK);
    }
}
