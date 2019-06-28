package com.QuantaSoftSP.app.Services;

import com.QuantaSoftSP.app.Dao.IPublicidadDao;
import com.QuantaSoftSP.app.Entity.Publicidad;
import com.QuantaSoftSP.app.Entity.Sponsor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PublicidadServiceImpl implements IPublicidadService {
    @Autowired
    private IPublicidadDao publicidadDao;

    @Override
    public List<Publicidad> findAll() {
        return (List<Publicidad>)  publicidadDao.findAll();
    }

    @Override
    public Publicidad findById(Long id) {
        return publicidadDao.findById(id).orElse(null);
    }

    @Override
    public Publicidad save(Publicidad publicidad) {
            return publicidadDao.save(publicidad);
    }

    @Override
    public void delete(Long id) {
        publicidadDao.deleteById(id);
    }

    @Override
    public List<Sponsor> findAllSponsorPublicidad() {
        return publicidadDao.findAllSponsorPublicidad();
    }
}
