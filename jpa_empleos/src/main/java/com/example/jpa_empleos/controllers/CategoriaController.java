package com.example.jpa_empleos.controllers;

import com.example.jpa_empleos.models.Categoria;
import com.example.jpa_empleos.repository.CategoriasJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/jpa-categorias")
public class CategoriaController {

    @Autowired
    private CategoriasJPARepository categoriasJPARepo;

    @GetMapping
    public List<Categoria> obtenerTodas() {
        return categoriasJPARepo.findAll();
    }

    @GetMapping("/ordenadas")
    public List<Categoria> obtenerOrdenadas() {
        return categoriasJPARepo.findAll(Sort.by("nombre").descending());
    }

    @GetMapping("/paginadas")
    public Page<Categoria> obtenerPaginadas(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "5") int cantidad) {
        return categoriasJPARepo.findAll(PageRequest.of(pagina, cantidad));
    }

    @GetMapping("/paginadas/ordenadas")
    public Page<Categoria> obtenerPaginadasYOrdenadas(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "5") int cantidad) {
        return categoriasJPARepo.findAll(PageRequest.of(pagina, cantidad, Sort.by("nombre").descending()));
    }

    @GetMapping("/{id}")
    public Optional<Categoria> obtenerPorId(@PathVariable Integer id) {
        return categoriasJPARepo.findById(id);
    }

    @PostMapping
    public Categoria crearCategoria(@RequestBody Categoria categoria) {
        return categoriasJPARepo.save(categoria);
    }

    @PutMapping("/{id}")
    public Categoria actualizarCategoria(@PathVariable Integer id, @RequestBody Categoria categoriaDetalles) {
        Optional<Categoria> categoriaBuscada = categoriasJPARepo.findById(id);
        if (categoriaBuscada.isPresent()) {
            Categoria categoria = categoriaBuscada.get();
            categoria.setNombre(categoriaDetalles.getNombre());
            categoria.setDescripcion(categoriaDetalles.getDescripcion());
            return categoriasJPARepo.save(categoria);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String eliminarCategoria(@PathVariable Integer id) {
        categoriasJPARepo.deleteById(id);
        return "Categoría eliminada con éxito.";
    }

    @DeleteMapping("/todas")
    public String eliminarTodas() {
        categoriasJPARepo.deleteAllInBatch();
        return "Todas las categorías han sido eliminadas en lote.";
    }
}