package co.edu.uceva.facultadservice.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Facultad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo_facu;
    private String nombre_facu;
    private String decano;
    private String modalidad;
    private String proyec_invest_facu;
    private String descripcion;
    private Date fecha_crea;
    private int telefono;
    private String correo;
    private String prog_academico;
    private String calendar_academico;
}