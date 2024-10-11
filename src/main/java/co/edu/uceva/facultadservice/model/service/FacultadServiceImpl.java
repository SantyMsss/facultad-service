package co.edu.uceva.facultadservice.model.service;

import co.edu.uceva.facultadservice.model.dao.FacultadDao;
import co.edu.uceva.facultadservice.model.entities.Facultad;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class FacultadServiceImpl implements FacultadService {
    @Autowired
    FacultadDao facultadDao;


    /**
     * Este metodo se encarga de listar las facultades
     *
     * @return retorna una lista de facultades
     */

    @Override
    public List<Facultad> listar() {
        return (List<Facultad>) facultadDao.findAll();
    }


    @Override
    public Facultad findById(Long id) {
        return facultadDao.findById(id).orElse(null);
    }

    @Override
    public void delete(Facultad facultad) { facultadDao.delete(facultad);
    }

    @Override
    public Facultad save(Facultad facultad) {
        return facultadDao.save(facultad);
    }

}


