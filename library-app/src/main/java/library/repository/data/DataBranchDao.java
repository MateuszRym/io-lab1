package library.repository.data;

import library.model.Book;
import library.model.Branch;
import library.repository.BranchDao;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Primary
@RequiredArgsConstructor
public class DataBranchDao implements BranchDao {
    private final BranchRepository branchRepository;

    @Override
    public List<Branch> findAll(){return branchRepository.findAll();}

    @Override
    public Branch findById(int id){return branchRepository.findById(id).orElse(null);}

    @Override
    public List<Branch> findByBook(Book b) {return branchRepository.findAllByBook(b);}

    @Override
    public Branch save(Branch b){return branchRepository.save(b);}
}
