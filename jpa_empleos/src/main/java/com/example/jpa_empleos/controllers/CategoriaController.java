package com.example.jpa_empleos.controllers;

import com.example.jpa_empleos.models.Categoria;
import com.example.jpa_empleos.repository.CategoriasRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final CategoriasRepository categoriasRepo;

    public CategoriaController(CategoriasRepository categoriasRepo) {
        this.categoriasRepo = categoriasRepo;
    }

    // 1. Consultar todas las categorías (GET http://localhost:8080/api/categorias)
    @GetMapping
    public Iterable<Categoria> obtenerTodas() {
        return categoriasRepo.findAll();
    }

    // 2. Buscar categoría por ID (GET http://localhost:8080/api/categorias/{id})
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> obtenerPorId(@PathVariable Integer id) {
        Optional<Categoria> categoria = categoriasRepo.findById(id);
        return categoria.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 3. Crear una nueva categoría (POST http://localhost:8080/api/categorias)
    @PostMapping
    public ResponseEntity<Categoria> crearCategoria(@RequestBody Categoria categoria) {
        Categoria nuevaCategoria = categoriasRepo.save(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCategoria);
    }

    // 4. Actualizar una categoría (PUT http://localhost:8080/api/categorias/{id})
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> actualizarCategoria(@PathVariable Integer id, @RequestBody Categoria categoriaDetalles) {
        return categoriasRepo.findById(id).map(categoria -> {
            categoria.setNombre(categoriaDetalles.getNombre());
            categoria.setDescripcion(categoriaDetalles.getDescripcion());
            Categoria categoriaActualizada = categoriasRepo.save(categoria);
            return ResponseEntity.ok(categoriaActualizada);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 5. Eliminar una categoría (DELETE http://localhost:8080/api/categorias/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Integer id) {
        if (categoriasRepo.existsById(id)) {
            categoriasRepo.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}