package ar.edu.unju.edm.trabajo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.edm.trabajo.model.Reserva;

@Service
public interface IReservaService {

  public Reserva guardarReserva(Reserva reserva);

  public List<Reserva> listarReservas();

  public void eliminarReserva(Long codigo);
}
