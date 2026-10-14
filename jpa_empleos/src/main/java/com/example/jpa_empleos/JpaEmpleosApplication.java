package com.example.jpa_empleos;

import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.jpa_empleos.models.Vacante;
import com.example.jpa_empleos.repository.CategoriasJPARepository;
import com.example.jpa_empleos.repository.CategoriasRepository;
import com.example.jpa_empleos.repository.VacantesRepository;

@SpringBootApplication
public class JpaEmpleosApplication implements CommandLineRunner {

    private final CategoriasRepository categoriasRepo;
    private final CategoriasJPARepository categoriasJPARepo;
    private final VacantesRepository vacantesRepo;

    public JpaEmpleosApplication(CategoriasRepository categoriasRepo,
                                 CategoriasJPARepository categoriasJPARepo,
                                 VacantesRepository vacantesRepo) {
        this.categoriasRepo = categoriasRepo;
        this.categoriasJPARepo = categoriasJPARepo;
        this.vacantesRepo = vacantesRepo;
    }

    public static void main(String[] args) {
        SpringApplication.run(JpaEmpleosApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        buscarVacantes();
    }

    /**
     * Método findAll - Interfaz JPARepository con consulta de ambas tablas
     */
    private void buscarVacantes() {
        List<Vacante> vacantes = vacantesRepo.findAll();
        for (Vacante vacante : vacantes) {
            System.out.println(vacante.getId() + ". " + vacante.getNombre() +
                    " -> " + vacante.getCategoria().getNombre());
        }
    }
}