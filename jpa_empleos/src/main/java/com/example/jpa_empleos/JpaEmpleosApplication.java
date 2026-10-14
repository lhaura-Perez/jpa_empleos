package com.example.jpa_empleos;

import com.example.jpa_empleos.models.Perfil;
import com.example.jpa_empleos.models.Usuario;
import com.example.jpa_empleos.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@SpringBootApplication
public class JpaEmpleosApplication implements CommandLineRunner {

    private final CategoriasRepository categoriasRepo;
    private final CategoriasJPARepository categoriasJPARepo;
    private final VacantesRepository vacantesRepo;
    private final PerfilesRepository perfilesRepo;
    private final UsuarioRepository usuarioRepo;

    public JpaEmpleosApplication(CategoriasRepository categoriasRepo,
                                 CategoriasJPARepository categoriasJPARepo,
                                 VacantesRepository vacantesRepo,
                                 PerfilesRepository perfilesRepo,
                                 UsuarioRepository usuarioRepo) {
        this.categoriasRepo = categoriasRepo;
        this.categoriasJPARepo = categoriasJPARepo;
        this.vacantesRepo = vacantesRepo;
        this.perfilesRepo = perfilesRepo;
        this.usuarioRepo = usuarioRepo;
    }

    public static void main(String[] args) {
        SpringApplication.run(JpaEmpleosApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // 1. Crear perfiles iniciales en la BD (ejecutar una vez o validar)
        crearPerfiles();

        // 2. Crear un usuario de prueba con perfiles asignados
        crearUsuarioConPerfiles();

        // 3. Buscar el usuario creado
        buscarUsuario();
    }

    private void crearPerfiles() {
        if (perfilesRepo.count() == 0) {
            perfilesRepo.saveAll(obtenerPerfiles());
            System.out.println("¡Perfiles creados con éxito!");
        }
    }

    private List<Perfil> obtenerPerfiles() {
        List<Perfil> perfiles = new LinkedList<>();
        Perfil perfil1 = new Perfil();
        perfil1.setPerfil("SUPERVISOR");
        Perfil perfil2 = new Perfil();
        perfil2.setPerfil("ADMINISTRADOR");
        Perfil perfil3 = new Perfil();
        perfil3.setPerfil("USUARIO");
        perfiles.add(perfil1);
        perfiles.add(perfil2);
        perfiles.add(perfil3);
        return perfiles;
    }

    private void crearUsuarioConPerfiles() {
        // Evitar duplicar si ya existe el usuario
        if (usuarioRepo.count() == 0) {
            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setNombre("José García");
            nuevoUsuario.setEmail("jose@gmail.com");
            nuevoUsuario.setUsername("joseg");
            nuevoUsuario.setPassword("12345");
            nuevoUsuario.setEstatus(1);
            nuevoUsuario.setFechaRegistro(LocalDate.now());

            Perfil perfil1 = new Perfil();
            perfil1.setId(2); // ADMINISTRADOR
            Perfil perfil2 = new Perfil();
            perfil2.setId(3); // USUARIO

            Set<Perfil> perfilesUsuario = new HashSet<>();
            perfilesUsuario.add(perfil1);
            perfilesUsuario.add(perfil2);
            nuevoUsuario.setPerfiles(perfilesUsuario);

            usuarioRepo.save(nuevoUsuario);
            System.out.println("¡Usuario con perfiles creado con éxito!");
        }
    }

    private void buscarUsuario() {
        Optional<Usuario> usuarioOptional = usuarioRepo.findById(1);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            System.out.println("Nombre: " + usuario.getNombre());
            System.out.println("Perfiles asignados:");
            for (Perfil perfil : usuario.getPerfiles()) {
                System.out.println("- " + perfil.getPerfil());
            }
        }
    }
}