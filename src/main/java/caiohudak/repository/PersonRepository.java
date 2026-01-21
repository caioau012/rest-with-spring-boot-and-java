package caiohudak.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import caiohudak.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{

}
