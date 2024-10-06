package co.edu.uceva.facultadservice.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Facultad {
    @Id
    private Long codigoFacu;
    private String nombreFacu;
    private String decano;
    private String modalidad;
    private String proyecInvestFacu;
    private String descripcion;
    private Date fechaCrea;
    private int telefono;
    private String correo;
    private String programasAcademicos;
    private String calendarioAcademico; // Que tipo de dato es este?
}