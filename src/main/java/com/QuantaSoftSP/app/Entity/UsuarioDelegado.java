package com.QuantaSoftSP.app.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "xvi_finusuario_delegados")
public class UsuarioDelegado implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotEmpty(message = "No puede ser vacio.")
	@Size(min = 4, max = 50)
	@Column(nullable = false, length = 50)
	private String cargo;

	@NotEmpty(message = "No puede ser vacio.")
	@Column(nullable = false, length = 1)
	private String estActivo;

	@NotNull(message = "El deporte no puede estar vacio.")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "deported_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Deporte deporte;

	@NotNull(message = "El deporte no puede estar vacio.")
	@OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	@JoinColumn(name = "usuariodl_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Usuario userDelUsuario;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
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

	public Usuario getUserDelUsuario() {
		return userDelUsuario;
	}

	public void setUserDelUsuario(Usuario userDelUsuario) {
		this.userDelUsuario = userDelUsuario;
	}
}
