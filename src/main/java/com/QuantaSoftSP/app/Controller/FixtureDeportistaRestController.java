package com.QuantaSoftSP.app.Controller;

import com.QuantaSoftSP.app.Entity.FixtureDeportista;
import com.QuantaSoftSP.app.Entity.FixtureEquipo;
import com.QuantaSoftSP.app.Entity.UsuarioJuez;
import com.QuantaSoftSP.app.Library.AuthLibrary;
import com.QuantaSoftSP.app.Services.IFixtureDeportistaService;
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
public class FixtureDeportistaRestController {

    @Autowired
    private IFixtureDeportistaService fixtureDeportistaService;

    @GetMapping("/fixtureCompeticionesDeportista")
    public List<FixtureDeportista> index()
    {
        return fixtureDeportistaService.findAll();
    }

    @GetMapping("/fixtureCompeticionesDeportista/{id}")
    public ResponseEntity<?> show(@PathVariable Long id)
    {
        FixtureDeportista fixtureDeportista = null;
        Map<String, Object> response = new HashMap<>();
        try {
            fixtureDeportista = fixtureDeportistaService.findById(id);
        }
        catch(DataAccessException e)
        {
            response.put("mensaje", "Error al consultar la base de datos.");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR );
        }
        if (fixtureDeportista==null)
        {
            response.put("mensaje", "El Fixture por Deportista ID:".concat(id.toString().concat(" No existe en la Base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<FixtureDeportista>(fixtureDeportista, HttpStatus.OK);

    }
    @PostMapping("/fixtureCompeticionesDeportista")
    public ResponseEntity<?> create(@Valid @RequestBody FixtureDeportista fixtureDeportista, BindingResult result)
    {
        FixtureDeportista fixtureDeportistaNew = null;
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
            fixtureDeportistaNew = fixtureDeportistaService.save(fixtureDeportista);
        }catch (DataAccessException e)
        {
            response.put("mensaje", "Error al realizar el registro en la base de datos!");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR );
        }
        response.put("mensaje", "El fixture por deportista se ha creado con exito!");
        response.put("fixture", fixtureDeportistaNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping("/fixtureCompeticionesDeportista/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody FixtureDeportista fixtureDeportista, BindingResult result, @PathVariable Long id)
    {
        FixtureDeportista fixtureDeportistaActual = fixtureDeportistaService.findById(id);
        FixtureDeportista fixtureDeportistaUpdate = null;

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
        if (fixtureDeportistaActual==null)
        {
            response.put("mensaje", "Error: no se pudo editar, el fixture ID:".concat(id.toString().concat(" No existe en la Base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {
            fixtureDeportistaActual.setHoraFixture(fixtureDeportista.getHoraFixture());
            fixtureDeportistaActual.setLugarFixture(fixtureDeportista.getLugarFixture());
            fixtureDeportistaActual.setCompeticion(fixtureDeportista.getCompeticion());
            fixtureDeportistaActual.setFechaReg(fixtureDeportista.getFechaReg());
            fixtureDeportistaActual.setUserJuezFixture(fixtureDeportista.getUserJuezFixture());
            fixtureDeportistaUpdate=	fixtureDeportistaService.save(fixtureDeportistaActual);
        }catch (DataAccessException e)
        {
            response.put("mensaje", "Error al actualizar el fixture en la base de datos!");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR );
        }
        response.put("mensaje", "Fixture actualizado con éxtio!");
        response.put("fixture", fixtureDeportistaUpdate);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/fixtureCompeticionesDeportista/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        Map<String, Object> response = new HashMap<>();
        try
        {
            fixtureDeportistaService.delete(id);
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

    @GetMapping("/fixtureCompeticionesDeportista/juez")
    public List<UsuarioJuez> listarPaisUsuario()
    {
        return fixtureDeportistaService.findAllFixtureDeportistaUsuarioJuez();
    }

}
