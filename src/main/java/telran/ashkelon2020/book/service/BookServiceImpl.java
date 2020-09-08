package telran.ashkelon2020.book.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import telran.ashkelon2020.book.dao.AuthorRepository;
import telran.ashkelon2020.book.dao.BookRepository;
import telran.ashkelon2020.book.dao.PublisherRepository;
import telran.ashkelon2020.book.dto.AuthorDto;
import telran.ashkelon2020.book.dto.BookDto;
import telran.ashkelon2020.book.model.Author;
import telran.ashkelon2020.book.model.Book;
import telran.ashkelon2020.book.model.Publisher;


@Service
public class BookServiceImpl implements BookService {

	@Autowired
	AuthorRepository authorRepository;

	@Autowired
	PublisherRepository publisherRepository;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	BookRepository bookRepository;

	@Override
	@Transactional
	public boolean addBook(BookDto bookDto) {
		if (bookRepository.existsById(bookDto.getIsbn())) {
			return false;
		}
		//Publisher
		String publisherName = bookDto.getPublisher();
		Publisher publisher = publisherRepository.findById(publisherName)
.orElse(publisherRepository.save(new Publisher(publisherName)));
				//Author
		
		Set<Author> authors= bookDto.getAuthors()
				.stream()
				.map(a->authorRepository.findById(a.getName())
				.orElse(authorRepository.save(new Author(a.getName(), a.getBirthDate())))).collect(Collectors.toSet());
		Book book = new Book(bookDto.getIsbn(),bookDto.getTitle(),authors,publisher);
		bookRepository.save(book);
		return true;
	}

	@Override
	public BookDto findByIsbn(String isbn) {
Book book = bookRepository.findById(isbn).orElseThrow(()->new EntityNotFoundException());
	return modelMapper.map(book, BookDto.class);
	}
	
	@Transactional
	@Override
	public BookDto removeBook(String isbn) {
		Book book = bookRepository.findById(isbn).orElseThrow(()->new EntityNotFoundException());
		bookRepository.delete(book);
		return modelMapper.map(book, BookDto.class);
	}

	@Transactional
	@Override
	public BookDto updateBook(String isbn, String title) {
		Book book = bookRepository.findById(isbn).orElseThrow(()->new EntityNotFoundException());
		book.setTitle(title);
	//	bookRepository.save(book);
		return modelMapper.map(book, BookDto.class);
	}

	@Override
	@Transactional
	public Iterable<BookDto> findBooksByAuthor(String authorName) {
		return  	bookRepository.findByAuthorsName(authorName).map(b->modelMapper.map(b,BookDto.class)).collect(Collectors.toList());
				//bookRepository.findBooksByAuthor(authorName).map(b->modelMapper.map(b,BookDto.class)).collect(Collectors.toList());
	
	}

	@Override
	@Transactional
	public Iterable<BookDto> findBooksByPublisher(String publisherName) {
		

		return bookRepository.findByPublisher(publisherName).map(b->modelMapper.map(b,BookDto.class)).collect(Collectors.toList());
		
	}

	@Override
	@Transactional
	public Iterable<AuthorDto> findBookAuthors(String isbn) {
	
		return bookRepository.findAuhtorsByIsbn(isbn).map(b->modelMapper.map(b,AuthorDto.class)).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public Iterable<String> findPublishersbyAuthor(String authorName) {
		
		return bookRepository.findPublishersByAuthor(authorName).map(p->p.getPublisherName()).collect(Collectors.toList());
	}

	@Override
	@Transactional
	
	public AuthorDto removeAuthor(String authorname) {
		Author author =authorRepository.findById(authorname).orElseThrow(()->new EntityNotFoundException());
	bookRepository.findByAuthorsName(authorname).forEach(b->bookRepository.delete(b));
	authorRepository.delete(author);

		return modelMapper.map(author, AuthorDto.class);
	}

}
