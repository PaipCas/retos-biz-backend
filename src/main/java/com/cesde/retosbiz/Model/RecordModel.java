package com.cesde.retosbiz.Model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "registro")
public class RecordModel {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	private LocalDateTime fecha_registro;

	@Column(length = 200)
	private String observacion;

	@Column(length = 50)
	private String estado;

	@Column(nullable = false)
	private String id_usuario;

	@Column(nullable = false)
	private String id_reto;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public LocalDateTime getFecha_registro() {
		return fecha_registro;
	}
	public void setFecha_registro(LocalDateTime fecha_registro) {
		this.fecha_registro = fecha_registro;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(String id_usuario) {
		this.id_usuario = id_usuario;
	}
	public String getId_reto() {
		return id_reto;
	}
	public void setId_reto(String id_reto) {
		this.id_reto = id_reto;
	}
}