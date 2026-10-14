package com.example.jpa_empleos.models;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String descripcion;

    // Relación OneToMany con Vacante (mappedBy apunta al atributo 'categoria' en Vacante)
    @OneToMany(mappedBy = "categoria")
    private List<Vacante> vacantes;

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Vacante> getVacantes() {
        return vacantes;
    }

    public void setVacantes(List<Vacante> vacantes) {
        this.vacantes = vacantes;
    }

    @Override
    public String toString() {
        return "Categoria [id=" + id + ", nombre=" + nombre + "]";
    }
}