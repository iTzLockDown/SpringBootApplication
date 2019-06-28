package com.QuantaSoftSP.app.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
@Entity
@Table(name="xvi_finusuario_juez")
public class UsuarioJuez implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "El deporte no puede estar vacio.")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deportej_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Deporte deporte;

    @NotEmpty(message = "No puede ir vacio.")
    @Size(min = 50,max = 255)
    @Column(nullable = false, length = 255)
    private String observaciones;

    @Size(max = 255)
    @Column(nullable = false, length = 255)
    private String descripcion;

    @NotNull(message = "El deporte no puede estar vacio.")
    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "usuarioj_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Usuario userJuezUsuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Deporte getDeporte() {
        return deporte;
    }

    public void setDeporte(Deporte deporte) {
        this.deporte = deporte;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Usuario getUserJuezUsuario() {
        return userJuezUsuario;
    }

    public void setUserJuezUsuario(Usuario userJuezUsuario) {
        this.userJuezUsuario = userJuezUsuario;
    }
}
