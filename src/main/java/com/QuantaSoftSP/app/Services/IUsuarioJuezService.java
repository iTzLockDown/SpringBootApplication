package com.QuantaSoftSP.app.Services;

import com.QuantaSoftSP.app.Entity.Deporte;
import com.QuantaSoftSP.app.Entity.Usuario;
import com.QuantaSoftSP.app.Entity.UsuarioJuez;

import java.util.List;

public interface IUsuarioJuezService {
    public List<UsuarioJuez> findAll();
    public UsuarioJuez findById(Long id);
    public UsuarioJuez save(UsuarioJuez usuarioJuez);
    public void delete(Long id);
    public List<Deporte> findAllDeporteUsuarioDepor();
    public Usuario findUserUsuarioJuez(Long id);
}
