package library.web.rest;

import library.model.Author;
import library.model.Book;
import library.model.Branch;
import library.service.BookService;
import library.web.rest.dto.BookDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class BookValidator implements Validator {
    private final BookService bookService;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(Book.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        BookDTO book = (BookDTO) target;
        Author author = bookService.getAuthorById(book.getAuthorId());
        if (author == null) {
            errors.rejectValue("authorId", "book.author.missing");
        }
    }
}
