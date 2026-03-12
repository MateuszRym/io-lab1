package library.repository.mem;

import library.model.Branch;
import library.model.Author;
import library.model.Book;

import java.util.ArrayList;
import java.util.List;

class SampleData {

    static List<Branch> branches = new ArrayList<>();

    static List<Author> authors = new ArrayList<>();

    static List<Book> books = new ArrayList<>();

    static {

        Author mroz = new Author(1, "Pan", "Mróz");

        Book sample = new Book(1, "sample", "okladka", mroz, (float) 6.9);

        bind(sample, mroz);

        Branch filia1 = new Branch(1, "Filia1", "logo_filia1.png");

        bind(filia1, sample);

        books.add(sample);

        authors.add(mroz);

        branches.add(filia1);

    }

    private static void bind(Branch br, Book b) {
        br.addBook(b);
        b.addBranch(br);
    }

    private static void bind(Book b, Author a) {
        a.addBook(b);
        b.setAuthor(a);
    }

}
