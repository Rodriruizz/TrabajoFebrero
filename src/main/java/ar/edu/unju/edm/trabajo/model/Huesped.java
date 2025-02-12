package ar.edu.unju.edm.trabajo.model;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Component
@Getter
@Setter
public class Huesped {
  @Id
  private String dni;
  private String apellido;
  private String nombre;
  private LocalDate fechaNacimiento;
  private String ciudadProcedencia;
  @Enumerated(EnumType.STRING)
  private Nacionalidad nacionalidad;

  private Boolean estado;
}

enum Nacionalidad {
  ARGENTINA, BRASILERA, CHILENA, PARAGUAYA, URUGUAYA
}