package ar.edu.unju.edm.trabajo.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.edm.trabajo.model.Habitacion;
import ar.edu.unju.edm.trabajo.service.IHabitacionService;

@Controller
public class HabitacionController {
  @Autowired
  IHabitacionService habitacionService;

  @GetMapping("/habitaciones")
  public ModelAndView listarHabitaciones() {
    ModelAndView mav = new ModelAndView("listaHabitaciones");
    mav.addObject("habitaciones", habitacionService.listar());
    return mav;
  }

  @GetMapping("/habitaciones/nueva")
  public ModelAndView formularioNuevaHabitacion() {
    ModelAndView mav = new ModelAndView("formularioHabitacion");
    List<String> tipos = Arrays.asList("SIMPLE", "DOBLE", "SUITE");
    mav.addObject("tipos", tipos);
    mav.addObject("habitacion", new Habitacion());
    return mav;
  }

  @PostMapping("/habitaciones/guardar")
  public String guardarHabitacion(Habitacion habitacion) {
    habitacionService.crear(habitacion);
    return "redirect:/habitaciones";
  }

  @GetMapping("/habitaciones/estado/{codigo}")
  public String actualizarEstadoHabitacion(@PathVariable Long codigo, @RequestParam Boolean estado) {
    habitacionService.actualizarEstado(codigo, estado);
    return "redirect:/habitaciones";
  }

  @GetMapping("/habitaciones/editar/{codigo}")
  public ModelAndView formularioEditarHabitacion(@PathVariable Long codigo) {
    ModelAndView mav = new ModelAndView("formularioHabitacion");
    List<String> tipos = Arrays.asList("SIMPLE", "DOBLE", "SUITE");
    mav.addObject("tipos", tipos);
    mav.addObject("habitacion", habitacionService.buscarPorCodigo(codigo));
    return mav;
  }

  // Guardar cambios en una habitación
  @PostMapping("/habitaciones/actualizar")
  public String actualizarHabitacion(Habitacion habitacion) {
    habitacionService.actualizar(habitacion);
    return "redirect:/habitaciones";
  }
}
