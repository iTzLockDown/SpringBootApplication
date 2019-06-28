package com.QuantaSoftSP.app.Dao;

import com.QuantaSoftSP.app.Entity.Deporte;
import com.QuantaSoftSP.app.Entity.Equipo;
import com.QuantaSoftSP.app.Entity.Pais;
import com.QuantaSoftSP.app.Entity.UsuarioDelegado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IEquipoDao extends CrudRepository<Equipo, Long> {
    @Query("from Deporte")
    public List<Deporte> findAllDeporteEquipo();

    @Query("from Pais")
    public List<Pais> findAllPaisEquipo();

    @Query("select u from UsuarioDelegado u where u.id= :id ")
    public UsuarioDelegado findUserUsuarioDelegadoEquipo(Long id);
}
