package com.QuantaSoftSP.app.Dao;

import com.QuantaSoftSP.app.Entity.FixtureDeportista;
import com.QuantaSoftSP.app.Entity.UsuarioJuez;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IFixtureDeportistaDao extends CrudRepository<FixtureDeportista, Long> {
    @Query("from UsuarioJuez")
    public List<UsuarioJuez> findAllByUserJuez();
}
