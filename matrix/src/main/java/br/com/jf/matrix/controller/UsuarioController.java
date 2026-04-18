package br.com.jf.matrix.controller;

import br.com.jf.matrix.dto.UsuarioDTO;
import br.com.jf.matrix.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> criarUsuario(@Valid @RequestBody UsuarioDTO dto) {
        UsuarioDTO novoUsuario = usuarioService.criarUsuario(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listarTodos() {
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<UsuarioDTO> buscarPorCpf(@PathVariable String cpf) {
        return ResponseEntity.ok(usuarioService.buscarPorCpf(cpf));
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<UsuarioDTO> atualizarCompleto(@PathVariable String cpf, @Valid @RequestBody UsuarioDTO dto) {
        return ResponseEntity.ok(usuarioService.atualizarCompleto(cpf, dto));
    }

    @PatchMapping("/{cpf}")
    public ResponseEntity<UsuarioDTO> atualizarParcial(@PathVariable String cpf, @RequestBody UsuarioDTO dto) {
        return ResponseEntity.ok(usuarioService.atualizarParcial(cpf, dto));
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable String cpf) {
        usuarioService.deletarUsuario(cpf);
        return ResponseEntity.noContent().build();
    }
}