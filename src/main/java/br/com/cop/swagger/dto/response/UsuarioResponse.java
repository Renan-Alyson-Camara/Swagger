package br.com.cop.swagger.dto.response;

import lombok.Data;
@Data
public class UsuarioResponse {
    private Long id;
    private String nome;
    private String email;
    private String senha;
}
