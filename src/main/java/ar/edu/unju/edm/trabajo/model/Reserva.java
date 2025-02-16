package ar.edu.unju.edm.trabajo.model;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotNull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Component
@Getter
@Setter
public class Reserva {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long codigo;
  @NotNull(message = "La fecha de la reserva no puede ser nula")
  private LocalDate fechaReserva;

  @ManyToOne
  private Huesped huesped;

  @ManyToOne
  private Habitacion habitacion;

  private Boolean estado;
}
