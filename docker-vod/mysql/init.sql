CREATE TABLE author
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    firstname VARCHAR(255),
    lastname VARCHAR(255)
);

CREATE TABLE branch
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    logo VARCHAR(255)
);

CREATE TABLE book
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    cover VARCHAR(255),
    rating FLOAT,
    author_id INT,
    FOREIGN KEY (author_id) REFERENCES author(id)
);

CREATE TABLE branch_books
(
    books_id INT NOT NULL,
    branch_id INT NOT NULL,
    FOREIGN KEY (books_id) REFERENCES book(id),
    FOREIGN KEY (branch_id) REFERENCES branch(id)
);

INSERT INTO author (id, firstname, lastname) VALUES
(1, 'Andrzej', 'Sapkowski'),
(2, 'J.K.', 'Rowling'),
(3, 'George', 'Orwell'),
(4, 'Isaac', 'Asimov'),
(5, 'J.R.R.', 'Tolkien'),
(6, 'Stephen', 'King'),
(7, 'Dan', 'Brown'),
(8, 'Haruki', 'Murakami'),
(9, 'Olga', 'Tokarczuk'),
(10, 'Frank', 'Herbert');

INSERT INTO branch (id, name, logo) VALUES
(1, 'Centrum', 'center.png'),
(2, 'Polnoc', 'north.png'),
(3, 'Poludnie', 'south.png'),
(4, 'Wschod', 'east.png'),
(5, 'Zachod', 'west.png'),
(6, 'Akademicka', 'academy.png'),
(7, 'Miejska', 'city.png'),
(8, 'Nowa Huta', 'nh.png'),
(9, 'Bialoleka', 'bialoleka.png'),
(10, 'Praga', 'praga.png');

INSERT INTO book (id, title, cover, rating, author_id) VALUES
(1, 'Wiedzmin', 'witcher.png', 9.7, 1),
(2, 'Krew elfow', 'blood-of-elves.png', 9.5, 1),
(3, 'Harry Potter i Kamien Filozoficzny', 'hp1.png', 9.4, 2),
(4, 'Rok 1984', '1984.png', 9.8, 3),
(5, 'Fundacja', 'foundation.png', 9.3, 4),
(6, 'Wladca Pierscieni', 'lotr.png', 9.9, 5),
(7, 'To', 'it.png', 8.9, 6),
(8, 'Kod Leonarda da Vinci', 'da-vinci-code.png', 8.7, 7),
(9, 'Norwegian Wood', 'norwegian-wood.png', 8.8, 8),
(10, 'Bieguni', 'bieguni.png', 9.1, 9);

INSERT INTO branch_books (books_id, branch_id) VALUES
(1, 1),
(1, 2),
(2, 1),
(2, 3),
(3, 2),
(3, 4),
(4, 1),
(4, 5),
(5, 3),
(5, 6),
(6, 1),
(6, 7),
(7, 4),
(7, 8),
(8, 5),
(8, 9),
(9, 6),
(9, 10),
(10, 7),
(10, 8),
(2, 4),
(3, 1),
(6, 2),
(7, 3),
(8, 4),
(9, 5),
(10, 6);