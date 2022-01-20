package no.ntnu.books;

import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

/**
 * REST API controller for book collection
 */
@RestController
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
}
