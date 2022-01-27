package no.ntnu.crudrest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * REST API controller for author collection
 */
@RestController
@RequestMapping("authors")
public class AuthorController {
  private List<no.ntnu.crudrest.Author> authors;

  public AuthorController() {
    initializeData();
  }

  /**
   * Initialize dummy author data for the collection
   */
  private void initializeData() {
    authors = new LinkedList<>();
    authors.add(new no.ntnu.crudrest.Author(1, "Michal", "Berg", 1999));
    authors.add(new no.ntnu.crudrest.Author(2, "Mathisen", "Venstrebaljen", 1873));
    authors.add(new no.ntnu.crudrest.Author(3, "Jordan", "Peterson", 1960));
  }

  /**
   * Get All authors
   * HTTP GET to /authors
   * @return List of all authors currently stored in the collection
   */
  @GetMapping
  public List<no.ntnu.crudrest.Author> getAll() {
    return authors;
  }

  /**
   * Get a specific author
   * @param id ID of the author to be returned
   * @return Author with the given ID or status 404
   */
  @GetMapping("/{id}")
  public ResponseEntity<no.ntnu.crudrest.Author> getOne(@PathVariable Integer id) {
    ResponseEntity<no.ntnu.crudrest.Author> response;
    no.ntnu.crudrest.Author author = findAuthorById(id);
    if (author != null) {
      response = new ResponseEntity<>(author, HttpStatus.OK);
    } else {
      response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return response;
  }

  /**
   * Add an author to the collection
   * @param author Author to be added, from HTTP response body
   * @return 200 OK status on success, 400 Bad request on error
   */
  @PostMapping
  public ResponseEntity<String> add(@RequestBody no.ntnu.crudrest.Author author) {
    ResponseEntity<String> response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    if (author != null && author.isValid()) {
      no.ntnu.crudrest.Author existingAuthor = findAuthorById(author.getId());
      if (existingAuthor == null) {
        authors.add(author);
        response = new ResponseEntity<>(HttpStatus.OK);
      }
    }
    return response;
  }

  /**
   * Delete an author from the collection
   * @param id ID of the author to delete
   * @return 200 OK on success, 404 Not found on error
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<String> delete(@PathVariable int id) {
    ResponseEntity<String> response;
    no.ntnu.crudrest.Author author = findAuthorById(id);
    if (author != null) {
      authors.remove(author);
      response = new ResponseEntity<>(HttpStatus.OK);
    } else {
      response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return response;
  }

  /**
   * Update an author in the repository
   * @param id ID of the author to update, from the URL
   * @param author New author data to store, from request body
   * @return 200 OK on success, 400 Bad request on error
   */
  @PutMapping("/{id}")
  public ResponseEntity<String> update(@PathVariable int id, @RequestBody no.ntnu.crudrest.Author author) {
    String errorMessage = null;
    no.ntnu.crudrest.Author existingAuthor = findAuthorById(id);
    if (existingAuthor == null) {
      errorMessage = "No author with id " + id + " found";
    }
    if (author == null || !author.isValid()) {
      errorMessage = "Wrong data in request body";
    } else if (author.getId() != id) {
      errorMessage = "Author ID in the URL does not match the ID in JSON data (response body)";
    }

    ResponseEntity<String> response;
    if (errorMessage == null) {
      authors.remove(existingAuthor);
      authors.add(author);
      response = new ResponseEntity<>(HttpStatus.OK);
    } else {
      response = new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    return response;
  }

  /**
   * Search through the author collection, find the author by given ID
   * @param id Author ID
   * @return Author or null if not found
   */
  private no.ntnu.crudrest.Author findAuthorById(Integer id) {
    no.ntnu.crudrest.Author author = null;
    Iterator<no.ntnu.crudrest.Author> it = authors.iterator();
    while (it.hasNext() && author == null) {
      no.ntnu.crudrest.Author b = it.next();
      if (b.getId() == id) {
        author = b;
      }
    }
    return author;
  }
}
