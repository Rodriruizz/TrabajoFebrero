package ar.edu.unju.edm.trabajo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.edm.trabajo.model.Reserva;
import ar.edu.unju.edm.trabajo.service.IHabitacionService;
import ar.edu.unju.edm.trabajo.service.IHuespedService;
import ar.edu.unju.edm.trabajo.service.IReservaService;

@Controller
public class ReservaController {
  @Autowired //llama a los metodos de la clase o interfaz
  IHuespedService huespedService;

  @Autowired
  IHabitacionService habitacionService;

  @Autowired
  IReservaService reservaService;

  @GetMapping("/reservas/nueva")
  public ModelAndView formularioNuevaReserva() {
    ModelAndView mav = new ModelAndView("formularioReserva");
    mav.addObject("reserva", new Reserva());
    mav.addObject("huespedes", huespedService.listar());
    mav.addObject("habitaciones", habitacionService.listar());
    return mav;
  }

  // Guardar una nueva reserva
  @PostMapping("/reservas/guardar")//mandar informacion
  public String guardarReserva(@ModelAttribute Reserva reserva) {
    reservaService.guardarReserva(reserva.getHuesped().getDni(), reserva.getHabitacion().getCodigo());
    return "redirect:/reservas";
  }

  // Mostrar todas las reservas
  @GetMapping("/reservas")
  public ModelAndView listarReservas() {
    ModelAndView mav = new ModelAndView("listaReservas");
    mav.addObject("reservas", reservaService.listarReservas());
    return mav;
  }

  @PostMapping("/reservas/eliminar/{codigo}")
  public String eliminarReserva(@PathVariable Long codigo) {
    reservaService.eliminarReserva(codigo);
    return "redirect:/reservas";
  }
}
