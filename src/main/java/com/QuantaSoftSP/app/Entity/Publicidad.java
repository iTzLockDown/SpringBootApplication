package com.QuantaSoftSP.app.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "xvi_finpublicidads")
public class Publicidad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String videoPublicidad;
    @Column(unique = true)
    private String imgPublicidad;

    @NotNull(message = "No puede estar vacio")
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaRegPublicidad;

    @NotNull(message = "El sponsor no puede estar vacio.")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publicidads_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Sponsor sponsorPublicidad;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVideoPublicidad() {
        return videoPublicidad;
    }

    public void setVideoPublicidad(String videoPublicidad) {
        this.videoPublicidad = videoPublicidad;
    }

    public String getImgPublicidad() {
        return imgPublicidad;
    }

    public void setImgPublicidad(String imgPublicidad) {
        this.imgPublicidad = imgPublicidad;
    }

    public Date getFechaRegPublicidad() {
        return fechaRegPublicidad;
    }

    public void setFechaRegPublicidad(Date fechaRegPublicidad) {
        this.fechaRegPublicidad = fechaRegPublicidad;
    }

    public Sponsor getSponsorPublicidad() {
        return sponsorPublicidad;
    }

    public void setSponsorPublicidad(Sponsor sponsorPublicidad) {
        this.sponsorPublicidad = sponsorPublicidad;
    }
}
