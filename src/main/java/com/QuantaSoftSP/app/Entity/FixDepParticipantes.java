package com.QuantaSoftSP.app.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "xvi_finfixture_deportistas_participantes")
public class FixDepParticipantes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fdpuserdep_id" )
    private UsuarioDeportista userDeportistaParticipante;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UsuarioDeportista getUserDeportistaParticipante() {
        return userDeportistaParticipante;
    }

    public void setUserDeportistaParticipante(UsuarioDeportista userDeportistaParticipante) {
        this.userDeportistaParticipante = userDeportistaParticipante;
    }
}
