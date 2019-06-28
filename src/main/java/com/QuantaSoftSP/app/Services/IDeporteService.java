package com.QuantaSoftSP.app.Services;

import java.util.List;

import com.QuantaSoftSP.app.Entity.Deporte;



public interface IDeporteService {
	public List<Deporte> findAll();
	public Deporte findById(Long id);
	public Deporte save(Deporte deporte);
	public void delete(Long id);
}
