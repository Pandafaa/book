package telran.ashkelon2020.book.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import telran.ashkelon2020.book.model.Author;
import telran.ashkelon2020.book.model.Publisher;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookDto {

	String isbn;
	String title;
	@Singular
	Set<AuthorDto> authors;
	String publisher;
}
