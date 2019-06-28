package com.QuantaSoftSP.app.Dao;

import com.QuantaSoftSP.app.Entity.FixtureEquipo;
import com.QuantaSoftSP.app.Entity.UsuarioJuez;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IFixtureEquipoDao extends CrudRepository<FixtureEquipo, Long> {
    @Query("from UsuarioJuez")
    public List<UsuarioJuez> findAllByUserJuez();
}
