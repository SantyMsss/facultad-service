package co.edu.uceva.facultadservice.model.service;

import co.edu.uceva.facultadservice.model.entities.Facultad;

import java.util.List;

public interface FacultadService {

    public List<Facultad> listar();
    Facultad findById(Long id);
    void delete(Facultad facultad);
    Facultad save(Facultad facultad);
}
