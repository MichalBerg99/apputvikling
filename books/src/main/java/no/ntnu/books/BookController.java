package no.ntnu.books;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.HTMLDocument;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * REST API controller for book collection
 */
@RestController
@RequestMapping("/books")
public class BookController {
    private List<Book> books;

    public BookController() {
        initializeData();
    }

    /**
     * Initialize dummy book data for the book collection
     */
    private void initializeData() {
        this.books = new LinkedList<>();

        this.books.add(new Book(1, 2016, 100, "Computer Science"));
        this.books.add(new Book(2, 2017, 112, "12 Rules For Life"));
        this.books.add(new Book(3, 1999, 98, "The Swoly Bible"));
    }

    @GetMapping("")
    public List<Book> getAll() {
        return this.books;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Integer id) {
        ResponseEntity<Book> response;
        Book book = findBookById(id);
        if (book != null) {
            response = new ResponseEntity<>(book, HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @PostMapping
    public ResponseEntity<String> add(@RequestBody Book book) {
        ResponseEntity<String> response;
        if (book != null && book.isValid()) {
            this.books.add(book);
            response = new ResponseEntity<>(HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return response;
    }


    /**
     * Searches for book with specified ID
     *
     * @param id the id of the book to be found
     * @return the book with matching id
     */
    private Book findBookById(Integer id) {
        Book book = null;
        Iterator<Book> iterator = this.books.iterator();
        while (book == null && iterator.hasNext()) {
            Book b = iterator.next();
            if (b.getId() == id) {
                book = b;
            }
        }
        return book;
    }

    /**
     * Delete a book from the collection
     *
     * @param id ID of the book to delete
     * @return 200 OK on success, 404 Not found on error
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        ResponseEntity<String> response;
        Book book = findBookById(id);
        if (book != null) {
            books.remove(book);
            response = new ResponseEntity<>(HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }

    /**
     * Update a book in the repository
     *
     * @param id   ID of the book to update, from the URL
     * @param book New book data to store, from request body
     * @return 200 OK on success, 400 Bad request on error
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody Book book) {
        String errorMessage = null;
        Book existingBook = findBookById(id);
        if (existingBook == null) {
            errorMessage = "No book with id " + id + " found";
        }
        if (book == null || !book.isValid()) {
            errorMessage = "Wrong data in request body";
        } else if (book.getId() != id) {
            errorMessage = "Book ID in the URL does not match the ID in JSON data (response body)";
        }

        ResponseEntity<String> response;
        if (errorMessage == null) {
            books.remove(existingBook);
            books.add(book);
            response = new ResponseEntity<>(HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }

        return response;
    }
}
