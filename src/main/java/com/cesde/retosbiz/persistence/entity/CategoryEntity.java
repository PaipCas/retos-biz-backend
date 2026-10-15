package com.cesde.retosbiz.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 
@Table(name = "categorias")
public class CategoryEntity {
	@Id 
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	@Column(nullable = false, length = 100, unique = true)
	private String nombre;

	@Column(length = 200)
	private String descripcion;
	
	@Column(length = 100)
	private String area_responsable;

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