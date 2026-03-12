package library.service;

import library.model.Branch;
import library.model.Book;

import java.util.List;

public interface BranchService {
//api zwraca nam wszystkie kina
    Branch getBranchById(int id);

    List<Branch> getAllBranches();

    List<Branch> getBranchesByBook(Book b);

    List<Book> getBooksInBranch(Branch b);

    Branch addBranch(Branch b);
}
