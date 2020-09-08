package telran.ashkelon2020.book.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import telran.ashkelon2020.book.dto.AuthorDto;
import telran.ashkelon2020.book.dto.BookDto;
import telran.ashkelon2020.book.service.BookService;

@RestController
public class BookController {

	@Autowired
	BookService bookService;

	@PostMapping("/book")
	public boolean addBook(@RequestBody BookDto bookDto) {
		return bookService.addBook(bookDto);
	}

@GetMapping("book/{isbn}")
public BookDto findBook(@PathVariable String isbn) {
	return bookService.findByIsbn(isbn);
}

@DeleteMapping("book/{isbn}")
public BookDto deleteBook(@PathVariable String isbn) {
	return bookService.removeBook(isbn);
}

@PutMapping("book/{isbn}/title/{title}")
public BookDto updateBook(@PathVariable String isbn,@PathVariable String title) {
	return bookService.updateBook(isbn, title);
}


@GetMapping("books/author/{authorName}")
public Iterable<BookDto> findBooksbyAuthor(@PathVariable String authorName){
	return bookService.findBooksByAuthor(authorName);
}

@GetMapping("authors/{isbn}")
public Iterable<AuthorDto> findBookAuthors(@PathVariable String isbn){
	return bookService.findBookAuthors(isbn);
	
}
@GetMapping("books/publisher/{publisherName}")
public Iterable<BookDto> findBooksbyPublisherName(@PathVariable String publisherName){
	return bookService.findBooksByPublisher(publisherName);
}

@GetMapping("publishers/author/{authorName}")
public Iterable<String> findPublishersByAuthor(@PathVariable String authorName){
	return bookService.findPublishersbyAuthor(authorName);
}

@DeleteMapping("author/{authorName}")
public AuthorDto deleteAuthor(@PathVariable String authorName) {
	return bookService.removeAuthor(authorName);
}

}
