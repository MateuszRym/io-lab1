package library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "branch")
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Size(min = 2, max = 20)
    private String name;
    @Column(name = "logo")
    private String logo; //url logo w przypadku UI będzie zaciągany dynamicznie
    @ManyToMany
    @JsonIgnore
    private List<Book> books = new ArrayList<>();//struktura kolekcyjna związaną z granymi filmami, uproszczone
//relacja wiele do wiele

    public Branch(int id, String name, String logo) {//konsturktor
        this.id = id;
        this.name = name;
        this.logo = logo;
    }

    public Branch() {//bezparametrowy
    }
//settery, gettery i to String - później będziemy korzystać z wynalazku Lombok
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
    @JsonIgnore
    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void addBook(Book b) {
        this.books.add(b);
    }

    @Override
    public String toString() {
        return "Branch{" +
                "name='" + name + '\'' +
                ", logo='" + logo + '\'' +
                '}';
    }
}
