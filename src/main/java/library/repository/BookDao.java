package library.repository;

import library.model.Branch;
import library.model.Author;
import library.model.Book;

import java.util.List;

public interface BookDao {

    List<Book> findAll();

    Book findById(int id);

    List<Book> findByAuthor(Author a);

    List<Book> findByBranch(Branch b);

    Book add(Book m);

}
