package ar.edu.unju.edm.trabajo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.edu.unju.edm.trabajo.model.Reserva;
import ar.edu.unju.edm.trabajo.service.IHabitacionService;
import ar.edu.unju.edm.trabajo.service.IHuespedService;
import ar.edu.unju.edm.trabajo.service.IReservaService;

@Controller
public class ReservaController {
  @Autowired
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
  @PostMapping("/reservas/guardar")
  public String guardarReserva(@ModelAttribute Reserva reserva, RedirectAttributes redirectAttributes) {
    try {//intenta realizar acciones
      if (reserva.getFechaReserva().isBefore(LocalDate.now())) {
        throw new RuntimeException("La fecha de reserva no puede ser en el pasado.");
      }
      List<Reserva> reservas = reservaService.listarReservas();
      for (Reserva reserva1 : reservas) {

        if (reserva1.getFechaReserva().equals(reserva.getFechaReserva())
            && reserva1.getHabitacion().getCodigo().equals(reserva.getHabitacion().getCodigo())) {
          throw new RuntimeException("La fecha de reserva ya existe o la habitacion esta reservada.");
        }
      }
      reservaService.guardarReserva(reserva);
      return "redirect:/reservas";

    } catch  (RuntimeException e) {//si el try falla se detecta el error y se muestra el mensaje de error
      // Mensaje de error en caso de excepción
      redirectAttributes.addFlashAttribute("mensajeError", e.getMessage());

      return "redirect:/reservas/nueva";
    }
  }

  // Mostrar todas las reservas
  @GetMapping("/reservas")
  public ModelAndView listarReservas() {
    ModelAndView mav = new ModelAndView("listaReservas");
    mav.addObject("reservas", reservaService.listarReservas());
    return mav;
  }

  @GetMapping("/reservas/eliminar/{codigo}")
  public String eliminarReserva(@PathVariable Long codigo) {
    reservaService.eliminarReserva(codigo);
    ;
    return "redirect:/reservas";
  }
}
