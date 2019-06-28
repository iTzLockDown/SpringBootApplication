package com.QuantaSoftSP.app.Services;

import java.util.List;

import com.QuantaSoftSP.app.Entity.Deporte;
import com.QuantaSoftSP.app.Entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.QuantaSoftSP.app.Dao.IUsuarioDeportistaDao;
import com.QuantaSoftSP.app.Entity.UsuarioDeportista;
@Service
public class UsuarioDeportistaServiceImpl implements IUsuarioDeportistaService {

	@Autowired
	private IUsuarioDeportistaDao usuarioDeportistaDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<UsuarioDeportista> findAll() {
		return (List<UsuarioDeportista>) usuarioDeportistaDao.findAll();
	}

	@Override
	public UsuarioDeportista findById(Long id) {
		return usuarioDeportistaDao.findById(id).orElse(null);
	}

	@Override
	public UsuarioDeportista save(UsuarioDeportista usuarioDeportista) {
		return usuarioDeportistaDao.save(usuarioDeportista);
	}

	@Override
	public void delete(Long id) {
		usuarioDeportistaDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public  List<Deporte> findAllDeporteUsuarioDepor()
	{
		return usuarioDeportistaDao.findAllDeporteUsuarioDepor();
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findUserUsuarioDepor(Long id)
	{
		return usuarioDeportistaDao.findUserUsuarioDepor(id);
	}
}
