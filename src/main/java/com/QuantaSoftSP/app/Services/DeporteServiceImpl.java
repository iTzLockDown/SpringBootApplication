package com.QuantaSoftSP.app.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.QuantaSoftSP.app.Dao.IDeporteDao;
import com.QuantaSoftSP.app.Entity.Deporte;


@Service
public class DeporteServiceImpl implements IDeporteService{

	@Autowired
	private IDeporteDao deporteDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Deporte> findAll() {
		return (List<Deporte>) deporteDao.findAll();
	}
	@Override
	public Deporte findById(Long id) {
		return deporteDao.findById(id).orElse(null);
	}
	@Override
	public Deporte save(Deporte deporte) {
		return deporteDao.save(deporte);
	}
	@Override
	public void delete(Long id) {
		deporteDao.deleteById(id);
	}

}
