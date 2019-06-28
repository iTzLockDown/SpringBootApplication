package com.QuantaSoftSP.app.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "xvi_finequipos")
public class Equipo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "No puede ser vacio.")
    @Column(nullable = false, length = 50)
    private String nombre;

    @NotNull(message = "No puede estar vacio")
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dDechaRegistro;


    @NotNull(message = "El deporte no puede estar vacio.")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipodep_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Deporte deporte;


    @NotNull(message = "El pais no puede estar vacio.")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipop_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Pais paisEquipo;

    @NotNull(message = "El delegado no puede estar vacio.")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipoudel_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private UsuarioDelegado userDelEquipo;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "equipode_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private List<DeportistaEquipo> deportistaEquipo;

    public Equipo()
    {
        deportistaEquipo = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getdDechaRegistro() {
        return dDechaRegistro;
    }

    public void setdDechaRegistro(Date dDechaRegistro) {
        this.dDechaRegistro = dDechaRegistro;
    }

    public Deporte getDeporte() {
        return deporte;
    }

    public void setDeporte(Deporte deporte) {
        this.deporte = deporte;
    }

    public UsuarioDelegado getUserDelEquipo() {
        return userDelEquipo;
    }

    public void setUserDelEquipo(UsuarioDelegado userDelEquipo) {
        this.userDelEquipo = userDelEquipo;
    }

    public Pais getPaisEquipo() {
        return paisEquipo;
    }

    public void setPaisEquipo(Pais paisEquipo) {
        this.paisEquipo = paisEquipo;
    }

    public List<DeportistaEquipo> getDeportistaEquipo() {
        return deportistaEquipo;
    }

    public void setDeportistaEquipo(List<DeportistaEquipo> deportistaEquipo) {
        this.deportistaEquipo = deportistaEquipo;
    }
}
