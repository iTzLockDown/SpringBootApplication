package com.QuantaSoftSP.app.Services;

import com.QuantaSoftSP.app.Dao.IFixtureDeportistaDao;
import com.QuantaSoftSP.app.Entity.FixtureDeportista;
import com.QuantaSoftSP.app.Entity.UsuarioJuez;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FitureDeportistaServiceImpl implements IFixtureDeportistaService {
    @Autowired
    private IFixtureDeportistaDao fixtureDeportistaDao;

    @Override
    public List<FixtureDeportista> findAll() {
        return (List<FixtureDeportista>) fixtureDeportistaDao.findAll();
    }

    @Override
    public FixtureDeportista findById(Long id) {
       return fixtureDeportistaDao.findById(id).orElse(null);
    }

    @Override
    public FixtureDeportista save(FixtureDeportista fixtureDeportista) {
        return fixtureDeportistaDao.save(fixtureDeportista);
    }

    @Override
    public void delete(Long id) {
        fixtureDeportistaDao.deleteById(id);
    }

    @Override
    public List<UsuarioJuez> findAllFixtureDeportistaUsuarioJuez() {
        return fixtureDeportistaDao.findAllByUserJuez();
    }
}
