package com.example.jpa_empleos;

import com.example.jpa_empleos.models.EstatusVacante;
import com.example.jpa_empleos.models.Vacante;
import com.example.jpa_empleos.repository.VacantesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class JpaEmpleosApplication implements CommandLineRunner {

    @Autowired
    private VacantesRepository vacantesRepo;

    public static void main(String[] args) {
        SpringApplication.run(JpaEmpleosApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        buscarPorEstatus();
        buscarPorSalario();
        buscarPorVariosEstatus();
        buscarPorSalarioOrdenadoDesc();
        buscarDestacadasYEstatus();
    }

    private void buscarPorEstatus() {
        List<Vacante> lista = vacantesRepo.findByEstatus(EstatusVacante.Aprobada);
        System.out.println("\n--- 1. VACANTES CON ESTATUS APROBADA ---");
        for (Vacante v : lista) {
            System.out.println("ID: " + v.getId() + " | Nombre: " + v.getNombre() + " | Estatus: " + v.getEstatus());
        }
        System.out.println("Total encontradas: " + lista.size());
    }

    private void buscarPorSalario() {
        List<Vacante> lista = vacantesRepo.findBySalarioBetween(7000.0, 14000.0);
        System.out.println("\n--- 2. VACANTES POR RANGO DE SALARIO (7000 - 14000) ---");
        for (Vacante v : lista) {
            System.out.println("ID: " + v.getId() + " | Nombre: " + v.getNombre() + " | Salario: " + v.getSalario());
        }
        System.out.println("Total encontradas: " + lista.size());
    }

    private void buscarPorVariosEstatus() {
        EstatusVacante[] estatusList = {EstatusVacante.Creada, EstatusVacante.Eliminada};
        List<Vacante> lista = vacantesRepo.findByEstatusIn(estatusList);
        System.out.println("\n--- 3. VACANTES POR MÚLTIPLES ESTATUS (Creada o Eliminada) ---");
        for (Vacante v : lista) {
            System.out.println("ID: " + v.getId() + " | Nombre: " + v.getNombre() + " | Estatus: " + v.getEstatus());
        }
        System.out.println("Total encontradas: " + lista.size());
    }

    private void buscarPorSalarioOrdenadoDesc() {
        List<Vacante> lista = vacantesRepo.findBySalarioBetweenOrderBySalarioDesc(7000.0, 14000.0);
        System.out.println("\n--- 4. SALARIO ENTRE 7000 Y 14000 (ORDENADO DESCENDENTE) ---");
        for (Vacante v : lista) {
            System.out.println("ID: " + v.getId() + " | Nombre: " + v.getNombre() + " | Salario: " + v.getSalario());
        }
        System.out.println("Total encontradas: " + lista.size());
    }

    private void buscarDestacadasYEstatus() {
        List<Vacante> lista = vacantesRepo.findByDestacadoAndEstatusOrderByIdDesc(1, EstatusVacante.Aprobada);
        System.out.println("\n--- 5. DESTACADAS (1) Y APROBADAS (ORDENADAS POR ID DESC) ---");
        for (Vacante v : lista) {
            System.out.println("ID: " + v.getId() + " | Nombre: " + v.getNombre() + " | Destacado: " + v.getDestacado() + " | Estatus: " + v.getEstatus());
        }
        System.out.println("Total encontradas: " + lista.size());
    }
}