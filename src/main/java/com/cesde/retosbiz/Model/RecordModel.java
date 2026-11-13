package com.cesde.retosbiz.Model;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "registro")
@Schema(name = "RecordModel", description = "Modelo que representa el registro o inscripción de un usuario en un reto")
public class RecordModel {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Schema(description = "Identificador único del registro", example = "b2c3d4e5-f6a7-8901-bcde-f23456789012")
	private String id;

	@Schema(description = "Fecha y hora en la que se realizó el registro", example = "2026-11-12T18:45:00")
	private LocalDateTime fecha_registro;

	@Column(length = 200)
	@Schema(description = "Observaciones o comentarios adicionales sobre el registro", example = "Inscripción aprobada con documentación completa")
	private String observacion;

	@Column(length = 50)
	@Schema(description = "Estado actual del registro", example = "Activo")
	private String estado;

	@Column(nullable = false)
	@Schema(description = "Identificador único del usuario asociado al registro", example = "c3d4e5f6-a7b8-9012-cdef-345678901234")
	private String id_usuario;

	@Column(nullable = false)
	@Schema(description = "Identificador único del reto al que se registra el usuario", example = "d4e5f6a7-b8c9-0123-defa-456789012345")
	private String id_reto;

	// Constructores, Getters y Setters
	public RecordModel() {
	}

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