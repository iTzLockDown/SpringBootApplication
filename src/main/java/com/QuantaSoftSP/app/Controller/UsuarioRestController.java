package com.QuantaSoftSP.app.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.QuantaSoftSP.app.Entity.Pais;
import com.QuantaSoftSP.app.Library.AuthLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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

import com.QuantaSoftSP.app.Entity.Usuario;
import com.QuantaSoftSP.app.Services.IUsuarioService;

import javax.validation.Valid;

@CrossOrigin(origins = {AuthLibrary.APLICACION_ENDPOINT_URL,AuthLibrary.APLICACION_ENDPOINT_URL_ALL})
@RestController
@RequestMapping(AuthLibrary.APPLICACION_ROUTER)
public class UsuarioRestController {
	
	@Autowired
	private IUsuarioService usuarioService;

	@GetMapping("/usuarios")
	public List<Usuario> index()
	{
		return usuarioService.findAll();
	}


	@Secured({"ROLE_ADMIN"})
	@GetMapping("/usuarios/{id}")
	public ResponseEntity<?> show(@PathVariable Long id)
	{
		Usuario usuario = null;
		Map<String, Object> response = new HashMap<>();
		try {
			usuario = usuarioService.findById(id);
		}
		catch(DataAccessException e)
		{
			response.put("mensaje", "Error al consultar la base de datos.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR );
		}
		if (usuario==null)
		{
			response.put("mensaje", "El usuario ID:".concat(id.toString().concat(" No existe en la Base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);

	}

	@Secured({"ROLE_ADMIN"})
	@PostMapping("/usuarios")
	public ResponseEntity<?> create(@Valid @RequestBody Usuario usuario, BindingResult result)
	{
		Usuario usuarioNew = null;
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
			usuarioNew = usuarioService.save(usuario);
		}catch (DataAccessException e)
		{
			response.put("mensaje", "Error al realizar el registro en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR );
		}
		response.put("mensaje", "El usuario se ha creado con exito!");
		response.put("usuario", usuarioNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	@Secured({"ROLE_ADMIN"})
	@PutMapping("/usuarios/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Usuario usuario, BindingResult result, @PathVariable Long id)
	{
		Usuario usuarioActual = usuarioService.findById(id);
		Usuario usuarioUpdate = null;

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
		if (usuarioActual==null)
		{
			response.put("mensaje", "Error: no se pudo editar, el usuario ID:".concat(id.toString().concat(" No existe en la Base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			usuarioActual.setNombre(usuario.getNombre());
			usuarioActual.setApellidop(usuario.getApellidop());
			usuarioActual.setApellidom(usuario.getApellidom());
			usuarioActual.setSexo(usuario.getSexo());
			usuarioActual.setDni(usuario.getDni());
			usuarioActual.setDireccion(usuario.getDireccion());
			usuarioActual.setTelefono(usuario.getTelefono());
			usuarioActual.setEmail(usuario.getEmail());
			usuarioActual.setPassword(usuario.getPassword());
			usuarioActual.setEstActivo(usuario.getEstActivo());
			usuarioActual.setFechaNac(usuario.getFechaNac());
			usuarioActual.setPaisUser(usuario.getPaisUser());
		    usuarioUpdate=	usuarioService.save(usuarioActual);
		}catch (DataAccessException e)
		{
			response.put("mensaje", "Error al actualizar el usuario en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR );
		}
		response.put("mensaje", "Cliente actualizado con éxtio!");
		response.put("usuario", usuarioUpdate);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	@Secured({"ROLE_ADMIN"})
	@DeleteMapping("/usuarios/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> delete(@PathVariable Long id)
	{
		Map<String, Object> response = new HashMap<>();
		try
		{
			usuarioService.delete(id);
		}
		catch (DataAccessException e)
		{
			response.put("mensaje", "Error al eliminar el usuario en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR );
		}
		response.put("mensaje", "Usuario eliminado con exito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/usuarios/pais")
	public List<Pais> listarPaisUsuario()
	{
		return usuarioService.findAllPaisUsuario();
	}
	
}
