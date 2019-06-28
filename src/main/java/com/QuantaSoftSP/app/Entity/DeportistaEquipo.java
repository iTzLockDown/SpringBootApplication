package com.QuantaSoftSP.app.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "xvi_finequipo_deportistas")
public class DeportistaEquipo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "depequipoud_id" )
    private UsuarioDeportista userDeportista;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UsuarioDeportista getUserDeportista() {
        return userDeportista;
    }

    public void setUserDeportista(UsuarioDeportista userDeportista) {
        this.userDeportista = userDeportista;
    }
}
