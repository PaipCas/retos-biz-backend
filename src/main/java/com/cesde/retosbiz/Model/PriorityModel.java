package com.cesde.retosbiz.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "prioridades")
public class PriorityModel {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	@Column(nullable = false, length = 100, unique = true)
	private String nombre;

	@Column(length = 200)
	private Integer nivel;

	@Column(length = 100)
	private Integer dias_max_respuesta;

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