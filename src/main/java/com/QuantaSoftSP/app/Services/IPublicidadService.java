package com.QuantaSoftSP.app.Services;

import com.QuantaSoftSP.app.Entity.Publicidad;
import com.QuantaSoftSP.app.Entity.Sponsor;

import java.util.List;

public interface IPublicidadService {
    public List<Publicidad> findAll();
    public Publicidad findById(Long id);
    public Publicidad save(Publicidad publicidad);
    public void delete(Long id);
    public List<Sponsor> findAllSponsorPublicidad();
}
