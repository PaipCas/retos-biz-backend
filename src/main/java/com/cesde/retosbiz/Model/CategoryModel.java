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
@Table(name = "categorias")
@Schema(name = "CategoryModel", description = "Modelo que representa una categoría de retos")
public class CategoryModel {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Schema(description = "Identificador único de la categoría", example = "a1b2c3d4-e5f6-7890-abcd-ef1234567890")
	private String id;

	@Column(nullable = false, length = 100, unique = true)
	@Schema(description = "Nombre de la categoría", example = "Desarrollo Sostenible")
	private String nombre;

	@Column(length = 200)
	@Schema(description = "Descripción detallada de la categoría", example = "Proyectos orientados al impacto ambiental y energías renovables")
	private String descripcion;

	@Column(length = 100)
	@Schema(description = "Área responsable encargada de la categoría", example = "Innovación y Tecnología")
	private String area_responsable;

	// Relación Uno a Muchos con Reto (Una categoría tiene muchos retos)
	@OneToMany(mappedBy = "categoria")
	@JsonManagedReference
	@Schema(description = "Lista de retos asociados a esta categoría")
	private List<ChallengeModel> retos;

	// Constructores, Getters y Setters
	public CategoryModel() {
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getArea_responsable() {
		return area_responsable;
	}

	public void setArea_responsable(String area_responsable) {
		this.area_responsable = area_responsable;
	}
}