package br.com.jf.matrix.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EnderecoDTO {

    @NotBlank(message = "O CEP é obrigatório")
    @Size(min = 8, max = 9, message = "CEP inválido")
    private String cep;

    @NotBlank(message = "O logradouro é obrigatório")
    private String logradouro;

    @NotBlank(message = "O número é obrigatório")
    private String numero;

    private String bairro;
    private String municipio;
    private String estado;
    private String complemento;
}