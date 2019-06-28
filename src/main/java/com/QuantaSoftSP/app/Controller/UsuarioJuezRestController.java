package com.QuantaSoftSP.app.Controller;

import com.QuantaSoftSP.app.Entity.Deporte;
import com.QuantaSoftSP.app.Entity.Usuario;
import com.QuantaSoftSP.app.Entity.UsuarioJuez;
import com.QuantaSoftSP.app.Library.AuthLibrary;
import com.QuantaSoftSP.app.Services.IUsuarioJuezService;
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

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = {AuthLibrary.APLICACION_ENDPOINT_URL,AuthLibrary.APLICACION_ENDPOINT_URL_ALL})
@RestController
@RequestMapping(AuthLibrary.APPLICACION_ROUTER)
public class UsuarioJuezRestController {
    @Autowired
    private IUsuarioJuezService usuarioJuezService;

    @GetMapping("/usuariosJuez")
    public List<UsuarioJuez> index()
    {
        return usuarioJuezService.findAll();
    }

    @GetMapping("/usuariosJuez/{id}")
    public ResponseEntity<?> show(@PathVariable Long id)
    {
        UsuarioJuez usuarioJuez = null;
        Map<String, Object> response = new HashMap<>();
        try {
            usuarioJuez = usuarioJuezService.findById(id);
        }
        catch(DataAccessException e)
        {
            response.put("mensaje", "Error al consultar la base de datos.");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR );
        }
        if (usuarioJuez==null)
        {
            response.put("mensaje", "El juez deportivo ID:".concat(id.toString().concat(" No existe en la Base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<UsuarioJuez>(usuarioJuez, HttpStatus.OK);

    }
    @PostMapping("/usuariosJuez")
    public ResponseEntity<?> create(@Valid @RequestBody UsuarioJuez usuarioJuez, BindingResult result)
    {
        UsuarioJuez usuarioJuezNew = null;
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
            usuarioJuezNew = usuarioJuezService.save(usuarioJuez);
        }catch (DataAccessException e)
        {
            response.put("mensaje", "Error al realizar el registro en la base de datos!");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR );
        }
        response.put("mensaje", "El usuario se ha creado con exito!");
        response.put("usuario", usuarioJuezNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping("/usuariosJuez/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody UsuarioJuez usuarioJuez, BindingResult result, @PathVariable Long id)
    {
        UsuarioJuez usuarioJuezActual = usuarioJuezService.findById(id);
        UsuarioJuez usuarioJuezUpdate = null;

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
        if (usuarioJuezActual==null)
        {
            response.put("mensaje", "Error: no se pudo editar, el juez deportivo ID:".concat(id.toString().concat(" No existe en la Base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {
            usuarioJuezActual.setDeporte(usuarioJuez.getDeporte());
            usuarioJuezActual.setObservaciones(usuarioJuez.getObservaciones());
            usuarioJuezActual.setDescripcion(usuarioJuez.getDescripcion());
            usuarioJuezActual.setUserJuezUsuario(usuarioJuez.getUserJuezUsuario());
            usuarioJuezUpdate=	usuarioJuezService.save(usuarioJuezActual);
        }catch (DataAccessException e)
        {
            response.put("mensaje", "Error al actualizar el juez deportivo en la base de datos!");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR );
        }
        response.put("mensaje", "Juez deportivo actualizado con éxtio!");
        response.put("usuarioJuez", usuarioJuezUpdate);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/usuariosJuez/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        Map<String, Object> response = new HashMap<>();
        try
        {
            usuarioJuezService.delete(id);
        }
        catch (DataAccessException e)
        {
            response.put("mensaje", "Error al eliminar el juez deportivo en la base de datos!");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR );
        }
        response.put("mensaje", "Juez deportivo eliminado con exito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @GetMapping("/usuariosJuez/deporte")
    public List<Deporte> listarDeporteUsuarioJuez()
    {
        return usuarioJuezService.findAllDeporteUsuarioDepor();
    }


    @GetMapping("/usuariosJuez/user/{id}")
    public Usuario mostrarUsuario(@PathVariable Long id)
    {
        return usuarioJuezService.findUserUsuarioJuez(id);
    }
}
