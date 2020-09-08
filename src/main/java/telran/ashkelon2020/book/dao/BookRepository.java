package telran.ashkelon2020.book.dao;


import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import telran.ashkelon2020.book.model.Author;
import telran.ashkelon2020.book.model.Book;
import telran.ashkelon2020.book.model.Publisher;



public interface BookRepository extends JpaRepository<Book,String> {
	
	

	@Query("select b from Book b where b.publisher.publisherName=:publisherName")
Stream<Book>  findByPublisher(@Param("publisherName") String publisherName);

//	Stream<Book>   findByPublisherName(String publisherName);
	
	
	@Query("select b.authors from Book b where b.isbn = :isbn")
	Stream<Author>findAuhtorsByIsbn(@Param("isbn")String isbn);
	


//@Query(value="select *  from book_authors ab  join book b Where ab.book_isbn =b.isbn and ab.authors_name =?1", nativeQuery = true)
	Stream<Book>  findByAuthorsName(String authorName);  
	
	@Query("select b from Book b join b.authors a  where  a.name=:authorName")
	Stream<Book> findBooksByAuthor(@Param("authorName")String authorName) ;

@Query("select distinct b.publisher from Book b  join b.authors a where a.name=:authorName ")
Stream<Publisher> findPublishersByAuthor(@Param("authorName")String authorName);




}
