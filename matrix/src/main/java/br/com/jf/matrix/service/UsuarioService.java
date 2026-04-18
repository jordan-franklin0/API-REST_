package br.com.jf.matrix.service;

import br.com.jf.matrix.dto.UsuarioDTO;
import br.com.jf.matrix.mapper.UsuarioMapper;
import br.com.jf.matrix.model.entity.Usuario;
import br.com.jf.matrix.model.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UsuarioDTO criarUsuario(UsuarioDTO dto) {
        if (usuarioRepository.findByCpf(dto.getCpf()).isPresent()) {
            throw new RuntimeException("Já existe um usuário cadastrado com este CPF.");
        }
        if (usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Já existe um usuário cadastrado com este E-mail.");
        }

        Usuario usuario = usuarioMapper.toEntity(dto);

        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));

        usuario = usuarioRepository.save(usuario);
        return usuarioMapper.toDTO(usuario);
    }

    public List<UsuarioDTO> listarTodos() {
        return usuarioRepository.findAll()
                .stream()
                .map(usuarioMapper::toDTO)
                .collect(Collectors.toList());
    }

    public UsuarioDTO buscarPorCpf(String cpf) {
        Usuario usuario = usuarioRepository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o CPF: " + cpf));
        return usuarioMapper.toDTO(usuario);
    }

    @Transactional
    public UsuarioDTO atualizarCompleto(String cpf, UsuarioDTO dto) {
        Usuario usuario = usuarioRepository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        usuario.setNome(dto.getNome());
        usuario.setRg(dto.getRg());
        usuario.setDataNascimento(dto.getDataNascimento());
        usuario.setGenero(dto.getGenero());
        usuario.setCelular(dto.getCelular());

        if (dto.getEndereco() != null) {
            usuario.getEndereco().setCep(dto.getEndereco().getCep());
            usuario.getEndereco().setLogradouro(dto.getEndereco().getLogradouro());
            usuario.getEndereco().setNumero(dto.getEndereco().getNumero());
            usuario.getEndereco().setBairro(dto.getEndereco().getBairro());
            usuario.getEndereco().setMunicipio(dto.getEndereco().getMunicipio());
            usuario.getEndereco().setEstado(dto.getEndereco().getEstado());
            usuario.getEndereco().setComplemento(dto.getEndereco().getComplemento());
        }

        usuario = usuarioRepository.save(usuario);
        return usuarioMapper.toDTO(usuario);
    }

    @Transactional
    public UsuarioDTO atualizarParcial(String cpf, UsuarioDTO dto) {
        Usuario usuario = usuarioRepository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        if (dto.getNome() != null) usuario.setNome(dto.getNome());
        if (dto.getRg() != null) usuario.setRg(dto.getRg());
        if (dto.getDataNascimento() != null) usuario.setDataNascimento(dto.getDataNascimento());
        if (dto.getGenero() != null) usuario.setGenero(dto.getGenero());
        if (dto.getCelular() != null) usuario.setCelular(dto.getCelular());

        if (dto.getEndereco() != null) {
            if (dto.getEndereco().getCep() != null) usuario.getEndereco().setCep(dto.getEndereco().getCep());
            if (dto.getEndereco().getLogradouro() != null) usuario.getEndereco().setLogradouro(dto.getEndereco().getLogradouro());
            if (dto.getEndereco().getNumero() != null) usuario.getEndereco().setNumero(dto.getEndereco().getNumero());
            if (dto.getEndereco().getBairro() != null) usuario.getEndereco().setBairro(dto.getEndereco().getBairro());
            if (dto.getEndereco().getMunicipio() != null) usuario.getEndereco().setMunicipio(dto.getEndereco().getMunicipio());
            if (dto.getEndereco().getEstado() != null) usuario.getEndereco().setEstado(dto.getEndereco().getEstado());
            if (dto.getEndereco().getComplemento() != null) usuario.getEndereco().setComplemento(dto.getEndereco().getComplemento());
        }

        usuario = usuarioRepository.save(usuario);
        return usuarioMapper.toDTO(usuario);
    }

    @Transactional
    public void deletarUsuario(String cpf) {
        Usuario usuario = usuarioRepository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o CPF: " + cpf));

        usuarioRepository.delete(usuario);
    }
}