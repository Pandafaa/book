package telran.ashkelon2020.book.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthorDto {

	String name;
	@JsonFormat(pattern="yyyy-MM-dd")
	LocalDate birthDate;
}
