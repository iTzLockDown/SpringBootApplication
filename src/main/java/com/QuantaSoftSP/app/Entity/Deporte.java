package com.QuantaSoftSP.app.Entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="xvi_findeportes")
public class Deporte implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false, length =  20, unique = true)
	private String nombre;
	@Column(length = 255)
	private String descripcion;
	@Column(length = 1)
	private String estActivo;
	public long getId() {
		return id;
	}
	public void setId(long id) {
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
	public String getEstActivo() {
		return estActivo;
	}
	public void setEstActivo(String estActivo) {
		this.estActivo = estActivo;
	}
	
	
}
