package co.edu.uceva.facultadservice.controller;


import co.edu.uceva.facultadservice.model.entities.Facultad;
import co.edu.uceva.facultadservice.model.service.FacultadServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/facultad-service")
public class FacultadRestController {
    @Autowired
    private FacultadServiceImpl facultadService; // Inyecto el servicio de la entidad Pais para realizar las operaciones CRUD


}
