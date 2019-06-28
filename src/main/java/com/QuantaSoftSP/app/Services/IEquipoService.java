package com.QuantaSoftSP.app.Services;

import com.QuantaSoftSP.app.Entity.Deporte;
import com.QuantaSoftSP.app.Entity.Equipo;
import com.QuantaSoftSP.app.Entity.Pais;
import com.QuantaSoftSP.app.Entity.UsuarioDelegado;

import java.util.List;

public interface IEquipoService {
    public List<Equipo> findAll();
    public Equipo findById(Long id);
    public Equipo save(Equipo equipo);
    public void delete(Long id);
    public List<Deporte> findAllDeporteEquipo();
    public List<Pais> findAllPaisEquipo();
    public UsuarioDelegado findUserUsuarioDelegadoEquipo(Long id);
}
