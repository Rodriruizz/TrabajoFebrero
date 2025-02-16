package ar.edu.unju.edm.trabajo.service.Imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.trabajo.model.Reserva;
import ar.edu.unju.edm.trabajo.repository.HabitacionRepository;
import ar.edu.unju.edm.trabajo.repository.HuespedRepository;
import ar.edu.unju.edm.trabajo.repository.ReservaRepository;
import ar.edu.unju.edm.trabajo.service.IReservaService;

@Service
public class IReservaServiceImp implements IReservaService {
  @Autowired
  ReservaRepository reservaRepository;
  @Autowired
  HuespedRepository pasajeroRepository;
  @Autowired
  HabitacionRepository habitacionRepository;

  @Override
  public Reserva guardarReserva(Reserva reserva) {
    reserva.setEstado(true);
    return reservaRepository.save(reserva);
  }

  @Override
  public List<Reserva> listarReservas() {
    List<Reserva> reservas = (List<Reserva>) reservaRepository.findAll();
    List<Reserva> reservasActivas = new ArrayList<>();
    for (Reserva reserva : reservas) {
      if (reserva.getEstado().equals(true)) {
        reservasActivas.add(reserva);
      }
    }
    return reservasActivas;
  }

  @Override
  public void eliminarReserva(Long codigo) {
    Reserva reserva = reservaRepository.findById(codigo)
        .orElseThrow(() -> new RuntimeException("reserva no encontrada"));
    reserva.setEstado(false);
    reservaRepository.save(reserva);
  }

}
