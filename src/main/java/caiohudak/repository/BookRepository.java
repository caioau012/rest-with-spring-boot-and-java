package caiohudak.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import caiohudak.model.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

}
