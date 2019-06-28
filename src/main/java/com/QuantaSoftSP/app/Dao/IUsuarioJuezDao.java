package com.QuantaSoftSP.app.Dao;

import com.QuantaSoftSP.app.Entity.Deporte;
import com.QuantaSoftSP.app.Entity.Usuario;
import com.QuantaSoftSP.app.Entity.UsuarioJuez;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IUsuarioJuezDao extends CrudRepository<UsuarioJuez, Long> {

    @Query("from Deporte")
    public List<Deporte> findAllDeporteUsuarioJuez();

    @Query("select u from Usuario u where u.id= :id ")
    public Usuario findUserUsuarioJuez(Long id);
}
