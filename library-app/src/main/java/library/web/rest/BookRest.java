package library.web.rest;

import library.model.Book;
import library.model.Branch;
import library.service.BookService;
import library.service.BranchService;
import library.web.rest.dto.BookDTO;
import library.web.rest.BranchValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/webapi")
public class BookRest {
    private final BranchService branchService;
    private final BookService bookService;
    private final MessageSource messageSource;
    private final LocaleResolver localeResolver;

    @GetMapping("/books")
    List<Book> getBooks() {
        log.info("searching all books");
        List<Book> books = bookService.getAllBooks();
        log.info("Found {} books", books.size());
        return books;
    }

    @GetMapping("/books/{id}")
    ResponseEntity<Book> getBook(@PathVariable("id") int id){
        log.info("searching book by id {}", id);
        Book book = bookService.getBookById(id);
        if(book != null) {
            log.info("Found book {}", book);
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/branches/{branchId}/books")
    ResponseEntity<List<Book>> getBooksByBranch(@PathVariable("branchId") int branchId){
        log.info("searching books in branch {}", branchId);
        Branch branch = branchService.getBranchById(branchId);
        if(branch == null){
            return ResponseEntity.notFound().build();
        } else{
            List<Book> books = branchService.getBooksInBranch(branch);
            log.info("Found {} books in {}", books.size(), branch);
            return ResponseEntity.ok(books);
        }
    }

    @PostMapping("/books")
    ResponseEntity<?> addBook(@RequestBody BookDTO bookDTO){
        log.info("adding book {}", bookDTO);
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setCover(bookDTO.getCover());
        book.setRating(bookDTO.getRating());
        book.setAuthor(bookService.getAuthorById(bookDTO.getAuthorId()));

        book = bookService.addBook(book);
        log.info("added book {}", book);
        return ResponseEntity.created(
                ServletUriComponentsBuilder
                        .fromCurrentRequestUri()
                        .path("/" + book.getId()).build().toUri())
                        .body(book);
    }
}
