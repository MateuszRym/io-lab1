package library.repository.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import library.model.Author;
import library.model.Book;
import library.model.Branch;
import library.repository.BookDao;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Primary
public class JpaBookDao implements BookDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Book> findAll() {
        return entityManager.createQuery(
                "select b from Book b", Book.class).getResultList();
    }

    @Override
    public Book findById(int id) {
        return entityManager.find(Book.class, id);
    }

    @Override
    public List<Book> findByAuthor(Author a){
        return entityManager.createQuery(
                "select b from Book b where author=:author")
                .setParameter("author", a)
                .getResultList();
    }

    @Override
    public List<Book> findByBranch(Branch b) {
        return entityManager.createQuery(
                "select boo from Book boo inner join boo.branches branch where branch=:branch")
                .setParameter("branch", b)
                .getResultList();
    }

    @Override
    public Book add(Book b) {
        entityManager.persist(b);
        return b;
    }
}
