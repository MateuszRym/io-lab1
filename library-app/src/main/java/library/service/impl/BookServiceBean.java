package library.service.impl;

import library.model.Branch;
import library.model.Author;
import library.model.Book;
import library.repository.BranchDao;
import library.repository.AuthorDao;
import library.repository.BookDao;
import library.service.BookService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;
@Component
public class BookServiceBean implements BookService {

    private static final Logger log = Logger.getLogger(BookService.class.getName());

    private AuthorDao authorDao;
    private BranchDao branchDao;
    private BookDao bookDao;

    public BookServiceBean(AuthorDao authorDao, BranchDao branchDao, BookDao bookDao) {
        this.authorDao = authorDao;
        this.branchDao = branchDao;
        this.bookDao = bookDao;
    }

    public List<Book> getAllBooks() {
        log.info("searching all books...");
        return bookDao.findAll();
    }

    public List<Book> getBooksByAuthor(Author a) {
        log.info("searching books by author " + a.getId());
        return bookDao.findByAuthor(a);
    }

    public List<Book> getBookInBranch(Branch b) {
        log.info("searching movies played in cinema " + b.getId());
        return bookDao.findByBranch(b);
    }

    public Book getBookById(int id) {
        log.info("searching movie by id " + id);
        return bookDao.findById(id);
    }

    public List<Branch> getAllBranches() {
        log.info("searching all branches");
        return branchDao.findAll();
    }

    public List<Branch> getBranchesByBook(Book b) {
        log.info("searching branches by book " + b.getId());
        return branchDao.findByBook(b);
    }

    public Branch getBranchById(int id) {
        log.info("searching cinema by id " + id);
        return branchDao.findById(id);
    }

    public List<Author> getAllAuthors() {
        log.info("searching all authors");
        return authorDao.findAll();
    }

    public Author getAuthorById(int id) {
        log.info("searching author by id " + id);
        return authorDao.findById(id);
    }

    @Override
    public Book addBook(Book b) {
        log.info("about to add book " + b);
        return bookDao.add(b);
    }

    @Override
    public Author addAuthor(Author a) {
        log.info("about to add author " + a);
        return authorDao.add(a);
    }

}
