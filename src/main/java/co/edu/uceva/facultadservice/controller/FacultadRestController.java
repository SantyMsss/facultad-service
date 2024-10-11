package co.edu.uceva.facultadservice.controller;


import co.edu.uceva.facultadservice.model.entities.Facultad;
import co.edu.uceva.facultadservice.model.service.FacultadServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/facultad-service")
public class FacultadRestController {

    @Autowired
    private FacultadServiceImpl facultadService;


    /**
     * Este metodo se encarga de retornar una lista de todas las facultades
     *
     * @return
     */


    @GetMapping("/facultades")
    public List<Facultad> listar() {
        return this.facultadService.listar();
    }


    @GetMapping("/facultades/{id}")
    public ResponseEntity<Facultad> buscarFacultad(@PathVariable Long id) {
        try {
            Facultad facultad = facultadService.findById(id);
            return ResponseEntity.ok(facultad);
        } catch (Exception e) {
            // error o devolver un mensaje de error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @DeleteMapping("/facultades/{id}")
    public ResponseEntity<?> eliminarPais(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        Facultad facultades = null;
        try {
            facultades = this.facultadService.findById(id);
            if (facultades == null) {
                response.put("mensaje", "La facultad con ID " + id + " no existe.");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            // Si existe, eliminar el país
            this.facultadService.delete(facultades);
            response.put("mensaje", "La facultad con ID " + id + " ha sido eliminada con éxito.");
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response.put("mensaje", "Error al buscar la facultad.");
            response.put("error", e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

    @PutMapping("/facultades/{id}")
    public ResponseEntity<?> actualizarFacultad(@RequestBody Facultad facultad) {
            try {
                Facultad facultades = this.facultadService.save(facultad);
                return ResponseEntity.ok(facultades);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar la facultad: " + e.getMessage());
            }
        }
    }

}
