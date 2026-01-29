package caiohudak.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import caiohudak.data.dto.v1.BookDTO;
import caiohudak.exception.RequiredObjectIsNullException;
import caiohudak.model.Book;
import caiohudak.repository.BookRepository;
import caiohudak.unitetests.mapper.mocks.MockBook;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class BookServicesTest {
	
	MockBook input;
	
	@InjectMocks
	private BookServices service;
	
	@Mock
	BookRepository repository;
	
	@BeforeEach
	void setUp() throws Exception {
		input = new MockBook();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindById() {
		Book book = input.mockEntity(1);
		book.setId(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(book));
		var result = service.findById(1L);
		
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getLinks());
		assertNotNull(result.getLinks().stream().anyMatch(link->link.getRel().value().equals("self")&& link.getHref().endsWith("/book/1")&&link.getType().equals("GET")));
		assertNotNull(result.getLinks().stream().anyMatch(link->link.getRel().value().equals("findAll")&& link.getHref().endsWith("/book")&&link.getType().equals("GET")));
		assertNotNull(result.getLinks().stream().anyMatch(link->link.getRel().value().equals("create")&& link.getHref().endsWith("/book")&&link.getType().equals("POST")));
		assertNotNull(result.getLinks().stream().anyMatch(link->link.getRel().value().equals("update")&& link.getHref().endsWith("/book")&&link.getType().equals("PUT")));
		assertNotNull(result.getLinks().stream().anyMatch(link->link.getRel().value().equals("delete")&& link.getHref().endsWith("/book/1")&&link.getType().equals("DELETE")));
		assertEquals("Author Test1", result.getAuthor());
		Date expectedDate = result.getLaunchDate();
		assertEquals(expectedDate, result.getLaunchDate());
		Double expectedPrice = result.getPrice();
		assertEquals(expectedPrice, result.getPrice());
		assertEquals("Title Test1", result.getTitle());
	}

	@Test
	void testCreate() {
		Book book = input.mockEntity(1);
		Book persisted = book;
		persisted.setId(1L);
		
		BookDTO dto = input.mockDTO(1);
		when(repository.save(book)).thenReturn((persisted));
		
		var result = service.create(dto);
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getLinks());
		assertNotNull(result.getLinks().stream().anyMatch(link->link.getRel().value().equals("self")&& link.getHref().endsWith("/book/1")&&link.getType().equals("GET")));
		assertNotNull(result.getLinks().stream().anyMatch(link->link.getRel().value().equals("findAll")&& link.getHref().endsWith("/book")&&link.getType().equals("GET")));
		assertNotNull(result.getLinks().stream().anyMatch(link->link.getRel().value().equals("create")&& link.getHref().endsWith("/book")&&link.getType().equals("POST")));
		assertNotNull(result.getLinks().stream().anyMatch(link->link.getRel().value().equals("update")&& link.getHref().endsWith("/book")&&link.getType().equals("PUT")));
		assertNotNull(result.getLinks().stream().anyMatch(link->link.getRel().value().equals("delete")&& link.getHref().endsWith("/book/1")&&link.getType().equals("DELETE")));
		assertEquals("Author Test1", result.getAuthor());
		Date expectedDate = result.getLaunchDate();
		assertEquals(expectedDate, result.getLaunchDate());
		Double expectedPrice = result.getPrice();
		assertEquals(expectedPrice, result.getPrice());
		assertEquals("Title Test1", result.getTitle());
	}
	
	@Test
	void testCreateWithNullBook() {
		Exception e = assertThrows(RequiredObjectIsNullException.class, () -> service.create(null));
		
		String expectedMessage = "It is not allowed to persist a null object";
		String actualMessage = e.getMessage();
		
		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void testUpdate() {
		Book book = input.mockEntity(1);
		Book persisted = book;
		persisted.setId(1L);
		
		BookDTO dto = input.mockDTO(1);
		
		when(repository.findById(1L)).thenReturn(Optional.of(book));
		when(repository.save(book)).thenReturn((persisted));
		
		var result = service.update(dto);
		
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getLinks());
		assertNotNull(result.getLinks().stream().anyMatch(link->link.getRel().value().equals("self")&& link.getHref().endsWith("/book/1")&&link.getType().equals("GET")));
		assertNotNull(result.getLinks().stream().anyMatch(link->link.getRel().value().equals("findAll")&& link.getHref().endsWith("/book")&&link.getType().equals("GET")));
		assertNotNull(result.getLinks().stream().anyMatch(link->link.getRel().value().equals("create")&& link.getHref().endsWith("/book")&&link.getType().equals("POST")));
		assertNotNull(result.getLinks().stream().anyMatch(link->link.getRel().value().equals("update")&& link.getHref().endsWith("/book")&&link.getType().equals("PUT")));
		assertNotNull(result.getLinks().stream().anyMatch(link->link.getRel().value().equals("delete")&& link.getHref().endsWith("/book/1")&&link.getType().equals("DELETE")));
		assertEquals("Author Test1", result.getAuthor());
		Date expectedDate = result.getLaunchDate();
		assertEquals(expectedDate, result.getLaunchDate());
		Double expectedPrice = result.getPrice();
		assertEquals(expectedPrice, result.getPrice());
		assertEquals("Title Test1", result.getTitle());
	}
	
	@Test
	void testUpdateWithNullBook() {
		Exception e = assertThrows(RequiredObjectIsNullException.class, () -> service.update(null));
		
		String expectedMessage = "It is not allowed to persist a null object";
		String actualMessage = e.getMessage();
		
		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void testDelete() {
		Book book = input.mockEntity(1);
		book.setId(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(book));
		service.delete(1L);
		verify(repository, times(1)).findById(anyLong());
		verify(repository, times(1)).delete(any(Book.class));
		verifyNoMoreInteractions(repository);
		
	}

	@Test
	@Disabled("REASON: Still Under Development")
	void testFindAll() {
		List<Book> list = input.mockEntityList();
		when(repository.findAll()).thenReturn(list);
		List<BookDTO> books = new ArrayList<>();//service.findAll();
		
		assertNotNull(books);
		assertEquals(14, books.size());
		
		var BookOne = books.get(1);
		
		assertNotNull(BookOne);
		assertNotNull(BookOne.getId());
		assertNotNull(BookOne.getLinks());
		assertNotNull(BookOne.getLinks().stream().anyMatch(link->link.getRel().value().equals("self")&& link.getHref().endsWith("/book/1")&&link.getType().equals("GET")));
		assertNotNull(BookOne.getLinks().stream().anyMatch(link->link.getRel().value().equals("findAll")&& link.getHref().endsWith("/book")&&link.getType().equals("GET")));
		assertNotNull(BookOne.getLinks().stream().anyMatch(link->link.getRel().value().equals("create")&& link.getHref().endsWith("/book")&&link.getType().equals("POST")));
		assertNotNull(BookOne.getLinks().stream().anyMatch(link->link.getRel().value().equals("update")&& link.getHref().endsWith("/book")&&link.getType().equals("PUT")));
		assertNotNull(BookOne.getLinks().stream().anyMatch(link->link.getRel().value().equals("delete")&& link.getHref().endsWith("/book/1")&&link.getType().equals("DELETE")));
		assertEquals("Author Test1", BookOne.getAuthor());
		Date expectedDate = BookOne.getLaunchDate();
		assertEquals(expectedDate, BookOne.getLaunchDate());
		Double expectedPrice = BookOne.getPrice();
		assertEquals(expectedPrice, BookOne.getPrice());
		assertEquals("Title Test1", BookOne.getTitle());
		
		var bookFour = books.get(4);
		
		assertNotNull(bookFour);
		assertNotNull(bookFour.getId());
		assertNotNull(bookFour.getLinks());
		assertNotNull(bookFour.getLinks().stream().anyMatch(link->link.getRel().value().equals("self")&& link.getHref().endsWith("/book/4")&&link.getType().equals("GET")));
		assertNotNull(bookFour.getLinks().stream().anyMatch(link->link.getRel().value().equals("findAll")&& link.getHref().endsWith("/book")&&link.getType().equals("GET")));
		assertNotNull(bookFour.getLinks().stream().anyMatch(link->link.getRel().value().equals("create")&& link.getHref().endsWith("/book")&&link.getType().equals("POST")));
		assertNotNull(bookFour.getLinks().stream().anyMatch(link->link.getRel().value().equals("update")&& link.getHref().endsWith("/book")&&link.getType().equals("PUT")));
		assertNotNull(bookFour.getLinks().stream().anyMatch(link->link.getRel().value().equals("delete")&& link.getHref().endsWith("/book/4")&&link.getType().equals("DELETE")));
		assertEquals("Author Test1", bookFour.getAuthor());
		Date expectedDate2 = bookFour.getLaunchDate();
		assertEquals(expectedDate2, bookFour.getLaunchDate());
		Double expectedPrice2 = bookFour.getPrice();
		assertEquals(expectedPrice2, bookFour.getPrice());
		assertEquals("Title Test1", bookFour.getTitle());
		
	}

}
