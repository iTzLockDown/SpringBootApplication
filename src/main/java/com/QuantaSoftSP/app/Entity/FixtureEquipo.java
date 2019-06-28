package com.QuantaSoftSP.app.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "xvi_finfixture_equipos")
public class FixtureEquipo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "No puede ser vacio.")
    private Time horaFixture;
    @NotEmpty(message = "No puede ser vacio.")
    @Size(min = 4, max = 100)
    private String lugarFixture;
    @NotEmpty(message = "No puede ser vacio.")
    @Size(min = 4, max = 100)
    private String Competicion;
    @NotNull(message = "No puede estar vacio.")
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaReg;

    @NotNull(message = "El juez no puede estar vacio.")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fixtureuj_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private UsuarioJuez userJuezFixture;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "fefeparticipante_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private List<FixEquipParticipante> equiposFixture;

    public FixtureEquipo() {
        equiposFixture = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Time getHoraFixture() {
        return horaFixture;
    }

    public void setHoraFixture(Time horaFixture) {
        this.horaFixture = horaFixture;
    }

    public String getLugarFixture() {
        return lugarFixture;
    }

    public void setLugarFixture(String lugarFixture) {
        this.lugarFixture = lugarFixture;
    }

    public String getCompeticion() {
        return Competicion;
    }

    public void setCompeticion(String competicion) {
        Competicion = competicion;
    }

    public Date getFechaReg() {
        return fechaReg;
    }

    public void setFechaReg(Date fechaReg) {
        this.fechaReg = fechaReg;
    }

    public UsuarioJuez getUserJuezFixture() {
        return userJuezFixture;
    }

    public void setUserJuezFixture(UsuarioJuez userJuezFixture) {
        this.userJuezFixture = userJuezFixture;
    }

    public List<FixEquipParticipante> getEquiposFixture() {
        return equiposFixture;
    }

    public void setEquiposFixture(List<FixEquipParticipante> equiposFixture) {
        this.equiposFixture = equiposFixture;
    }
}
