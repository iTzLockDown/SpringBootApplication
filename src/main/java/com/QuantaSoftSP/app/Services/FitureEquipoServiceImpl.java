package com.QuantaSoftSP.app.Services;

import com.QuantaSoftSP.app.Dao.IFixtureEquipoDao;
import com.QuantaSoftSP.app.Entity.FixtureEquipo;
import com.QuantaSoftSP.app.Entity.UsuarioJuez;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FitureEquipoServiceImpl implements IFixtureEquipoService {
    @Autowired
    private IFixtureEquipoDao fixtureEquipoDao;
    @Override
    public List<FixtureEquipo> findAll() {
        return (List<FixtureEquipo>) fixtureEquipoDao.findAll();
    }

    @Override
    public FixtureEquipo findById(Long id) {
        return fixtureEquipoDao.findById(id).orElse(null);
    }

    @Override
    public FixtureEquipo save(FixtureEquipo fixtureEquipo) {
        return fixtureEquipoDao.save(fixtureEquipo);
    }

    @Override
    public void delete(Long id) {
        fixtureEquipoDao.deleteById(id);
    }

    @Override
    public List<UsuarioJuez> findAllFixtureEquipoUsuarioJuez() {
        return fixtureEquipoDao.findAllByUserJuez();
    }
}
