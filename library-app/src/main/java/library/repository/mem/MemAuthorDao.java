package library.repository.mem;

import library.model.Author;
import library.repository.AuthorDao;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("authorDao")
public class MemAuthorDao implements AuthorDao {
    @Override
    public List<Author> findAll() {
        return SampleData.authors;
    }

    @Override
    public Author findById(int id) {
        return SampleData.authors.stream().filter(a -> a.getId() == id).findFirst().orElse(null);
    }

    @Override
    public Author add(Author a) {
        int max = SampleData.authors.stream().max((a1, a2) -> a1.getId() - a2.getId()).get().getId();
        a.setId(++max);
        SampleData.authors.add(a);
        return a;
    }
}
