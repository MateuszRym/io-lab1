package library.repository.data;

import library.model.Author;
import org.hibernate.validator.constraints.ParameterScriptAssert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
    @Query("select a from Author a where a.id=:id")
    public Author getAuthorById(@Param("id") int id);
}
