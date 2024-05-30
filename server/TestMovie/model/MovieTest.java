package model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.DateTimeException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest
{
  private static Movie movie;

  @BeforeAll public static void setup()
  {
    movie = new Movie("2h", "Action-packed thriller",
        "The Invisible Man", "Horror", LocalDate.of(2020, 2, 28));
  }

  @Test
  void testZeroMovieReleaseDate() {
    assertNotNull(movie.getReleaseDate(), "Release date should not be null for a valid movie");
  }
  @Test
  void testOneMovieReleaseDate() {
    assertEquals(LocalDate.of(2020, 2, 28), movie.getReleaseDate(),
        "Movie date should match the set name");
  }
  @Test
  void testManyMovieReleaseDate() {
    Movie movie1 = new Movie("2h", "Thrilling adventure",
        "Pirates of the Caribbean",
        "Action", LocalDate.of(2003, 7, 9));
    Movie movie2 = new Movie("1h45m", "Family-friendly animation", "Toy Story",
        "Animation", LocalDate.of(1995, 11, 22));
    Movie movie3 = new Movie("2h15m", "Epic fantasy",
        "The Lord of the Rings: The Fellowship of the Ring",
        "Fantasy", LocalDate.of(2001, 12, 19));

    assertAll("releaseDate",
        () -> assertEquals(LocalDate.of(2003,7,9),
            movie1.getReleaseDate(), "Movie date should match"),
        () -> assertEquals(LocalDate.of(1995,11,22),
            movie2.getReleaseDate(), "Movie date should match"),
        () -> assertEquals(LocalDate.of(2001,12,19),
            movie3.getReleaseDate(), "Movie date should match")
    );
  }
  @Test
  void testBoundaryMovieReleaseDate() {
    Movie movie = new Movie("1h:3m", "Emotional drama", "Forrest Gump",
        "Drama", LocalDate.of(0000,1,1));
    assertNotNull(movie.getReleaseDate(), "Movie date should not be null");
    assertTrue(movie.getReleaseDate().equals(LocalDate.of(0000, 1, 1)),
        "Movie date should be 0000-01-01 for an unspecified date");
  }
  @Test
  void testExceptionalMovieReleaseDate() {
    assertThrows(DateTimeException.class, () -> {
      movie = new Movie("2h", "Action-packed thriller", "The Invisible Man",
          "Horror", LocalDate.of(2020, 2, 0));
      movie.getReleaseDate();
    }, "Exception should be thrown for invalid release date");
  }

  /*
      GET MOVIE LENGTH
   */
  @Test
  void testZeroMovieLength() {
    Movie movie = new Movie("", "Adventure film", "Jurassic Park", "Science Fiction", LocalDate.of(1993, 6, 11));
    assertNotNull(movie.getLenghth(), "Length should not be null for a movie");
  }
  @Test
  void testOneMovieLength() {
    Movie movie = new Movie("1h30m", "ssss", "The Shawshank Redemption", "Drama", LocalDate.of(1994, 9, 23));
    assertEquals("1h30m", movie.getLenghth(), "Movie length should match the set name");
  }
  @Test
  void testManyMovieLength() {
    Movie movie1 = new Movie("2h", "Thrilling adventure", "Pirates of the Caribbean", "Action", LocalDate.of(2003, 7, 9));
    Movie movie2 = new Movie("1h45m", "Family-friendly animation", "Toy Story", "Animation", LocalDate.of(1995, 11, 22));
    Movie movie3 = new Movie("2h15m", "Epic fantasy", "The Lord of the Rings: The Fellowship of the Ring", "Fantasy", LocalDate.of(2001, 12, 19));

    assertAll("length",
        () -> assertEquals("2h", movie1.getLenghth(), "Movie length should match"),
        () -> assertEquals("1h45m", movie2.getLenghth(), "Movie length should match"),
        () -> assertEquals("2h15m", movie3.getLenghth(), "Movie length should match")
    );
  }
  @Test
  void testBoundaryMovieLength() {
    Movie movie = new Movie("", "Emotional drama", "Forrest Gump", "Drama", LocalDate.of(1994, 7, 6));
    assertNotNull(movie.getLenghth(), "Movie length should not be null");
    assertTrue(movie.getLenghth().isEmpty(), "Movie length should be empty for an unspecified length");
  }
  @Test
  void testExceptionalMovieLength() {
    assertThrows(IllegalArgumentException.class, () -> {
      Movie movie = new Movie("", "Mysterious thriller", "Inception", "Sci-Fi", LocalDate.of(2040, 3, 1));
      movie.getLenghth();
    }, "Exception should be thrown for empty length");
  }
/*
    GET MOVIE DESCRIPTION
 */
  @Test
  void testZeroMovieDescription() {
    Movie movie = new Movie("2h", "", "The Godfather", "Crime", LocalDate.of(1972, 3, 24));
    assertNotNull(movie.getDescription(), "Description should not be null for a movie");
  }
  @Test
  void testOneMovieDescription() {
    Movie movie = new Movie("1h30m", "TEST", "The Shawshank Redemption", "Drama", LocalDate.of(1994, 9, 23));
    assertEquals("TEST", movie.getDescription(), "Movie description should match the set name");
  }
  @Test
  void testManyMovieDescription() {
    Movie movie1 = new Movie("2h", "Thrilling adventure", "Pirates of the Caribbean", "Action", LocalDate.of(2003, 7, 9));
    Movie movie2 = new Movie("1h45m", "Family-friendly animation", "Toy Story", "Animation", LocalDate.of(1995, 11, 22));
    Movie movie3 = new Movie("2h15m", "Epic fantasy", "The Lord of the Rings: The Fellowship of the Ring", "Fantasy", LocalDate.of(2001, 12, 19));

    assertAll("description",
        () -> assertEquals("Thrilling adventure", movie1.getDescription(), "Movie description should match"),
        () -> assertEquals("Family-friendly animation", movie2.getDescription(), "Movie description should match"),
        () -> assertEquals("Epic fantasy", movie3.getDescription(), "Movie description should match")
    );
  }
  @Test
  void testBoundaryMovieDescription() {
    Movie movie = new Movie("2h:3m", "", "Forrest Gump", "Drama", LocalDate.of(1994, 7, 6));
    assertNotNull(movie.getDescription(), "Movie description should not be null");
    assertTrue(movie.getDescription().isEmpty(), "Movie description should be empty for an unspecifieddescription");
  }
  @Test
  void testExceptionalMovieDescription() {
    assertThrows(IllegalArgumentException.class, () -> {
      Movie movie = new Movie("1h:3m", "", "Inception", "Sci-Fi", LocalDate.of(2040, 3, 1));
      movie.getDescription();
    }, "Exception should be thrown for empty description");
  }
  /*
      GET MOVIE NAME
   */
  @Test
  void testZeroMovieName() {
    Movie movie = new Movie("1h30m", "Heartwarming story", "", "Drama", LocalDate.of(1994, 9, 23));
    assertNotNull(movie.getName(), "Name should not be null for a movie");
  }
  @Test
  void testOneMovieName() {
    Movie movie = new Movie("1h30m", "Heartwarming story", "The Shawshank Redemption", "Drama", LocalDate.of(1994, 9, 23));
    assertEquals("The Shawshank Redemption", movie.getName(), "Movie name should match the set name");
  }

  @Test void testManyMovieName()
  {
    Movie movie1 = new Movie("2h", "Thrilling adventure",
        "Pirates of the Caribbean", "Action", LocalDate.of(2003, 7, 9));
    Movie movie2 = new Movie("1h45m", "Family-friendly animation", "Toy Story",
        "Animation", LocalDate.of(1995, 11, 22));
    Movie movie3 = new Movie("2h15m", "Epic fantasy",
        "The Lord of the Rings: The Fellowship of the Ring", "Fantasy",
        LocalDate.of(2001, 12, 19));

    assertAll("name", () -> assertEquals("Pirates of the Caribbean", movie1.getName(),
            "Movie name should match"),
        () -> assertEquals("Toy Story", movie2.getName(),
            "Movie name should match"),
        () -> assertEquals("The Lord of the Rings: The Fellowship of the Ring", movie3.getName(),
            "Movie name should match"));
}
  @Test
  void testBoundaryMovieName() {
    Movie movie = new Movie("2h:3m", "idk", "", "Drama", LocalDate.of(1994, 7, 6));
    assertNotNull(movie.getName(), "Movie name should not be null");
    assertTrue(movie.getName().isEmpty(), "Movie name should be empty for an unspecified name");
  }
  @Test
  void testExceptionalMovieName() {
    assertThrows(IllegalArgumentException.class, () -> {
      Movie movie = new Movie("1h:3m", "aaa", "", "Sci-Fi", LocalDate.of(2040, 3, 1));
      movie.getName();
    }, "Exception should be thrown for empty name");
  }
  /*
      GET MOVIE GENRE
   */
  @Test
  void testZeroMovieGenre() {
    Movie movie = new Movie("2h", "Adventure film", "Pirates of the Caribbean", "", LocalDate.of(2003, 7, 9));
    assertNotNull(movie.getGenre(), "Genre should not be null for a movie");
  }
  @Test
  void testOneMovieGenre() {
    Movie movie = new Movie("1h30m", "Heartwarming story", "The Shawshank Redemption", "Drama", LocalDate.of(1994, 9, 23));
    assertEquals("Drama", movie.getGenre(), "Movie genre should match the set name");
  }
  @Test
  void testManyMovieGenre() {
    Movie movie1 = new Movie("2h", "Thrilling adventure", "Pirates of the Caribbean", "Action", LocalDate.of(2003, 7, 9));
    Movie movie2 = new Movie("1h45m", "Family-friendly animation", "Toy Story", "Animation", LocalDate.of(1995, 11, 22));
    Movie movie3 = new Movie("2h15m", "Epic fantasy", "The Lord of the Rings: The Fellowship of the Ring", "Fantasy", LocalDate.of(2001, 12, 19));

    assertAll("genre",
        () -> assertEquals("Action", movie1.getGenre(), "Movie genre should match"),
        () -> assertEquals("Animation", movie2.getGenre(), "Movie genre should match"),
        () -> assertEquals("Fantasy", movie3.getGenre(), "Movie genre should match")
    );
  }
  @Test
  void testBoundaryMovieGenre() {
    Movie movie = new Movie("2h:3m", "aaa", "Forrest Gump", "", LocalDate.of(1994, 7, 6));
    assertNotNull(movie.getGenre(), "Movie genre should not be null");
    assertTrue(movie.getGenre().isEmpty(), "Movie genre should be empty for an unspecified genre");
  }
  @Test
  void testExceptionalMovieGenre() {
    assertThrows(IllegalArgumentException.class, () -> {
      Movie movie = new Movie("1h:3m", "Name", "Inception", "", LocalDate.of(2040, 3, 1));
      movie.getGenre();
    }, "Exception should be thrown for empty genre");
  }
  /*
      GET RELEASE STRING
   */
  @Test
  void testZeroMovieReleaseString() {
    Movie movie = new Movie("1h30m", "Mysterious thriller", "Inception", "Sci-Fi", LocalDate.of(2010, 7, 16));
    assertNotNull(movie.getReleaseString(), "Release string should not be null for a movie");
  }
  @Test
  void testOneMovieReleaseString() {
    Movie movie = new Movie("1h30m", "Heartwarming story", "The Shawshank Redemption", "Drama", LocalDate.of(1994, 9, 23));
    assertEquals("1994-09-23", movie.getReleaseString(), "Movie genre should match the set name");
  }
  @Test
  void testManyMovieReleaseString() {
    Movie movie1 = new Movie("2h", "Thrilling adventure", "Pirates of the Caribbean", "Action", LocalDate.of(2000, 1, 1));
    Movie movie2 = new Movie("1h45m", "Family-friendly animation", "Toy Story", "Animation", LocalDate.of(2000, 1, 1));
    Movie movie3 = new Movie("2h15m", "Epic fantasy", "The Lord of the Rings: The Fellowship of the Ring", "Fantasy", LocalDate.of(2000, 1, 1));

    assertAll("releaseDate",
        () -> assertEquals("2000-01-01", movie1.getReleaseString(), "Movie release string should match"),
        () -> assertEquals("2000-01-01", movie2.getReleaseString(), "Movie release string should match"),
        () -> assertEquals("2000-01-01", movie3.getReleaseString(), "Movie release string should match")
    );
  }
  @Test
  void testBoundaryMovieReleaseString() {
    Movie movie = new Movie("3h:2m", "Emotional drama", "", "Drama", LocalDate.of(1994, 7, 6));
    assertNotNull(movie.getReleaseString(), "Movie release date String should not be null");
  }

  @Test
  void testExceptionalMovieReleaseString() {
    Movie movie = new Movie("1h30m", "Mysterious thriller", "Inception", "Sci-Fi", LocalDate.of(2010, 7, 16));
    assertDoesNotThrow(movie::getReleaseString, "No exception should be thrown when getting release date as string");
  }

  @Test
  void testSetLength_Zero() {
    Movie movie = new Movie("2h", "Adventure film", "Jurassic Park", "Science Fiction", LocalDate.of(1993, 6, 11));
    movie.setLength("");
    assertTrue(movie.getLenghth().isEmpty(), "Movie length should be empty after setting an empty length");
  }

  @Test
  void testSetLength_One() {
    Movie movie = new Movie("2h", "Adventure film", "Jurassic Park", "Science Fiction", LocalDate.of(1993, 6, 11));
    movie.setLength("1h45m");
    assertEquals("1h45m", movie.getLenghth(), "Movie length should match the set length");
  }

  @Test
  void testSetLength_Many() {
    Movie movie = new Movie("2h", "Adventure film", "Jurassic Park", "Science Fiction", LocalDate.of(1993, 6, 11));
    movie.setLength("3h");
    assertEquals("3h", movie.getLenghth(), "Movie length should match the set length");
  }

  @Test
  void testSetLength_Boundary() {
    Movie movie = new Movie("2h", "Adventure film", "Jurassic Park", "Science Fiction", LocalDate.of(1993, 6, 11));
    movie.setLength("");
    assertTrue(movie.getLenghth().isEmpty(), "Movie length should be empty after setting an empty length");
  }

  @Test
  void testSetLength_Exceptional() {
    assertThrows(IllegalArgumentException.class, () -> {
      Movie movie = new Movie("2h", "Adventure film", "Jurassic Park", "Science Fiction", LocalDate.of(1993, 6, 11));
      movie.setLength("-1h");
    }, "Exception should be thrown for invalid length");
  }

  @Test
  void testSetDescription_Zero() {
    Movie movie = new Movie("2h", "", "Jurassic Park", "Science Fiction", LocalDate.of(1993, 6, 11));
    movie.setDescription("");
    assertTrue(movie.getDescription().isEmpty(), "Movie description should be empty after setting an empty description");
  }

  @Test
  void testSetDescription_One() {
    Movie movie = new Movie("2h", "", "Jurassic Park", "Science Fiction", LocalDate.of(1993, 6, 11));
    movie.setDescription("Dinosaur adventure");
    assertEquals("Dinosaur adventure", movie.getDescription(), "Movie description should match the set description");
  }

  @Test
  void testSetDescription_Many() {
    Movie movie = new Movie("2h", "", "Jurassic Park", "Science Fiction", LocalDate.of(1993, 6, 11));
    movie.setDescription("Dinosaur adventure with thrilling action scenes");
    assertEquals("Dinosaur adventure with thrilling action scenes", movie.getDescription(), "Movie description should match the set description");
  }

  @Test
  void testSetDescription_Boundary() {
    Movie movie = new Movie("2h", "", "Jurassic Park", "Science Fiction", LocalDate.of(1993, 6, 11));
    movie.setDescription("");
    assertTrue(movie.getDescription().isEmpty(), "Movie description should be empty after setting an empty description");
  }
  @Test
  void testSetDescription_Exceptional() {
    assertThrows(IllegalArgumentException.class, () -> {
      Movie movie = new Movie("2h", "Adventure film", "Jurassic Park", "Science Fiction", LocalDate.of(1993, 6, 11));
      movie.setDescription("");
    }, "Exception should be thrown for invalid description");
  }

  @Test
  void testSetName_Zero() {
    Movie movie = new Movie("2h", "Dinosaur adventure", "", "Science Fiction", LocalDate.of(1993, 6, 11));
    movie.setName("");
    assertTrue(movie.getName().isEmpty(), "Movie name should be empty after setting an empty name");
  }

  @Test
  void testSetName_One() {
    Movie movie = new Movie("2h", "Dinosaur adventure", "", "Science Fiction", LocalDate.of(1993, 6, 11));
    movie.setName("Jurassic Park");
    assertEquals("Jurassic Park", movie.getName(), "Movie name should match the set name");
  }

  @Test
  void testSetName_Many() {
    Movie movie = new Movie("2h", "Dinosaur adventure", "", "Science Fiction", LocalDate.of(1993, 6, 11));
    movie.setName("Jurassic Park: The Lost World");
    assertEquals("Jurassic Park: The Lost World", movie.getName(), "Movie name should match the set name");
  }

  @Test
  void testSetName_Boundary() {
    Movie movie = new Movie("2h", "Dinosaur adventure", "", "Science Fiction", LocalDate.of(1993, 6, 11));
    movie.setName("");
    assertTrue(movie.getName().isEmpty(), "Movie name should be empty after setting an empty name");
  }
  @Test
  void testSetName_Exceptional() {
    assertThrows(IllegalArgumentException.class, () -> {
      Movie movie = new Movie("2h", "Adventure film", "Jurassic Park", "Science Fiction", LocalDate.of(1993, 6, 11));
      movie.setName("");
    }, "Exception should be thrown for invalid name");
  }
  @Test
  void testSetGenre_Zero() {
    Movie movie = new Movie("2h", "Dinosaur adventure", "Jurassic Park", "", LocalDate.of(1993, 6, 11));
    movie.setGenre("");
    assertTrue(movie.getGenre().isEmpty(), "Movie genre should be empty after setting an empty genre");
  }

  @Test
  void testSetGenre_One() {
    Movie movie = new Movie("2h", "Dinosaur adventure", "Jurassic Park", "", LocalDate.of(1993, 6, 11));
    movie.setGenre("Science Fiction");
    assertEquals("Science Fiction", movie.getGenre(), "Movie genre should match the set genre");
  }

  @Test
  void testSetGenre_Many() {
    Movie movie = new Movie("2h", "Dinosaur adventure", "Jurassic Park", "", LocalDate.of(1993, 6, 11));
    movie.setGenre("Adventure");
    assertEquals("Adventure", movie.getGenre(), "Movie genre should match the set genre");
  }

  @Test
  void testSetGenre_Boundary() {
    Movie movie = new Movie("2h", "Dinosaur adventure", "Jurassic Park", "", LocalDate.of(1993, 6, 11));
    movie.setGenre("");
    assertTrue(movie.getGenre().isEmpty(), "Movie genre should be empty after setting an empty genre");
  }
  @Test
  void testSetGenre_Exceptional() {
    assertThrows(IllegalArgumentException.class, () -> {
      Movie movie = new Movie("2h", "Adventure film", "Jurassic Park", "Science Fiction", LocalDate.of(1993, 6, 11));
      movie.setGenre("");
    }, "Exception should be thrown for invalid genre");
  }
}