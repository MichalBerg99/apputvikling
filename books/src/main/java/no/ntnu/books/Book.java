package no.ntnu.books;

/**
 * Represents a book
 */
public class Book {
  private int id;
  private int year;
  private int numberOfPages;
  private String title;

  /**
   * Gets id.
   *
   * @return the id
   */
  public int getId() {
    return id;
  }

  /**
   * Gets year.
   *
   * @return the year
   */
  public int getYear() {
    return year;
  }

  /**
   * Gets number of pages.
   *
   * @return the number of pages
   */
  public int getNumberOfPages() {
    return numberOfPages;
  }

  /**
   * Gets title.
   *
   * @return the title
   */
  public String getTitle() {
    return title;
  }

  /**
   * Sets id.
   *
   * @param id the id
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Sets year.
   *
   * @param year the year
   */
  public void setYear(int year) {
    this.year = year;
  }

  /**
   * Sets number of pages.
   *
   * @param numberOfPages the number of pages
   */
  public void setNumberOfPages(int numberOfPages) {
    this.numberOfPages = numberOfPages;
  }

  /**
   * Sets title.
   *
   * @param title the title
   */
  public void setTitle(String title) {
    this.title = title;
  }
}
