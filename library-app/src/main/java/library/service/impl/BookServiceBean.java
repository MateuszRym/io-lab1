package library.service.impl;

import library.model.Branch;
import library.model.Author;
import library.model.Book;
import library.repository.BranchDao;
import library.repository.AuthorDao;
import library.repository.BookDao;
import library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.List;
import java.util.logging.Logger;
@Service
//@RequiredArgsConstructor
public class BookServiceBean implements BookService {

    private static final Logger log = Logger.getLogger(BookService.class.getName());
    private final PlatformTransactionManager transactionManager;

    private AuthorDao authorDao;
    private BranchDao branchDao;
    private BookDao bookDao;

    public BookServiceBean(AuthorDao authorDao, BranchDao branchDao, BookDao bookDao, PlatformTransactionManager transactionManager) {
        this.authorDao = authorDao;
        this.branchDao = branchDao;
        this.bookDao = bookDao;
        this.transactionManager = transactionManager;
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
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Book addBook(Book b) {
        log.info("about to add book " + b);
        TransactionStatus ts = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try{
            b = bookDao.add(b);
            if(b.getTitle().equals("test")){
                throw new RuntimeException("test exception");
            }
            transactionManager.commit(ts);
        }catch (RuntimeException e){
            transactionManager.rollback(ts);
            throw e;
        }
        return b;
    }

    @Override
    public Author addAuthor(Author a) {
        log.info("about to add author " + a);
        return authorDao.add(a);
    }

}
