package com.cesde.retosbiz.Model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "retos")
public class ChallengeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(length = 500)
    private String descripcion;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @Column(length = 50)
    private String estado;

    @Column(name = "id_empresa", nullable = false, length = 50)
    private String idEmpresa;

    @Column(name = "id_categoria", nullable = false, length = 50)
    private String idCategoria;

    @Column(name = "id_prioridad", nullable = false, length = 50)
    private String idPrioridad;

    /// Getters and Setters

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }

    public LocalDate getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getIdEmpresa() { return idEmpresa; }
    public void setIdEmpresa(String idEmpresa) { this.idEmpresa = idEmpresa; }

    public String getIdCategoria() { return idCategoria; }
    public void setIdCategoria(String idCategoria) { this.idCategoria = idCategoria; }

    public String getIdPrioridad() { return idPrioridad; }
    public void setIdPrioridad(String idPrioridad) { this.idPrioridad = idPrioridad; }
}