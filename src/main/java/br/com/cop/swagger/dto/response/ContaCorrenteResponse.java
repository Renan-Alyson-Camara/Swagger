package br.com.cop.swagger.dto.response;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class ContaCorrenteResponse {
    private Long id;
    private UsuarioResponse usuario;
    private String agencia;
    private String numeroConta;
    private BigDecimal saldo;
}
