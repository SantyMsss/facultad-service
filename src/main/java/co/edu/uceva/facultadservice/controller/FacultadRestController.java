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
     * @return
     */


    @GetMapping("/facultades")
    public List<Facultad> listar(){
        return this.facultadService.listar();
    }

    @PostMapping("/facultades")
    public ResponseEntity<?> guardarFacultad(@RequestBody Facultad facultad) {
        try {
            Facultad facultades = this.facultadService.save(facultad);
            return ResponseEntity.ok(facultades);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar la facultad: " + e.getMessage());
        }
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
    public ResponseEntity<?> eliminarFacultad(@PathVariable Long id) {
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
    public ResponseEntity<?> actualizarFacultad(@RequestBody Facultad facultad, @PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        Facultad facultadActual = null;
        try {
            facultadActual = this.facultadService.findById(id);
            if (facultadActual == null) {
                response.put("mensaje", "La facultad con ID " + id + " no existe.");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            facultadActual.setCodigoFacu(facultad.getCodigoFacu());
            facultadActual.setNombreFacu(facultad.getNombreFacu());
            facultadActual.setDecano(facultad.getDecano());
            facultadActual.setModalidad(facultad.getModalidad());
            facultadActual.setProyecInvestFacu(facultad.getProyecInvestFacu());
            facultadActual.setDescripcion(facultad.getDescripcion());
            facultadActual.setFechaCrea(facultad.getFechaCrea());
            facultadActual.setTelefono(facultad.getTelefono());
            facultadActual.setCorreo(facultad.getCorreo());
            facultadActual.setProgramasAcademicos(facultad.getProgramasAcademicos());
            facultadActual.setCalendarioAcademico(facultad.getCalendarioAcademico());

            Facultad facultadActualizada = this.facultadService.save(facultadActual);
            response.put("mensaje", "La facultad ha sido actualizada con éxito.");
            response.put("facultad", facultadActualizada);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("mensaje", "Error al actualizar la facultad.");
            response.put("error", e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }
}
