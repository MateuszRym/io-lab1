package library.repository.data;

import library.model.Author;
import library.model.Book;
import library.model.Branch;
import library.repository.BookDao;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Primary
public class DataBookDao implements BookDao {
    private final BookRepository bookRepository;

    @Override
    public List<Book> findAll() {return bookRepository.findAll();}

    @Override
    public Book findById(int id) {return bookRepository.findById(id).orElse(null);}

    @Override
    public List<Book> findByAuthor(Author a) {return bookRepository.findAllByAuthor(a);}

    @Override
    public List<Book> findByBranch(Branch b) {return bookRepository.findAllByBranchesContaining(b);}
    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public Book add(Book m) {return bookRepository.save(m);}
}
