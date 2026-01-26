package caiohudak.mapper.custom;

import org.springframework.stereotype.Service;

import caiohudak.data.dto.v1.BookDTO;
import caiohudak.model.Book;

@Service
public class BookMapper {

	public BookDTO convertEntityToDTO(Book book) {
		BookDTO dto = new BookDTO();
		dto.setId(book.getId());
		dto.setAuthor(book.getAuthor());
		dto.setLaunchDate(book.getLaunchDate());
		dto.setPrice(book.getPrice());
		dto.setTitle(book.getTitle());
		return dto;
	}
	
	public Book convertDTOtoEntity(BookDTO book) {
		Book entity = new Book();
		entity.setId(book.getId());
		entity.setAuthor(book.getAuthor());
		entity.setLaunchDate(book.getLaunchDate());
		entity.setPrice(book.getPrice());
		entity.setTitle(book.getTitle());
		return entity;
	}
}
