package com.QuantaSoftSP.app.Services;

import com.QuantaSoftSP.app.Dao.IUsuarioDelegadoDao;
import com.QuantaSoftSP.app.Entity.Deporte;
import com.QuantaSoftSP.app.Entity.Usuario;
import com.QuantaSoftSP.app.Entity.UsuarioDelegado;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class UsuarioDelegadoServiceImpl implements IUsuarioDelegadoService{

	@Autowired
    private IUsuarioDelegadoDao usuarioDelegadoDao;

    @Override
	@Transactional(readOnly = true)
    public List<UsuarioDelegado> findAll() {
        return (List<UsuarioDelegado>) usuarioDelegadoDao.findAll();
    }

    @Override
    public UsuarioDelegado findById(Long id) {
        return usuarioDelegadoDao.findById(id).orElse(null);
    }

    @Override
    public UsuarioDelegado save(UsuarioDelegado usuarioDelegado) {
        return usuarioDelegadoDao.save(usuarioDelegado);
    }

    @Override
    public void delete(Long id) {
    	usuarioDelegadoDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Deporte> findAllDeporteUsuarioJuez() {
        return usuarioDelegadoDao.findAllDeporteUsuariDel();
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario findUserUsuarioJuez(Long id) {
        return usuarioDelegadoDao.findUserUsuarioDel(id);
    }
}
