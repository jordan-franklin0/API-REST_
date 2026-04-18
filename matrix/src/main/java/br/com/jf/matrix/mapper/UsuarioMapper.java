package br.com.jf.matrix.mapper;

import br.com.jf.matrix.dto.EnderecoDTO;
import br.com.jf.matrix.dto.UsuarioDTO;
import br.com.jf.matrix.model.entity.Endereco;
import br.com.jf.matrix.model.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public Usuario toEntity(UsuarioDTO dto) {
        if (dto == null) return null;

        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setCpf(dto.getCpf());
        usuario.setRg(dto.getRg());
        usuario.setDataNascimento(dto.getDataNascimento());
        usuario.setGenero(dto.getGenero());
        usuario.setCelular(dto.getCelular());
        usuario.setSenha(dto.getSenha());

        if (dto.getEndereco() != null) {
            Endereco endereco = new Endereco();
            endereco.setCep(dto.getEndereco().getCep());
            endereco.setLogradouro(dto.getEndereco().getLogradouro());
            endereco.setNumero(dto.getEndereco().getNumero());
            endereco.setBairro(dto.getEndereco().getBairro());
            endereco.setMunicipio(dto.getEndereco().getMunicipio());
            endereco.setEstado(dto.getEndereco().getEstado());
            endereco.setComplemento(dto.getEndereco().getComplemento());

            endereco.setUsuario(usuario);
            usuario.setEndereco(endereco);
        }

        return usuario;
    }

    public UsuarioDTO toDTO(Usuario usuario) {
        if (usuario == null) return null;

        UsuarioDTO dto = new UsuarioDTO();
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        dto.setCpf(usuario.getCpf());
        dto.setRg(usuario.getRg());
        dto.setDataNascimento(usuario.getDataNascimento());
        dto.setGenero(usuario.getGenero());
        dto.setCelular(usuario.getCelular());

        if (usuario.getEndereco() != null) {
            EnderecoDTO enderecoDTO = new EnderecoDTO();
            enderecoDTO.setCep(usuario.getEndereco().getCep());
            enderecoDTO.setLogradouro(usuario.getEndereco().getLogradouro());
            enderecoDTO.setNumero(usuario.getEndereco().getNumero());
            enderecoDTO.setBairro(usuario.getEndereco().getBairro());
            enderecoDTO.setMunicipio(usuario.getEndereco().getMunicipio());
            enderecoDTO.setEstado(usuario.getEndereco().getEstado());
            enderecoDTO.setComplemento(usuario.getEndereco().getComplemento());

            dto.setEndereco(enderecoDTO);
        }

        return dto;
    }
}