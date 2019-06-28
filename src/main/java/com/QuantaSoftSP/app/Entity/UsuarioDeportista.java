package com.QuantaSoftSP.app.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="xvi_finusuario_deportistas")
public class UsuarioDeportista implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull(message = "No puede ser vacio.")
	@Column(nullable = false, length = 5)
	private double altura;
	@NotNull(message = "No puede ser vacio.")
	@Column(nullable = false, length = 4)
	private double peso;
	@Column(length = 255)
	private String observacion;

	@NotEmpty(message = "No puede ser vacio.")
	@Column(nullable = false, length = 1)
	private String estActivo;



	private String estBloq;

	@NotNull(message = "El deporte no puede estar vacio.")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "deporte_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Deporte deporte;

	@NotNull(message = "El deporte no puede estar vacio.")
	@OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Usuario userDepUsuario;


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getEstActivo() {
		return estActivo;
	}

	public void setEstActivo(String estActivo) {
		this.estActivo = estActivo;
	}

	public Deporte getDeporte() {
		return deporte;
	}

	public void setDeporte(Deporte deporte) {
		this.deporte = deporte;
	}

	public Usuario getUserDepUsuario() {
		return userDepUsuario;
	}

	public void setUserDepUsuario(Usuario userDepUsuario) {
		this.userDepUsuario = userDepUsuario;
	}
}
