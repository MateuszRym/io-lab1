package library.repository;

import library.model.Branch;
import library.model.Book;

import java.util.List;

public interface BranchDao {

    List<Branch> findAll();

    Branch findById(int id);

    List<Branch> findByBook(Book b);

    Branch save(Branch b);

}
