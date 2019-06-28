package com.QuantaSoftSP.app.Dao;

import com.QuantaSoftSP.app.Entity.Publicidad;
import com.QuantaSoftSP.app.Entity.Sponsor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IPublicidadDao extends CrudRepository<Publicidad, Long> {
    @Query("from Sponsor")
    public List<Sponsor> findAllSponsorPublicidad();
}
