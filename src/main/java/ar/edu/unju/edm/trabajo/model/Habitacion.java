package ar.edu.unju.edm.trabajo.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity//indica que se almacena entidades en base de datos
@Component//para identificar objetos
@Getter
@Setter
public class Habitacion {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long codigo; // Clave primaria

  @Enumerated(EnumType.STRING)
  private Tipo tipo;

  private String servicios;
  private String descripcion;
  private Boolean estado;
}

enum Tipo {
  SIMPLE, DOBLE, SUITE
}