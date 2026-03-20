package library.web.ui;

import library.model.Author;
import library.model.Book;
import library.model.Branch;
import library.service.BookService;
import library.service.BranchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BookController {
    private final BookService bookService;
    private final BranchService branchService;

    @GetMapping("/books")
    String getBooks(Model model,
                    @RequestParam(value = "cinemaId", required = false) Integer cinemaId,
                    @RequestParam(value = "authorId", required = false) Integer authorId) {
        log.info("getting books");
        if(cinemaId!=null){
            Branch branch = branchService.getBranchById(cinemaId);
            List<Book> books = branchService.getBooksInBranch(branch);
            model.addAttribute("books", books);
            model.addAttribute("title", "Books in cinema '" + branch.getName() + "'");
            return "booksView";
        }else if(authorId!=null) {
            Author author = bookService.getAuthorById(authorId);
            List<Book> books = bookService.getBooksByAuthor(author);
            model.addAttribute("books", books);
            model.addAttribute("title", "Books written by '" + author.getLastName() + " " + author.getFirstName() + "'");
        }else {
            List<Book> books = bookService.getAllBooks();
            model.addAttribute("books", books);
            model.addAttribute("title", "Books");
        }
        return "booksView";
    }
}
