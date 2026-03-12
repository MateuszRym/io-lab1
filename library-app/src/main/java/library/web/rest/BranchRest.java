package library.web.rest;

import jakarta.servlet.http.HttpServletRequest;
import library.model.Book;
import library.model.Branch;
import library.service.BookService;
import library.service.BranchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;

import java.util.List;
import java.util.Locale;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/webapi")
public class BranchRest {
    private final BranchService branchService;
    private final BookService bookService;
    private final MessageSource messageSource;
    private final LocaleResolver localeResolver;

    @GetMapping("/branches")
    List<Branch> getBranches(
            @RequestParam(value = "phrase", required = false) String phrase,
            @RequestHeader(value = "custom-header", required = false) String customHeader,
            @CookieValue(value = "cookie-name", required = false) String cookieValue
    ) {
        log.info("searching all cinemas");
        log.info("phrase: {}", phrase);
        log.info("custom-header: {}", customHeader);
        log.info("cookie-name: {}", cookieValue);
        List<Branch> branches = branchService.getAllBranches();
        log.info("Found {} branches", branches.size());
        return branches;
    }

    @GetMapping("/branches/{id}")
    ResponseEntity<Branch> getBranch(@PathVariable("id") int id){
        log.info("searching cinema {}", id);
        Branch branch = branchService.getBranchById(id);
        log.info("Found branch {}", branch);
        if(branch != null) {
            return ResponseEntity.status(200).body(branch);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/books/{bookId}/branches")
    ResponseEntity<List<Branch>> getBranchesByBook(@PathVariable("bookId") int bookId){
        log.info("searching cinemas for book {}", bookId);
        Book book = bookService.getBookById(bookId);
        if(book != null) {
            List<Branch> branches = branchService.getBranchesByBook(book);
            log.info("Found {} branches", branches.size());
            return ResponseEntity.status(200).body(branches);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/branches")
    ResponseEntity<?> addBranch(@Validated @RequestBody Branch branch, Errors errors, HttpServletRequest request){
        log.info("want to add branch {}", branch);

        if(errors.hasErrors()) {
            Locale locale = localeResolver.resolveLocale(request);
            String errorMsg = errors.getAllErrors().stream()
                    .map(e -> messageSource.getMessage(e.getCode(), new Object[0], locale))
                    .reduce("errors:\n", (accu, e) -> accu + e + "\n");
            return ResponseEntity.badRequest().body(errorMsg);
        }

        branch = branchService.addBranch(branch);
        log.info("added branch {}", branch);
        return ResponseEntity.status(HttpStatus.CREATED).body(branch);
    }
}
