package library.repository.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import library.model.Book;
import library.model.Branch;
import library.repository.BranchDao;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaBranchDao implements BranchDao {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Branch> findAll() {
        return entityManager.createQuery("select b from Branch b").getResultList();
    }

    @Override
    public Branch findById(int id) {
        return entityManager.find(Branch.class, id);
    }

    @Override
    public List<Branch> findByBook(Book boo) {
        return entityManager.createQuery("select b from Branch b inner join b.books book where book=:book")
                .setParameter("book", boo)
                .getResultList();
    }
    @Override
    public Branch save(Branch b) {
        entityManager.persist(b);
        return b;
    }
}
