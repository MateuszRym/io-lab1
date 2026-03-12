package library.web.rest;

import library.model.Book;
import library.model.Branch;
import library.service.BookService;
import library.service.BranchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/webapi")
public class BranchRest {
    private final BranchService branchService;
    private final BookService bookService;

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
    ResponseEntity<Branch> addBranch(@RequestBody Branch branch){
        log.info("want to add branch {}", branch);
        //TODO validation
        branch = branchService.addBranch(branch);
        log.info("added branch {}", branch);
        return ResponseEntity.status(HttpStatus.CREATED).body(branch);
    }
}
