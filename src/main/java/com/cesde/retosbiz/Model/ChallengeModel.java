package com.cesde.retosbiz.Model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "retos")
@Schema(name = "ChallengeModel", description = "Modelo que representa un reto")
public class ChallengeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(description = "Identificador único del reto", example = "a1b2c3d4-e5f6-7890-abcd-ef1234567890")
    private String id;

    @Column(nullable = false, length = 150)
    @Schema(description = "Nombre del reto", example = "Crear una aplicación web")
    private String nombre;

    @Column(length = 500)
    @Schema(description = "Descripción detallada del reto", example = "Diseñar e implementar una aplicación para resolver un problema")
    private String descripcion;

    @Column(name = "fecha_inicio")
    @Schema(description = "Fecha de inicio del reto", example = "2026-01-15")
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    @Schema(description = "Fecha de finalización del reto", example = "2026-03-30")
    private LocalDate fechaFin;

    @Column(length = 50)
    @Schema(description = "Estado actual del reto", example = "En progreso")
    private String estado;

    // Relación Muchos a Uno con Empresa (muchos retos pueden pertenecer a una empresa)
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_empresa", nullable = false)
    @JsonBackReference
    @Schema(description = "Empresa asociada al reto")
    private CompanyModel empresa;

    // Relación Muchos a Uno con Categoría (muchos retos pueden pertenecer a una categoría)
    @ManyToOne
    @JoinColumn(name = "id_categoria")
    @JsonBackReference
    @Schema(description = "Categoría a la que pertenece el reto")
    private CategoryModel categoria;

    // Relación Muchos a Uno con Prioridad (muchos retos pueden tener una prioridad)
    @ManyToOne
    @JoinColumn(name = "id_prioridad")
    @JsonBackReference
    @Schema(description = "Prioridad asignada al reto")
    private PriorityModel prioridad;

    /// Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public CompanyModel getEmpresa() {
        return empresa;
    }

    public void setEmpresa(CompanyModel empresa) {
        this.empresa = empresa;
    }

    public CategoryModel getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoryModel categoria) {
        this.categoria = categoria;
    }

    public PriorityModel getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(PriorityModel prioridad) {
        this.prioridad = prioridad;
    }
}