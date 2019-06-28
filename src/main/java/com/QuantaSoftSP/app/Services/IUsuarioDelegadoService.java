package com.QuantaSoftSP.app.Services;

import java.util.List;

import com.QuantaSoftSP.app.Entity.Deporte;
import com.QuantaSoftSP.app.Entity.Usuario;
import com.QuantaSoftSP.app.Entity.UsuarioDelegado;

public interface IUsuarioDelegadoService {
	public List<UsuarioDelegado> findAll();
	public UsuarioDelegado findById(Long id);
	public UsuarioDelegado save(UsuarioDelegado usuarioDelegado);
	public void delete(Long id);
	public List<Deporte> findAllDeporteUsuarioJuez();
	public Usuario findUserUsuarioJuez(Long id);
}
