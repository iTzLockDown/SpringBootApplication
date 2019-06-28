package com.QuantaSoftSP.app.Controller;

import java.util.List;

import com.QuantaSoftSP.app.Entity.Deporte;
import com.QuantaSoftSP.app.Entity.Usuario;
import com.QuantaSoftSP.app.Library.AuthLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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


import com.QuantaSoftSP.app.Entity.UsuarioDeportista;
import com.QuantaSoftSP.app.Services.IUsuarioDeportistaService;

@CrossOrigin(origins = {AuthLibrary.APLICACION_ENDPOINT_URL,AuthLibrary.APLICACION_ENDPOINT_URL_ALL})
@RestController
@RequestMapping(AuthLibrary.APPLICACION_ROUTER)

public class UsuarioDeportistaRestController {
	@Autowired
	private IUsuarioDeportistaService usuarioDeportistaService;
	
	@GetMapping("/usuarioDeportista")
	public List<UsuarioDeportista> index()
	{
		return usuarioDeportistaService.findAll();
	}
	@GetMapping("/usuarioDeportista/{id}")
	public UsuarioDeportista show(@PathVariable Long id)
	{
		return usuarioDeportistaService.findById(id);
	}
	
	@PostMapping("/usuarioDeportista")
	@ResponseStatus(HttpStatus.CREATED)
	public UsuarioDeportista create(@RequestBody UsuarioDeportista usuarioDeportista)
	{
		return usuarioDeportistaService.save(usuarioDeportista);
	}
	
	@PutMapping("/usuarioDeportista/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public UsuarioDeportista update(@RequestBody UsuarioDeportista usuarioDeportista, @PathVariable Long id)
	{
		UsuarioDeportista usuarioDeportistaActual = usuarioDeportistaService.findById(id);

		usuarioDeportistaActual.setAltura(usuarioDeportista.getAltura());
		usuarioDeportistaActual.setPeso(usuarioDeportista.getPeso());
		usuarioDeportistaActual.setObservacion(usuarioDeportista.getObservacion());
		usuarioDeportistaActual.setEstActivo(usuarioDeportista.getEstActivo());
		usuarioDeportistaActual.setDeporte(usuarioDeportista.getDeporte());
		usuarioDeportistaActual.setUserDepUsuario(usuarioDeportista.getUserDepUsuario());

		return usuarioDeportistaService.save(usuarioDeportistaActual);
	}
	
	@DeleteMapping("/usuarioDeportista/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id)
	{
		usuarioDeportistaService.delete(id);
	}

	@GetMapping("/usuarioDeportista/deporte")
	public List<Deporte> listarPaisUsuario()
	{
		return usuarioDeportistaService.findAllDeporteUsuarioDepor();
	}

	@GetMapping("/usuarioDeportista/user/{id}")
	public Usuario mostrarUsuario(@PathVariable Long id)
	{
		return usuarioDeportistaService.findUserUsuarioDepor(id);
	}
}
