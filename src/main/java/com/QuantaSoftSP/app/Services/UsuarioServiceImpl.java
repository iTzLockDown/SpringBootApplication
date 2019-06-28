package com.QuantaSoftSP.app.Services;

import java.util.List;

import com.QuantaSoftSP.app.Entity.Pais;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.QuantaSoftSP.app.Dao.IUsuarioDao;
import com.QuantaSoftSP.app.Entity.Usuario;

@Service
public class UsuarioServiceImpl implements IUsuarioService{

	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		return (List<Usuario>) usuarioDao.findAll();
	}

	@Override
	public Usuario findById(Long id) {
		return usuarioDao.findById(id).orElse(null);
	}

	@Override
	public Usuario save(Usuario usuario) {
		// TODO Auto-generated method stub
		return usuarioDao.save(usuario);
	}
	@Override
	public void delete(Long id) {
		usuarioDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Pais> findAllPaisUsuario()
	{
		return usuarioDao.findAllPaisUsuario();
	}
}
