package library.service.impl;

import library.model.Branch;
import library.model.Book;
import library.repository.AuthorDao;
import library.repository.BranchDao;
import library.repository.BookDao;
import library.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;
@Component
@Scope("prototype")
public class BranchServiceBean implements BranchService {

    private static final Logger log = Logger.getLogger(BranchService.class.getName());

    private BranchDao branchDao;
    private BookDao bookDao;

    public BranchServiceBean(BranchDao branchDao, BookDao bookDao) {
        log.info("creating cinema service bean");
        this.branchDao = branchDao;
        this.bookDao = bookDao;
    }
    @Autowired
    public void setBranchDao(BranchDao branchDao) {
        this.branchDao = branchDao;
    }
    @Autowired
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }
    @Override
    public Branch getBranchById(int id) {
        log.info("searching cinema by id " + id);
        return branchDao.findById(id);
    }

    @Override
    public List<Book> getBooksInBranch(Branch b) {
        log.info("searching movies played in cinema " + b.getId());
        return bookDao.findByBranch(b);
    }

    @Override
    public List<Branch> getAllBranches() {
        log.info("searching all cinemas");
        return branchDao.findAll();
    }

    @Override
    public List<Branch> getBranchesByBook(Book b) {
        log.info("searching cinemas by movie " + b.getId());
        return branchDao.findByBook(b);
    }

}
