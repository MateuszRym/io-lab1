package library.repository.data;

import library.model.Book;
import library.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BranchRepository extends JpaRepository<Branch, Integer> {
    List<Branch> findAllByNameContaining(String name);

    @Query("select b from Branch b inner join b.books book where book=:book")
    List<Branch> findAllByBook(@Param("book") Book book);
}
