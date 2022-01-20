package no.ntnu.books;

import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

/**
 * REST API controller for author collection
 */
@RestController
public class AuthorController {
  private List<Author> authors;

  /**
   * Instantiates a new Author controller.
   */
  public AuthorController() {
    initializeData();
  }

  /**
   * Initialize dummy author data for the author collection
   */
  private void initializeData() {
    this.authors = new LinkedList<>();

    this.authors.add(new Author(1, "Jordan", "Peterson", 1965));
    this.authors.add(new Author(2, "Michal", "Berg", 1999));
    this.authors.add(new Author(3, "Mygla", "Balj", 1834));
  }
}
