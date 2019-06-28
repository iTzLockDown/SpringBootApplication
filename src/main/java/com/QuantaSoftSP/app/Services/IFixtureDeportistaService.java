package com.QuantaSoftSP.app.Services;

import com.QuantaSoftSP.app.Entity.FixtureDeportista;
import com.QuantaSoftSP.app.Entity.FixtureEquipo;
import com.QuantaSoftSP.app.Entity.UsuarioJuez;

import java.util.List;

public interface IFixtureDeportistaService {
    public List<FixtureDeportista> findAll();
    public FixtureDeportista findById(Long id);
    public FixtureDeportista save(FixtureDeportista fixtureDeportista);
    public void delete(Long id);
    public List<UsuarioJuez> findAllFixtureDeportistaUsuarioJuez();
}
