package com.example.jpa_empleos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.jpa_empleos.models.Vacante;

public interface VacantesRepository extends JpaRepository<Vacante, Integer> {
}