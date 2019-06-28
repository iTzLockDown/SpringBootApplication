package com.QuantaSoftSP.app.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.QuantaSoftSP.app.Library.AuthLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.QuantaSoftSP.app.Entity.Deporte;
import com.QuantaSoftSP.app.Services.IDeporteService;

@CrossOrigin(origins = {AuthLibrary.APLICACION_ENDPOINT_URL,AuthLibrary.APLICACION_ENDPOINT_URL_ALL})
@RestController
@RequestMapping(AuthLibrary.APPLICACION_ROUTER)
public class DeporteRestController {
	@Autowired
	private IDeporteService deporteService;
	
	@GetMapping("/deporte")
	public List<Deporte> index()
	{
		return deporteService.findAll();
	}
	@GetMapping("/deporte/{id}")
	public ResponseEntity<?> show(@PathVariable Long id)
	{
		Deporte deporte = null;
		Map<String, Object> response = new HashMap<>();
		try
		{
			deporte = deporteService.findById(id);
		}
		catch (DataAccessException e)
		{
			response.put("mensaje", "Error al consultar la base de datos.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR );
		}
		if (deporte==null)
		{
			response.put("mensaje", "El deporte ID:".concat(id.toString().concat(" No existe en la Base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Deporte>(deporte, HttpStatus.OK);
	}

	@PostMapping("/deporte")
	public ResponseEntity<?> create(@RequestBody Deporte deporte)
	{
		Deporte deporteNew = null;
		Map<String, Object> response = new HashMap<>();
		try
		{
			deporteNew = deporteService.save(deporte);
		}catch (DataAccessException e)
		{
			response.put("mensaje", "Error al realizar el insert en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR );
		}
		response.put("mensaje", "El usuario se ha creado con exito!");
		response.put("deporte", deporteNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	@PutMapping("/deporte/{id}")

	public ResponseEntity<?> update(@RequestBody Deporte deporte, @PathVariable Long id)
	{
		Deporte deporteActual = deporteService.findById(id);
		Deporte deporteUpdate = null;

		Map<String, Object> response = new HashMap<>();

		if (deporteActual==null)
		{
			response.put("mensaje", "Error: no se pudo editar, el deporte ID:".concat(id.toString().concat(" No existe en la Base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {

			deporteActual.setNombre(deporte.getNombre());
			deporteActual.setDescripcion(deporte.getDescripcion());
			deporteActual.setEstActivo(deporte.getEstActivo());
			deporteUpdate=	deporteService.save(deporteActual);
		}catch (DataAccessException e)
		{
			response.put("mensaje", "Error al actualizar el deporte en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR );
		}
		response.put("mensaje", "Deporte actualizado con éxtio!");
		response.put("cliente", deporteUpdate);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}


	@DeleteMapping("/deporte/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> delete(@PathVariable Long id)
	{
		Map<String, Object> response = new HashMap<>();
		try
		{
			deporteService.delete(id);
		}
		catch (DataAccessException e)
		{
			response.put("mensaje", "Error al eliminar el deporte en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR );
		}
		response.put("mensaje", "Deporte eliminado con exito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
