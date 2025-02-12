package ar.edu.unju.edm.trabajo.service;

import java.util.List;

import ar.edu.unju.edm.trabajo.model.Habitacion;

public interface IHabitacionService {

  public Habitacion crear(Habitacion habitacion);

  public List<Habitacion> listar();

  public void eliminar(Long codigo);

  public void actualizarEstado(Long codigo, Boolean estado);//es para cambiar entre disponible y ocupado

  public Habitacion buscarPorCodigo(Long codigo);

  public Habitacion actualizar(Habitacion habitacion);//actualizar el objeto Habitacion
}
