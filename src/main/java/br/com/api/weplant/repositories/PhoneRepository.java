package br.com.api.weplant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.weplant.entities.Phone;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {

}
