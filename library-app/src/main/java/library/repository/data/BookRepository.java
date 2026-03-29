package library.repository.data;

import library.model.Author;
import library.model.Book;
import library.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findAllByAuthor(Author a);

    List<Book> findAllByBranchesContaining(Branch b);


}
