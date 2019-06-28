package com.QuantaSoftSP.app.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
@Entity
@Table(name = "xvi_finfixture_equipos_participantes")
public class FixEquipParticipante {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fepequipo_id" )
    private Equipo equipoFixture;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Equipo getEquipoFixture() {
        return equipoFixture;
    }

    public void setEquipoFixture(Equipo equipoFixture) {
        this.equipoFixture = equipoFixture;
    }
}
