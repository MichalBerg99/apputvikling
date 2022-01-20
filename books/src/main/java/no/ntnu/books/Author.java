package no.ntnu.books;

/**
 * Represents an author.
 */
public class Author {
  private int id;
  private String firstName;
  private String lastName;
  private int birthYear;

  /**
   * Instantiates a new Author.
   *
   * @param id        the id
   * @param firstName the first name
   * @param lastName  the last name
   * @param birthYear the birth year
   */
  public Author(int id, String firstName, String lastName, int birthYear) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.birthYear = birthYear;
  }

  /**
   * Gets id.
   *
   * @return the id
   */
  public int getId() {
    return id;
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
   * Gets first name.
   *
   * @return the first name
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Sets first name.
   *
   * @param firstName the first name
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * Gets last name.
   *
   * @return the last name
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Sets last name.
   *
   * @param lastName the last name
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * Gets birth year.
   *
   * @return the birth year
   */
  public int getBirthYear() {
    return birthYear;
  }

  /**
   * Sets birth year.
   *
   * @param birthYear the birth year
   */
  public void setBirthYear(int birthYear) {
    this.birthYear = birthYear;
  }
}
