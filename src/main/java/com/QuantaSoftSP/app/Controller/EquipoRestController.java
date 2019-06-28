package com.QuantaSoftSP.app.Controller;


import com.QuantaSoftSP.app.Entity.Deporte;
import com.QuantaSoftSP.app.Entity.Equipo;
import com.QuantaSoftSP.app.Entity.Pais;
import com.QuantaSoftSP.app.Entity.UsuarioDelegado;
import com.QuantaSoftSP.app.Library.AuthLibrary;
import com.QuantaSoftSP.app.Services.IEquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = {AuthLibrary.APLICACION_ENDPOINT_URL,AuthLibrary.APLICACION_ENDPOINT_URL_ALL})
@RestController
@RequestMapping(AuthLibrary.APPLICACION_ROUTER)
public class EquipoRestController {
    @Autowired
    private IEquipoService equipoService;
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/equipos")
    public List<Equipo> index()
    {
        return equipoService.findAll();
    }
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/equipos/{id}")
    public ResponseEntity<?> show(@PathVariable Long id)
    {
        Equipo equipo = null;
        Map<String, Object> response = new HashMap<>();
        try {
            equipo = equipoService.findById(id);
            }
        catch(DataAccessException e)
        {
            response.put("mensaje", "Error al consultar la base de datos.");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR );
        }
        if (equipo==null)
        {
            response.put("mensaje", "El equipo ID:".concat(id.toString().concat(" No existe en la Base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Equipo>(equipo, HttpStatus.OK);

    }
    @PostMapping("/equipos")
    public ResponseEntity<?> create(@Valid @RequestBody Equipo equipo, BindingResult result)
    {
        Equipo equipoNew = null;
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
            equipoNew = equipoService.save(equipo);
        }catch (DataAccessException e)
        {
            response.put("mensaje", "Error al realizar el registro en la base de datos!");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR );
        }
        response.put("mensaje", "El equipo se ha creado con exito!");
        response.put("usuario", equipoNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping("/equipos/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Equipo equipo, BindingResult result, @PathVariable Long id)
    {
        Equipo equipoActual = equipoService.findById(id);
        Equipo equipoUpdate = null;

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
        if (equipoActual==null)
        {
            response.put("mensaje", "Error: no se pudo editar, el equipo ID:".concat(id.toString().concat(" No existe en la Base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {
            equipoActual.setNombre(equipo.getNombre());
            equipoActual.setdDechaRegistro(equipo.getdDechaRegistro());
            equipoActual.setDeporte(equipo.getDeporte());
            equipoActual.setUserDelEquipo(equipo.getUserDelEquipo());
            equipoActual.setPaisEquipo(equipo.getPaisEquipo());
            equipoUpdate=	equipoService.save(equipoActual);
        }catch (DataAccessException e)
        {
            response.put("mensaje", "Error al actualizar el equipo en la base de datos!");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR );
        }
        response.put("mensaje", "Equipo actualizado con éxtio!");
        response.put("equipo", equipoUpdate);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/equipos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        Map<String, Object> response = new HashMap<>();
        try
        {
            equipoService.delete(id);
        }
        catch (DataAccessException e)
        {
            response.put("mensaje", "Error al eliminar el equipo en la base de datos!");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR );
        }
        response.put("mensaje", "Equipo eliminado con exito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/equipos/pais")
    public List<Pais> listarPaisEquipo()
    {
        return equipoService.findAllPaisEquipo();
    }
    @GetMapping("/equipos/deporte")
    public List<Deporte> listarDeporteEquipo()
    {
        return equipoService.findAllDeporteEquipo();
    }
    @GetMapping("/equipos/usuariodel/{id}")
    public UsuarioDelegado listarUsuarioDelEquipo(@PathVariable Long id)
    {
        return equipoService.findUserUsuarioDelegadoEquipo(id);
    }
}
