package com.QuantaSoftSP.app.Services;

import com.QuantaSoftSP.app.Dao.IUsuarioDeportistaDao;
import com.QuantaSoftSP.app.Dao.IUsuarioJuezDao;
import com.QuantaSoftSP.app.Entity.Deporte;
import com.QuantaSoftSP.app.Entity.Usuario;
import com.QuantaSoftSP.app.Entity.UsuarioJuez;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class UsuarioJuezServiceImpl implements IUsuarioJuezService {
    @Autowired
    private IUsuarioJuezDao usuarioJuezDao;

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioJuez> findAll() {
        return (List<UsuarioJuez>) usuarioJuezDao.findAll();
    }

    @Override
    public UsuarioJuez findById(Long id) {
        return usuarioJuezDao.findById(id).orElse(null);
    }

    @Override
    public UsuarioJuez save(UsuarioJuez usuarioJuez) {
        return usuarioJuezDao.save(usuarioJuez);
    }

    @Override
    public void delete(Long id) {
        usuarioJuezDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Deporte> findAllDeporteUsuarioDepor() {
       return usuarioJuezDao.findAllDeporteUsuarioJuez();
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario findUserUsuarioJuez(Long id) {
        return usuarioJuezDao.findUserUsuarioJuez(id);
    }
}
