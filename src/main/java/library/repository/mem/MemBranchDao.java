package library.repository.mem;

import library.model.Branch;
import library.model.Book;
import library.repository.BranchDao;

import java.util.List;
import java.util.stream.Collectors;


public class MemBranchDao implements BranchDao {

    @Override
    public List<Branch> findAll() {
        return SampleData.branches;
    }

    @Override
    public Branch findById(int id) {
        return SampleData.branches.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Branch> findByBook(Book b) {
        return SampleData.branches.stream().filter(c -> c.getBooks().contains(b)).collect(Collectors.toList());
    }
}
