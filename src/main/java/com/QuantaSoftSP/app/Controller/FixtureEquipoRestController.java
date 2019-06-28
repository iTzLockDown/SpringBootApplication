package com.QuantaSoftSP.app.Controller;

import com.QuantaSoftSP.app.Entity.FixtureEquipo;
import com.QuantaSoftSP.app.Entity.UsuarioJuez;
import com.QuantaSoftSP.app.Library.AuthLibrary;
import com.QuantaSoftSP.app.Services.IFixtureEquipoService;
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
public class FixtureEquipoRestController {

    @Autowired
    private IFixtureEquipoService fixtureEquipoService;

    @GetMapping("/fixtureCompeticionesEquipos")
    public List<FixtureEquipo> index()
    {
        return fixtureEquipoService.findAll();
    }

    @GetMapping("/fixtureCompeticionesEquipos/{id}")
    public ResponseEntity<?> show(@PathVariable Long id)
    {
        FixtureEquipo fixtureEquipo = null;
        Map<String, Object> response = new HashMap<>();
        try {
            fixtureEquipo = fixtureEquipoService.findById(id);
        }
        catch(DataAccessException e)
        {
            response.put("mensaje", "Error al consultar la base de datos.");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR );
        }
        if (fixtureEquipo==null)
        {
            response.put("mensaje", "El Fixture por Equipos ID:".concat(id.toString().concat(" No existe en la Base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<FixtureEquipo>(fixtureEquipo, HttpStatus.OK);

    }
    @PostMapping("/fixtureCompeticionesEquipos")
    public ResponseEntity<?> create(@Valid @RequestBody FixtureEquipo fixtureEquipo, BindingResult result)
    {
        FixtureEquipo fixtureEquipoNew = null;
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
            fixtureEquipoNew = fixtureEquipoService.save(fixtureEquipo);
        }catch (DataAccessException e)
        {
            response.put("mensaje", "Error al realizar el registro en la base de datos!");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR );
        }
        response.put("mensaje", "El fixture por equipos se ha creado con exito!");
        response.put("fixture", fixtureEquipoNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping("/fixtureCompeticionesEquipos/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody FixtureEquipo fixtureEquipo, BindingResult result, @PathVariable Long id)
    {
        FixtureEquipo fixtureEquipoActual = fixtureEquipoService.findById(id);
        FixtureEquipo fixtureEquipoUpdate = null;

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
        if (fixtureEquipoActual==null)
        {
            response.put("mensaje", "Error: no se pudo editar, el fixture ID:".concat(id.toString().concat(" No existe en la Base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {
            fixtureEquipoActual.setHoraFixture(fixtureEquipo.getHoraFixture());
            fixtureEquipoActual.setLugarFixture(fixtureEquipo.getLugarFixture());
            fixtureEquipoActual.setCompeticion(fixtureEquipo.getCompeticion());
            fixtureEquipoActual.setFechaReg(fixtureEquipo.getFechaReg());
            fixtureEquipoActual.setUserJuezFixture(fixtureEquipo.getUserJuezFixture());
            fixtureEquipoUpdate=	fixtureEquipoService.save(fixtureEquipoActual);
        }catch (DataAccessException e)
        {
            response.put("mensaje", "Error al actualizar el fixtre en la base de datos!");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR );
        }
        response.put("mensaje", "Fixture actualizado con éxtio!");
        response.put("fixture", fixtureEquipoUpdate);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/fixtureCompeticionesEquipos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        Map<String, Object> response = new HashMap<>();
        try
        {
            fixtureEquipoService.delete(id);
        }
        catch (DataAccessException e)
        {
            response.put("mensaje", "Error al eliminar el fixture en la base de datos!");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR );
        }
        response.put("mensaje", "Fixture eliminado con exito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @GetMapping("/fixtureCompeticionesEquipos/juez")
    public List<UsuarioJuez> listarPaisUsuario()
    {
        return fixtureEquipoService.findAllFixtureEquipoUsuarioJuez();
    }



}
