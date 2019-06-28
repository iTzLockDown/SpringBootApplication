package com.QuantaSoftSP.app.Controller;

import com.QuantaSoftSP.app.Entity.Sponsor;
import com.QuantaSoftSP.app.Library.AuthLibrary;
import com.QuantaSoftSP.app.Services.ISponsorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {AuthLibrary.APLICACION_ENDPOINT_URL,AuthLibrary.APLICACION_ENDPOINT_URL_ALL})
@RestController
@RequestMapping(AuthLibrary.APPLICACION_ROUTER)
public class SponsorRestController {

    @Autowired
    private ISponsorService sponsorService;

    @GetMapping("/sponsor")
    public List<Sponsor> index()
    {
        return sponsorService.findAll();
    }

    @GetMapping("/sponsor/{id}")
    public ResponseEntity<?> show(@PathVariable Long id)
    {
        Sponsor sponsor = null;
        Map<String, Object> response = new HashMap<>();
        try {
            sponsor = sponsorService.findById(id);
        }
        catch(DataAccessException e)
        {
            response.put("mensaje", "Error al consultar la base de datos.");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR );
        }
        if (sponsor==null)
        {
            response.put("mensaje", "El sponsor ID:".concat(id.toString().concat(" No existe en la Base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Sponsor>(sponsor, HttpStatus.OK);

    }
    @PostMapping("/sponsor")
    public ResponseEntity<?> create(@RequestBody Sponsor sponsor)
    {
        Sponsor sponsorNew = null;
        Map<String, Object> response = new HashMap<>();
        try
        {
            sponsorNew = sponsorService.save(sponsor);
        }catch (DataAccessException e)
        {
            response.put("mensaje", "Error al realizar el insert en la base de datos!");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR );
        }
        response.put("mensaje", "El sponsor se ha creado con exito!");
        response.put("sponsor", sponsorNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping("/sponsor/{id}")

    public ResponseEntity<?> update(@RequestBody Sponsor sponsor, @PathVariable Long id)
    {
        Sponsor sponsorActual = sponsorService.findById(id);
        Sponsor sponsorUpdate = null;

        Map<String, Object> response = new HashMap<>();

        if (sponsorActual==null)
        {
            response.put("mensaje", "Error: no se pudo editar, el sponsor ID:".concat(id.toString().concat(" No existe en la Base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {
            sponsorActual.setNombreCompleto(sponsor.getNombreCompleto());
            sponsorActual.setNombreCorto(sponsor.getNombreCorto());
            sponsorActual.setPagWeb(sponsor.getPagWeb());
            sponsorActual.setNombreMostrar(sponsor.getNombreMostrar());
            sponsorActual.setFilosofia(sponsor.getFilosofia());
            sponsorActual.setPagWeb(sponsor.getPagWeb());
            sponsorActual.setLogoSponsor(sponsor.getLogoSponsor());
            sponsorActual.setEstActivo(sponsor.getEstActivo());
            sponsorUpdate=	sponsorService.save(sponsorActual);
        }catch (DataAccessException e)
        {
            response.put("mensaje", "Error al actualizar el sponsor en la base de datos!");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR );
        }
        response.put("mensaje", "Sponsor actualizado con éxtio!");
        response.put("sponsor", sponsorUpdate);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/sponsor/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        Map<String, Object> response = new HashMap<>();
        try
        {
            sponsorService.delete(id);
        }
        catch (DataAccessException e)
        {
            response.put("mensaje", "Error al eliminar el sponsor en la base de datos!");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR );
        }
        response.put("mensaje", "Sponsor eliminado con exito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

}
