package com.example.jpa_empleos;

import com.example.jpa_empleos.models.Categoria;
import com.example.jpa_empleos.repository.CategoriasJPARepository;
import com.example.jpa_empleos.repository.CategoriasRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootApplication
public class JpaEmpleosApplication implements CommandLineRunner {

    private final CategoriasRepository categoriasRepo;
    private final CategoriasJPARepository categoriasJPARepo;

    public JpaEmpleosApplication(CategoriasRepository categoriasRepo, CategoriasJPARepository categoriasJPARepo) {
        this.categoriasRepo = categoriasRepo;
        this.categoriasJPARepo = categoriasJPARepo;
    }

    public static void main(String[] args) {
        SpringApplication.run(JpaEmpleosApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        buscarTodasJPA();
        buscarTodosOrdenados();
        buscarTodosPaginacion();
    }

    private void buscarTodasJPA() {
        List<Categoria> categorias = categoriasJPARepo.findAll();
        for (Categoria c : categorias) {
            System.out.println(c.getId() + " - " + c.getNombre());
        }
    }

    private void buscarTodosOrdenados() {
        List<Categoria> categorias = categoriasJPARepo.findAll(Sort.by("nombre").descending());
        for (Categoria c : categorias) {
            System.out.println(c.getId() + " - " + c.getNombre());
        }
    }

    private void buscarTodosPaginacion() {
        Page<Categoria> page = categoriasJPARepo.findAll(PageRequest.of(0, 5));
        System.out.println("Total Registros: " + page.getTotalElements());
        System.out.println("Total Páginas: " + page.getTotalPages());
        for (Categoria c : page.getContent()) {
            System.out.println(c.getId() + " - " + c.getNombre());
        }
    }
}