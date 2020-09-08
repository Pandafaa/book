package telran.ashkelon2020.book.service;

import telran.ashkelon2020.book.dto.AuthorDto;
import telran.ashkelon2020.book.dto.BookDto;

public interface BookService {
	
	boolean addBook(BookDto boookdto);
	
	BookDto findByIsbn(String isbn);
	
	BookDto removeBook(String isbn);
	
	BookDto updateBook(String isbn, String title);
	
	Iterable<BookDto> findBooksByAuthor(String authorName);
	
	Iterable<BookDto> findBooksByPublisher(String publisherName);
	
	Iterable<AuthorDto> findBookAuthors(String isbn);
	
	Iterable<String> findPublishersbyAuthor(String authorName);
	
	AuthorDto removeAuthor(String authorname);

}
