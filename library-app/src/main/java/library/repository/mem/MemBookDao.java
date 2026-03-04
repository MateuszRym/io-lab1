package library.repository.mem;

import library.model.Branch;
import library.model.Author;
import library.model.Book;
import library.repository.BookDao;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class MemBookDao implements BookDao {
    @Override
    public List<Book> findAll() {
        return SampleData.books;
    }

    @Override
    public Book findById(int id) {
        return SampleData.books.stream().filter(m -> m.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Book> findByAuthor(Author a) {
       return SampleData.books.stream().filter(m -> m.getAuthor() == a).collect(Collectors.toList());
    }

    @Override
    public List<Book> findByBranch(Branch br) {
        return SampleData.books.stream().filter(b -> b.getBranches().contains(br)).collect(Collectors.toList());
    }

    @Override
    public Book add(Book b) {
        int max = SampleData.books.stream().max((b1, b2) -> b1.getId() - b2.getId()).get().getId();
        b.setId(++max);
        SampleData.books.add(b);
        return b;
    }
}
