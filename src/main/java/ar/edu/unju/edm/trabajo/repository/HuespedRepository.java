package ar.edu.unju.edm.trabajo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.edm.trabajo.model.Huesped;

@Repository
public interface HuespedRepository extends CrudRepository<Huesped, String> {

}
