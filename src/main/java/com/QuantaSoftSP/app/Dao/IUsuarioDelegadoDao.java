package com.QuantaSoftSP.app.Dao;

import com.QuantaSoftSP.app.Entity.Deporte;
import com.QuantaSoftSP.app.Entity.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.QuantaSoftSP.app.Entity.UsuarioDelegado;

import java.util.List;

public interface IUsuarioDelegadoDao extends CrudRepository<UsuarioDelegado, Long>{

    @Query("from Deporte")
    public List<Deporte> findAllDeporteUsuariDel();

    @Query("select u from Usuario u where u.id= :id ")
    public Usuario findUserUsuarioDel(Long id);
}
