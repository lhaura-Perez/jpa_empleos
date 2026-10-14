package com.example.jpa_empleos.controller;

import com.example.jpa_empleos.models.Perfil;
import com.example.jpa_empleos.models.Usuario;
import com.example.jpa_empleos.repository.PerfilesRepository;
import com.example.jpa_empleos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/jpa-usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private PerfilesRepository perfilesRepo;

    /**
     * GET: Buscar un usuario por ID (incluyendo sus perfiles)
     * URL: http://localhost:8080/api/jpa-usuario/{id}
     */
    @GetMapping("/{id}")
    public Usuario buscarUsuario(@PathVariable Integer id) {
        Optional<Usuario> usuario = usuarioRepo.findById(id);
        return usuario.orElse(null);
    }

    /**
     * POST: Crear un nuevo usuario con perfiles asociados
     * URL: http://localhost:8080/api/jpa-usuario
     */
    @PostMapping
    public Usuario guardarUsuario(@RequestBody Usuario usuario) {
        return usuarioRepo.save(usuario);
    }

    /**
     * POST: Crear uno o varios perfiles nuevos
     * URL: http://localhost:8080/api/jpa-usuario/perfiles
     */
    @PostMapping("/perfiles")
    public List<Perfil> guardarPerfiles(@RequestBody List<Perfil> perfiles) {
        return perfilesRepo.saveAll(perfiles);
    }
}