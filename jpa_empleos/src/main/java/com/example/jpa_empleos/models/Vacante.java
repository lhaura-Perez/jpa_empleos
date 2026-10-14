package com.example.jpa_empleos.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;

@Entity
@Table(name = "vacantes")
@JsonPropertyOrder({
        "id",
        "nombre",
        "descripcion",
        "fecha",
        "salario",
        "estatus",
        "destacado",
        "imagen",
        "detalles"
})
public class Vacante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

    private String descripcion;

    private String fecha;

    @Enumerated(EnumType.STRING)
    private EstatusVacante estatus;

    private Double salario;

    private Integer destacado;

    private String imagen;

    private String detalles;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    // Getters y Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }

    public EstatusVacante getEstatus() { return estatus; }
    public void setEstatus(EstatusVacante estatus) { this.estatus = estatus; }

    public Double getSalario() { return salario; }
    public void setSalario(Double salario) { this.salario = salario; }

    public Integer getDestacado() { return destacado; }
    public void setDestacado(Integer destacado) { this.destacado = destacado; }

    public String getImagen() { return imagen; }
    public void setImagen(String imagen) { this.imagen = imagen; }

    public String getDetalles() { return detalles; }
    public void setDetalles(String det) { this.detalles = det; }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
}