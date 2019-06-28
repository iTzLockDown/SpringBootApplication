package com.QuantaSoftSP.app.Dao;

import com.QuantaSoftSP.app.Entity.Deporte;
import com.QuantaSoftSP.app.Entity.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.QuantaSoftSP.app.Entity.UsuarioDeportista;

import java.util.List;

public interface IUsuarioDeportistaDao extends CrudRepository<UsuarioDeportista, Long> {
    @Query("from Deporte")
    public List<Deporte> findAllDeporteUsuarioDepor();

    @Query("select u from Usuario u where u.id= :id ")
    public Usuario findUserUsuarioDepor(Long id);
}
