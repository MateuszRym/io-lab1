package library.repository;

import library.model.Author;

import java.util.List;

public interface AuthorDao {

    List<Author> findAll();

    Author findById(int id);

    Author add(Author d);


}
