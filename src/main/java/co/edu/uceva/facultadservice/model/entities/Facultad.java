package co.edu.uceva.facultadservice.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Facultad {
    @Id
    private String nombreFacu;
    private String codigoFacu;
    private String decano;
    private String modalidad;
    private String proyecInvestFacu;
    private String descripcion;
    private String fechaCrea;
    private String telefono;
    private String correo;
    private String programasAcademicos;
    private String calendarioAcademico;
}