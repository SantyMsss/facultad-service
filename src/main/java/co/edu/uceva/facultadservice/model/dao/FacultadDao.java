package co.edu.uceva.facultadservice.model.dao;

import co.edu.uceva.facultadservice.model.entities.Facultad;
import org.springframework.data.repository.CrudRepository;

public interface FacultadDao extends CrudRepository<Facultad, Long> {
}
