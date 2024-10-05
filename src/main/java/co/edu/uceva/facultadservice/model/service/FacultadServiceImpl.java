package co.edu.uceva.facultadservice.model.service;

import co.edu.uceva.facultadservice.model.dao.FacultadDao;
import org.springframework.beans.factory.annotation.Autowired;

public class FacultadServiceImpl implements FacultadService {
    @Autowired
    FacultadDao facultadDao;
}
