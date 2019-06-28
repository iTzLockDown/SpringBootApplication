package com.QuantaSoftSP.app.Services;

import com.QuantaSoftSP.app.Entity.Sponsor;

import java.util.List;

public interface ISponsorService {
    public List<Sponsor> findAll();
    public Sponsor findById(Long id);
    public Sponsor save(Sponsor deporte);
    public void delete(Long id);
}
