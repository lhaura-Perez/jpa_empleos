package com.example.jpa_empleos;

import java.util.Date;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.jpa_empleos.models.Categoria;
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
        // guardarVacante(); // Comentado para evitar que se inserte automáticamente al iniciar la app
        buscarVacantes();
    }

    /**
     * Guardar una vacante
     */
    private void guardarVacante() {
        Vacante vacante = new Vacante();
        vacante.setNombre("Desarrollador Java PRO");
        vacante.setDescripcion("Se busca desarrollador con experiencia en Spring Boot y JPA.");
        vacante.setFecha(new Date());
        vacante.setSalario(25000.0);
        vacante.setEstatus(1);
        vacante.setDestacado("1");
        vacante.setImagen("logo_empresa.png");
        vacante.setDetalles("Trabajo remoto con horario flexible. Beneficios y capacitación incluidos.");

        // Crear una categoría asociada (asociando el ID 1 que ya existe en la BD)
        Categoria categoria = new Categoria();
        categoria.setId(1);

        vacante.setCategoria(categoria);
        vacantesRepo.save(vacante);
        System.out.println("¡Vacante guardada con éxito!");
    }

    /**
     * Método findAll - Interfaz JPARepository con consulta de ambas tablas
     */
    private void buscarVacantes() {
        List<Vacante> vacantes = vacantesRepo.findAll();
        for (Vacante vacante : vacantes) {
            System.out.println(vacante.getId() + ". " + vacante.getNombre() +
                    " -> " + (vacante.getCategoria() != null ? vacante.getCategoria().getNombre() : "Sin categoría"));
        }
    }
}