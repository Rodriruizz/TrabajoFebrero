package ar.edu.unju.edm.trabajo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.edm.trabajo.model.Reserva;

@Repository
public interface ReservaRepository extends CrudRepository<Reserva, Long> {

}
