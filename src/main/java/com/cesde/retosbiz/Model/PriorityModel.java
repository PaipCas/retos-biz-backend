package com.cesde.retosbiz.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "prioridades")
@Schema(name = "PriorityModel", description = "Modelo que representa el nivel de prioridad de un reto")
public class PriorityModel {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Schema(description = "Identificador único de la prioridad", example = "f9e8d7c6-b5a4-3210-fedc-ba0987654321")
	private String id;

	@Column(nullable = false, length = 100, unique = true)
	@Schema(description = "Nombre de la prioridad", example = "Alta")
	private String nombre;

	@Column
	@Schema(description = "Nivel numérico de la prioridad", example = "1")
	private Integer nivel;

	@Column
	@Schema(description = "Días máximos de respuesta estimados para este nivel de prioridad", example = "3")
	private Integer dias_max_respuesta;

	// Relación Uno a Muchos con Reto (Una prioridad puede estar en muchos retos)
	@OneToMany(mappedBy = "prioridad")
	@JsonManagedReference
	@Schema(description = "Lista de retos asociados a esta prioridad")
	private List<ChallengeModel> retos;

	// Constructores, Getters y Setters
	public PriorityModel() {
	}

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

	public Integer getNivel() {
		return nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

	public Integer getDias_max_respuesta() {
		return dias_max_respuesta;
	}

	public void setDias_max_respuesta(Integer dias_max_respuesta) {
		this.dias_max_respuesta = dias_max_respuesta;
	}
}