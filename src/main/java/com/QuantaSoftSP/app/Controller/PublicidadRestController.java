package com.QuantaSoftSP.app.Controller;

import com.QuantaSoftSP.app.Entity.Publicidad;
import com.QuantaSoftSP.app.Entity.Sponsor;
import com.QuantaSoftSP.app.Library.AuthLibrary;
import com.QuantaSoftSP.app.Services.IPublicidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class PublicidadRestController {
    @Autowired
    private IPublicidadService publicidadService;

    @GetMapping("/publicidad")
    public List<Publicidad> index()
    {
        return publicidadService.findAll();
    }

    @GetMapping("/publicidad/{id}")
    public ResponseEntity<?> show(@PathVariable Long id)
    {
        Publicidad publicidad = null;
        Map<String, Object> response = new HashMap<>();
        try {
            publicidad = publicidadService.findById(id);
        }
        catch(DataAccessException e)
        {
            response.put("mensaje", "Error al consultar la base de datos.");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR );
        }
        if (publicidad==null)
        {
            response.put("mensaje", "La publicidad ID:".concat(id.toString().concat(" No existe en la Base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Publicidad>(publicidad, HttpStatus.OK);

    }
    @PostMapping("/publicidad")
    public ResponseEntity<?> create(@Valid @RequestBody Publicidad publicidad, BindingResult result)
    {
        Publicidad publicidadNew = null;
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
            publicidadNew = publicidadService.save(publicidad);
        }catch (DataAccessException e)
        {
            response.put("mensaje", "Error al realizar el registro en la base de datos!");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR );
        }
        response.put("mensaje", "La publidad se ha creado con exito!");
        response.put("publicidad", publicidadNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping("/publicidad/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Publicidad publicidad, BindingResult result, @PathVariable Long id)
    {
        Publicidad publicidadActual = publicidadService.findById(id);
        Publicidad publicidadUpdate = null;

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
        if (publicidadActual==null)
        {
            response.put("mensaje", "Error: no se pudo editar, la publicidad ID:".concat(id.toString().concat(" No existe en la Base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {
            publicidadActual.setVideoPublicidad(publicidad.getVideoPublicidad());
            publicidadActual.setImgPublicidad(publicidad.getImgPublicidad());
            publicidadActual.setSponsorPublicidad(publicidad.getSponsorPublicidad());
            publicidadUpdate=	publicidadService.save(publicidadActual);
        }catch (DataAccessException e)
        {
            response.put("mensaje", "Error al actualizar la publicidad en la base de datos!");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR );
        }
        response.put("mensaje", "Publicidad actualizado con éxtio!");
        response.put("publicidad", publicidadUpdate);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/publicidad/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        Map<String, Object> response = new HashMap<>();
        try
        {
            publicidadService.delete(id);
        }
        catch (DataAccessException e)
        {
            response.put("mensaje", "Error al eliminar la publicidad en la base de datos!");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR );
        }
        response.put("mensaje", "Publicidad eliminado con exito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @GetMapping("/publicidad/sponsor")
    public List<Sponsor> listarSponsorPublicidad()
    {
        return publicidadService.findAllSponsorPublicidad();
    }
}
