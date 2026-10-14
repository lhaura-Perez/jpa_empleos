package com.example.jpa_empleos.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

    private String descripcion;

    // Anotación clave para cortar la recursión infinita en el JSON
    @JsonIgnore
    @OneToMany(mappedBy = "categoria")
    private List<Vacante> vacantes;

    // Getters y Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public List<Vacante> getVacantes() { return vacantes; }
    public void setVacantes(List<Vacante> vacantes) { this.vacantes = vacantes; }
}