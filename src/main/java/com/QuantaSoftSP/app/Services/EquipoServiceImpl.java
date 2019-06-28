package com.QuantaSoftSP.app.Services;

import com.QuantaSoftSP.app.Dao.IEquipoDao;
import com.QuantaSoftSP.app.Entity.Deporte;
import com.QuantaSoftSP.app.Entity.Equipo;
import com.QuantaSoftSP.app.Entity.Pais;
import com.QuantaSoftSP.app.Entity.UsuarioDelegado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class EquipoServiceImpl implements IEquipoService {
    @Autowired
    private IEquipoDao equipoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Equipo> findAll() {
        return(List<Equipo>)equipoDao.findAll();
    }

    @Override
    public Equipo findById(Long id) {
        return equipoDao.findById(id).orElse(null);
    }

    @Override
    public Equipo save(Equipo equipo) {
        return equipoDao.save(equipo);
    }

    @Override
    public void delete(Long id) {
        equipoDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Deporte> findAllDeporteEquipo() {

        return equipoDao.findAllDeporteEquipo();
    }
    @Override
    @Transactional(readOnly = true)
    public List<Pais> findAllPaisEquipo() {

        return equipoDao.findAllPaisEquipo();
    }

    @Override
    @Transactional(readOnly = true)
    public UsuarioDelegado findUserUsuarioDelegadoEquipo(Long id) {
        return equipoDao.findUserUsuarioDelegadoEquipo(id);

    }
}
