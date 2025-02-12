package ar.edu.unju.edm.trabajo.service.Imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.trabajo.model.Huesped;
import ar.edu.unju.edm.trabajo.repository.HuespedRepository;
import ar.edu.unju.edm.trabajo.service.IHuespedService;

@Service
public class IHuespedServiceImp implements IHuespedService {
  @Autowired
  HuespedRepository pasajeroRepository;

  @Override
  public Huesped crear(Huesped huesped) {
    return pasajeroRepository.save(huesped);
  }

  @Override
  public List<Huesped> listar() {
    List<Huesped> huespedes = (List<Huesped>) pasajeroRepository.findAll();
    List<Huesped> huespedesActivos = new ArrayList<>();
    for (Huesped huesped : huespedes) {
      if (huesped.getEstado().equals(true)) {
        huespedesActivos.add(huesped);
      }
    }
    return huespedesActivos;
  }

  @Override
  public void eliminar(String dni) {
    Huesped huesped = pasajeroRepository.findById(dni)
        .orElseThrow(() -> new RuntimeException("Huesped no encontrado"));
    huesped.setEstado(false);
    pasajeroRepository.save(huesped);
  }

  @Override
  public Huesped actualizar(Huesped huesped) {
    Huesped huesped2 = pasajeroRepository.findById(huesped.getDni())
        .orElseThrow(() -> new RuntimeException("Huesped no encontrado"));
    huesped2 = huesped;
    return pasajeroRepository.save(huesped2);
  }

  @Override
  public Huesped buscarPorDni(String dni) {
    Huesped huesped = pasajeroRepository.findById(dni)
        .orElseThrow(() -> new RuntimeException("Huesped no encontrado"));
    return huesped;
  }

}
