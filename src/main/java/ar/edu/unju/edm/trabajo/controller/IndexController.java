package ar.edu.unju.edm.trabajo.controller;

import org.springframework.web.bind.annotation.GetMapping;
// GetMApping pedir informacion
//Postmapping enviar informacion
public class IndexController {
  @GetMapping("/")
  public String index() {
    return "index";
  }
}
