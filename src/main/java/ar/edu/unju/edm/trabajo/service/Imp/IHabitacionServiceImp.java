package ar.edu.unju.edm.trabajo.service.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.trabajo.model.Habitacion;
import ar.edu.unju.edm.trabajo.repository.HabitacionRepository;
import ar.edu.unju.edm.trabajo.service.IHabitacionService;

@Service
public class IHabitacionServiceImp implements IHabitacionService {
  @Autowired
  HabitacionRepository habitacionRepository;

  @Override
  public Habitacion crear(Habitacion habitacion) {
    return habitacionRepository.save(habitacion);
  }

  @Override
  public List<Habitacion> listar() {
    return (List<Habitacion>) habitacionRepository.findAll();
  }

  @Override
  public void actualizarEstado(Long codigo, Boolean estado) {
    Habitacion habitacion = habitacionRepository.findById(codigo)
        .orElseThrow(() -> new RuntimeException("Habitación no encontrada"));
    habitacion.setEstado(estado);
    habitacionRepository.save(habitacion);
  }

  @Override
  public void eliminar(Long codigo) {
    Habitacion habitacion = habitacionRepository.findById(codigo)
        .orElseThrow(() -> new RuntimeException("Habitación no encontrada"));
    habitacion.setEstado(false);
    habitacionRepository.save(habitacion);
  }

  @Override
  public Habitacion buscarPorCodigo(Long codigo) {
    return habitacionRepository.findById(codigo).orElseThrow(() -> new RuntimeException("Habitación no encontrada"));
  }

  @Override
  public Habitacion actualizar(Habitacion habitacion) {
    Habitacion existente = buscarPorCodigo(habitacion.getCodigo());
    existente = habitacion;
    return habitacionRepository.save(existente);
  }

}
