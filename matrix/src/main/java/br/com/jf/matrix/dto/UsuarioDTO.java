package br.com.jf.matrix.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UsuarioDTO {

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "Formato de e-mail inválido")
    private String email;

    @NotBlank(message = "O CPF é obrigatório")
    @Size(min = 11, max = 11, message = "O CPF deve ter 11 caracteres")
    private String cpf;

    private String rg;
    private LocalDate dataNascimento;
    private String genero;
    private String celular;

    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
    private String senha;

    @Valid
    private EnderecoDTO endereco;
}
