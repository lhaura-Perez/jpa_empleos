package com.example.jpa_empleos.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jpa_empleos.models.Vacante;
import com.example.jpa_empleos.repository.VacantesRepository;

@RestController
@RequestMapping("/api")
public class VacanteController {

    @Autowired
    private VacantesRepository vacantesRepo;

    // Endpoint GET para buscar todas las vacantes
    @GetMapping("/jpa-vacante")
    public List<Vacante> buscarVacantes() {
        return vacantesRepo.findAll();
    }

    // Endpoint POST para guardar una nueva vacante
    @PostMapping("/jpa-vacante")
    public Vacante guardarVacante(@RequestBody Vacante vacante) {
        return vacantesRepo.save(vacante);
    }
}