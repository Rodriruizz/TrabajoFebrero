package ar.edu.unju.edm.trabajo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.edm.trabajo.model.Huesped;

@Service
public interface IHuespedService {

  public Huesped crear(Huesped huesped);

  public Huesped actualizar(Huesped huesped);

  public List<Huesped> listar();

  public void eliminar(String dni);

  public Huesped buscarPorDni(String dni);
}
