package ar.edu.unju.edm.trabajo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.edu.unju.edm.trabajo.model.Huesped;
import ar.edu.unju.edm.trabajo.service.IHuespedService;

@Controller
public class HuespedController {
  @Autowired
  IHuespedService huespedService;

  @GetMapping("/huespedes")
  public ModelAndView listarHuespedes() {
    ModelAndView vista = new ModelAndView("listaHuespedes");
    vista.addObject("huespedes", huespedService.listar());
    return vista;
  }

  @GetMapping("/huespedes/nuevo")
  public ModelAndView formularioNuevoHuesped() {
    ModelAndView mav = new ModelAndView("formularioHuesped");
    mav.addObject("nacionalidades", List.of("ARGENTINA", "BRASILERA", "CHILENA", "PARAGUAYA", "URUGUAYA"));
    mav.addObject("huesped", new Huesped());
    return mav;
  }

  @PostMapping("/huespedes/guardar")
  public String guardarhuesped(Huesped huesped,RedirectAttributes redirectAttributes) {
    try{
      if(LocalDate.now().getYear()-huesped.getFechaNacimiento().getYear()>=18){
        huesped.setEstado(true);
        huespedService.crear(huesped);
        return "redirect:/huespedes";
      }else{
        throw new RuntimeException("El huesped es menor de edad");
      }
    }catch(RuntimeException e){
      redirectAttributes.addFlashAttribute("mensajeError", e.getMessage());
      return "redirect:/huespedes/nuevo";
    }
    
  }

  @GetMapping("/huespedes/eliminar/{dni}")
  public String eliminarPasajero(@PathVariable String dni) {
    huespedService.eliminar(dni);
    return "redirect:/huespedes";
  }

  @GetMapping("/huespedes/editar/{dni}")
  public ModelAndView formularioEditarHuesped(@PathVariable String dni) {
    ModelAndView mav = new ModelAndView("formularioHuesped");
    mav.addObject("nacionalidades", List.of("ARGENTINA", "BRASILERA", "CHILENA", "PARAGUAYA", "URUGUAYA"));
    mav.addObject("huesped", huespedService.buscarPorDni(dni));
    return mav;
  }

  @PostMapping("/huespedes/actualizar")
  public String actualizarHuesped(Huesped huesped) {
    huespedService.actualizar(huesped);
    return "redirect:/huespedes";
  }
}
