package com.QuantaSoftSP.app.Services;

import com.QuantaSoftSP.app.Entity.FixtureEquipo;
import com.QuantaSoftSP.app.Entity.Publicidad;
import com.QuantaSoftSP.app.Entity.UsuarioJuez;

import java.util.List;

public interface IFixtureEquipoService {
    public List<FixtureEquipo> findAll();
    public FixtureEquipo findById(Long id);
    public FixtureEquipo save(FixtureEquipo fixtureEquipo);
    public void delete(Long id);
    public List<UsuarioJuez> findAllFixtureEquipoUsuarioJuez();
}
