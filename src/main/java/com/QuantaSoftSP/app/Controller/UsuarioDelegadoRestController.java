package com.QuantaSoftSP.app.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.QuantaSoftSP.app.Entity.Deporte;
import com.QuantaSoftSP.app.Entity.Usuario;
import com.QuantaSoftSP.app.Entity.UsuarioJuez;
import com.QuantaSoftSP.app.Library.AuthLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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

import com.QuantaSoftSP.app.Entity.UsuarioDelegado;
import com.QuantaSoftSP.app.Services.IUsuarioDelegadoService;

import javax.validation.Valid;


@CrossOrigin(origins = {AuthLibrary.APLICACION_ENDPOINT_URL,AuthLibrary.APLICACION_ENDPOINT_URL_ALL})
@RestController
@RequestMapping(AuthLibrary.APPLICACION_ROUTER)
public class UsuarioDelegadoRestController {
	@Autowired
	private IUsuarioDelegadoService usuarioDelegadoService;

	@GetMapping("/usuariosDelegado")
	public List<UsuarioDelegado> index()
	{
		return usuarioDelegadoService.findAll();
	}

	@GetMapping("/usuariosDelegado/{id}")
	public ResponseEntity<?> show(@PathVariable Long id)
	{
		UsuarioDelegado usuarioDelegado = null;
		Map<String, Object> response = new HashMap<>();
		try {
			usuarioDelegado = usuarioDelegadoService.findById(id);
		}
		catch(DataAccessException e)
		{
			response.put("mensaje", "Error al consultar la base de datos.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR );
		}
		if (usuarioDelegado==null)
		{
			response.put("mensaje", "El delegado ID:".concat(id.toString().concat(" No existe en la Base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<UsuarioDelegado>(usuarioDelegado, HttpStatus.OK);

	}
	@PostMapping("/usuariosDelegado")
	public ResponseEntity<?> create(@Valid @RequestBody UsuarioDelegado usuarioDelegado, BindingResult result)
	{
		UsuarioDelegado usuarioDelegadoNew = null;
		Map<String, Object> response = new HashMap<>();
		if (result.hasErrors())
		{
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '"+err.getField()+"' "+err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST );
		}

		try
		{
			usuarioDelegadoNew = usuarioDelegadoService.save(usuarioDelegado);
		}
		catch (DataAccessException e)
		{
			response.put("mensaje", "Error al realizar el registro en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR );
		}
		response.put("mensaje", "El delegado se ha creado con exito!");
		response.put("usuarioDelegado", usuarioDelegadoNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PutMapping("/usuariosDelegado/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody UsuarioDelegado usuarioDelegado, BindingResult result, @PathVariable Long id)
	{
		UsuarioDelegado usuarioDelegadoActual = usuarioDelegadoService.findById(id);
		UsuarioDelegado usuarioDelegadoUpdate = null;

		Map<String, Object> response = new HashMap<>();
		if (result.hasErrors())
		{
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '"+err.getField()+"' "+err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST );
		}
		if (usuarioDelegadoActual==null)
		{
			response.put("mensaje", "Error: no se pudo editar, el delegdo ID:".concat(id.toString().concat(" No existe en la Base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			usuarioDelegadoActual.setCargo(usuarioDelegado.getCargo());
			usuarioDelegadoActual.setEstActivo(usuarioDelegado.getEstActivo());
			usuarioDelegadoActual.setDeporte(usuarioDelegado.getDeporte());
			usuarioDelegadoActual.setUserDelUsuario(usuarioDelegado.getUserDelUsuario());
			usuarioDelegadoUpdate=	usuarioDelegadoService.save(usuarioDelegadoActual);
		}catch (DataAccessException e)
		{
			response.put("mensaje", "Error al actualizar el delegado en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR );
		}
		response.put("mensaje", "Delegado deportivo actualizado con éxtio!");
		response.put("usuarioDelegado", usuarioDelegadoUpdate);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@DeleteMapping("/usuariosDelegado/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> delete(@PathVariable Long id)
	{
		Map<String, Object> response = new HashMap<>();
		try
		{
			usuarioDelegadoService.delete(id);
		}
		catch (DataAccessException e)
		{
			response.put("mensaje", "Error al eliminar el delegado en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR );
		}
		response.put("mensaje", "Delegado deportivo eliminado con exito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@GetMapping("/usuariosDelegado/deporte")
	public List<Deporte> listarDeporteUsuarioJuez()
	{
		return usuarioDelegadoService.findAllDeporteUsuarioJuez();
	}


	@GetMapping("/usuariosDelegado/user/{id}")
	public Usuario mostrarUsuario(@PathVariable Long id)
	{
		return usuarioDelegadoService.findUserUsuarioJuez(id);
	}


}
