package library.web.ui;

import library.model.Book;
import library.model.Branch;
import library.service.BookService;
import library.service.BranchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BranchController {
    private final BranchService branchService;
    private final BookService bookService;

    @GetMapping("/branches")
    String getBranches(Model model, @RequestParam(value = "bookId", required = false) Integer bookId) {
        log.info("getting libraries");
        if(bookId != null) {
            Book book = bookService.getBookById(bookId);
            List<Branch> branches = branchService.getBranchesByBook(book);
            model.addAttribute("branches", branches);
            model.addAttribute("title", "Branches for book '" + book.getTitle() + "'");
        }else{
            List<Branch> branches = branchService.getAllBranches();
            model.addAttribute("branches", branches);
            model.addAttribute("title", "Branches");
        }
        return "branchesView";
    }
}
