package com.QuantaSoftSP.app.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
@Entity
@Table(name = "xvi_finsponsor")
public class Sponsor implements Serializable {

    private static final long serialVersionUID = 1L;
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

   @NotEmpty(message = "No puede ir vacio.")
   @Size(min = 4, max = 255)
   @Column(nullable = false, length = 255)
   private String nombreCompleto;


    @NotEmpty(message = "No puede ir vacio.")
    @Size(min = 4, max = 255)
    @Column(nullable = false, length = 255)
    private String nombreCorto;

    @NotEmpty(message = "No puede ir vacio.")
    @Size(min = 4, max = 255)
    @Column(nullable = false, length = 255, unique = true)
    private String nombreMostrar;


    @Size(min = 4, max = 255)
    @Column(nullable = false, length = 255)
    private String filosofia;

    @Size(max = 100)
    @Column( nullable = false, unique =true, length = 100)
    private String pagWeb;

    private String logoSponsor;

    @Column(nullable = false, length = 1)
    private String estActivo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getNombreCorto() {
        return nombreCorto;
    }

    public void setNombreCorto(String nombreCorto) {
        this.nombreCorto = nombreCorto;
    }

    public String getNombreMostrar() {
        return nombreMostrar;
    }

    public void setNombreMostrar(String nombreMostrar) {
        this.nombreMostrar = nombreMostrar;
    }

    public String getFilosofia() {
        return filosofia;
    }

    public void setFilosofia(String filosofia) {
        this.filosofia = filosofia;
    }

    public String getPagWeb() {
        return pagWeb;
    }

    public void setPagWeb(String pagWeb) {
        this.pagWeb = pagWeb;
    }

    public String getLogoSponsor() {
        return logoSponsor;
    }

    public void setLogoSponsor(String logoSponsor) {
        this.logoSponsor = logoSponsor;
    }

    public String getEstActivo() {
        return estActivo;
    }

    public void setEstActivo(String estActivo) {
        this.estActivo = estActivo;
    }
}
