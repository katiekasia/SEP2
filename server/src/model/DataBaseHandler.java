package model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.sql.*;

public class DataBaseHandler
{
  private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
  private static final String USERNAME = "postgres";
  private static final String PASSWORD = "7890";

  private static Connection connection;

  private DataBaseHandler()
  {
  }

  // Method to get the database connection
  public static Connection getConnection() throws SQLException
  {
    if (connection == null || connection.isClosed())
    {
      // Establish a new connection if one doesn't exist or is closed
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      // Set the schema after establishing the connection
      try (Statement statement = connection.createStatement())
      {
        statement.execute("SET SEARCH_PATH TO SEP2_Cinema");
      }
    }
    return connection;
  }
  // Method to get the database connection

  // Method to close the database connection
  public static void closeConnection() throws SQLException
  {
    if (connection != null && !connection.isClosed())
    {
      connection.close();
    }
  }

  // Method to close the database connection

  public static void addTicketToDatabase(Ticket ticket,Order order) throws SQLException{
    String sql = "INSERT INTO ticket (ticketID,ticketPrice,ticketType,screeningHour,screeningDate,seatID,orderID, id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    try(Connection conn = getConnection();
   PreparedStatement statement = conn.prepareStatement(sql)){
      LocalTime.of(ticket.getHour(),ticket.getMinute(),0);
      SimpleDate date = ticket.getDate();
      statement.setString(1, ticket.getTicketID());
      statement.setDouble(2, ticket.getTicketPrice());
      statement.setString(3, ticket.getTicketType());
      statement.setTime(4, Time.valueOf(LocalTime.of(ticket.getHour(),ticket.getMinute(),0)));
      statement.setDate(5, Date.valueOf(date.getDate()));
      statement.setString(6, ticket.getSeatID());
      statement.setInt(7, order.getOrderID());
      statement.setInt(8,ticket.getScreening().getId());
      statement.executeUpdate();
    }

  }
  public static void changeTicketType(Ticket ticket) throws SQLException{
    String update = "UPDATE ticket SET ticketType = ? WHERE ticketID = ?";
    try (Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(update)) {

      connection.setAutoCommit(false);

      try {
        statement.setString(1, ticket.getTicketType());
        statement.setString(2, ticket.getTicketID());
        connection.commit();
      } catch (SQLException e) {
        connection.rollback();
        throw e;
      }
    }
  }
  public static void addSnack(Snack snack, Order order) throws SQLException{
    String sql = "INSERT INTO SNACK (snackName,size,price,orderID) VALUES (?, ?, ?, ?)";
    try(Connection conn = getConnection();
        PreparedStatement statement = conn.prepareStatement(sql)){
      statement.setString(1,snack.getType());
      statement.setString(2,snack.getSize());
      statement.setDouble(3,snack.getPrice());
      statement.setInt(4, order.getOrderID());
      statement.executeUpdate();
    }
  }
  public static void newMovie(Movie movie)throws SQLException{
    String sql = "INSERT INTO Movie (name, length, description, genre, releaseDate) VALUES(?, ?, ?, ?, ?)";
    try(Connection conn = getConnection();
        PreparedStatement statement = conn.prepareStatement(sql)){
      statement.setString(1,movie.getName());
      statement.setString(2, movie.getLenghth());
      statement.setString(3, movie.getDescription());
      statement.setString(4, movie.getGenre());
      statement.setDate(5, Date.valueOf(movie.getReleaseDate()));
      statement.executeUpdate();
    }
  }

  public static void addScreeningToDatabase(Screening screening) throws SQLException{
    String sql = "INSERT INTO Screening (screeningHour, screeningDate, roomID, name, id) VALUES(?, ?, ?, ?, ?)";
    try(Connection conn = getConnection();
        PreparedStatement statement = conn.prepareStatement(sql)){
      statement.setTime(1,Time.valueOf(LocalTime.of(screening.getHour() ,screening.getMinute(), 0)));
      statement.setDate(2, Date.valueOf(screening.getDate().getDate()));
      statement.setInt(3, screening.getRoomID());
      statement.setString(4, screening.getMovieTitle());
      statement.setInt(5, screening.getId());
      statement.executeUpdate();
    }
  }
  public static void addOrderToDatabase(Order order, String username) throws SQLException{
    String sql = "INSERT INTO orders (orderID,totalPrice,username) VALUES (?, ?, ?)";
    try(Connection conn = getConnection();
        PreparedStatement statement = conn.prepareStatement(sql)){

      statement.setInt(1, order.getOrderID());
      statement.setDouble(2, order.getOrderPrice());
      statement.setString(3, username);
      statement.executeUpdate();
    }
  }

  public static void newUser(String username, String name, String surname, String phoneNumber, String email, String password) throws SQLException {
    String sql = "INSERT INTO Customer (username, name, surname, phone_number, email, password) VALUES (?, ?, ?, ?, ?, ?)";

    try (Connection conn = getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, username);
      pstmt.setString(2, name);
      pstmt.setString(3, surname);
      pstmt.setString(4, phoneNumber);
      pstmt.setString(5, email);
      pstmt.setString(6, password);
      pstmt.executeUpdate();
    }
  }


  public static void updateUser(User user, String previousUsername) throws SQLException {
    String updateCustomerQuery = "UPDATE Customer SET name = ?, surname = ?, phone_number = ?, email = ?, password = ?, username = ? WHERE username = ?";
    String updateOrdersQuery = "UPDATE Orders SET username = ? WHERE username = ?";

    try (Connection connection = getConnection();
        PreparedStatement statementOrder = connection.prepareStatement(updateOrdersQuery);
        PreparedStatement statementUser = connection.prepareStatement(updateCustomerQuery)) {

      connection.setAutoCommit(false);

      try {
        statementUser.setString(1, user.getFstName());
        statementUser.setString(2, user.getLstName());
        statementUser.setString(3, user.getPhoneNumber());
        statementUser.setString(4, user.getEmail());
        statementUser.setString(5, user.getPassword());
        statementUser.setString(6, user.getUsername());
        statementUser.setString(7, previousUsername);
        statementUser.executeUpdate();

        statementOrder.setString(1, user.getUsername());
        statementOrder.setString(2, previousUsername);
        statementOrder.executeUpdate();

        connection.commit();

      } catch (SQLException e) {
        connection.rollback();
        throw e;
      }
    }
  }

  public static void deleteUser(String username) throws SQLException {
    String sql = "DELETE FROM Customer WHERE username = ?";

    try (Connection conn = getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, username);
      pstmt.executeUpdate();
    }
  }
  public static void deleteTicket(String ticketID) throws SQLException{
    String sql = "DELETE * FROM ticket WHERE ticketID = ?";
    try (Connection conn = getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql))
    {
      pstmt.setString(1, ticketID);
      pstmt.executeUpdate();
    }
  }
////////////////////////////////////////////////////////!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
public static void deleteScreening(Screening screening) {
  String sql = "DELETE FROM Screening WHERE id = ?";
  String sql1 = "DELETE FROM Ticket WHERE id =?";
  try (Connection conn = getConnection();
      PreparedStatement pstmt = conn.prepareStatement(sql);
  PreparedStatement statement = conn.prepareStatement(sql1)){
    statement.setInt(1, screening.getId());
    pstmt.setInt(1, screening.getId());
    statement.executeUpdate();
    int rowsDeleted = pstmt.executeUpdate();
  } catch (SQLException e) {
    System.err.println("Error deleting screening: " + e.getMessage());
  }
}

  ////////////////////////////////////!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

//  public static void deleteScreening(Screening screening) throws SQLException {
//    String sql = "DELETE FROM Screening WHERE screeningHour = ? AND screeningDate = ? AND roomID = ? AND name = ?";
//    try (Connection conn = getConnection();
//        PreparedStatement pstmt = conn.prepareStatement(sql)) {
//      // Set screeningHour as java.sql.Time
//      pstmt.setTime(1, Time.valueOf(LocalTime.of(screening.getHour(),screening.getMinute(),0)));
//      pstmt.setDate(2, java.sql.Date.valueOf(screening.getDate().getDate()));
//      pstmt.setInt(3, screening.getRoomID());
//      pstmt.setString(4, screening.getMovieTitle());
//      pstmt.executeUpdate();
//    }
//  }


  public static void deleteSnack(Snack snack, Order order) throws SQLException{
    String sql =  "DELETE * FROM Snack WHERE snackName = ? AND size = ? AND price = ? AND orderID = ?";
    try (Connection conn = getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql))
    {
      pstmt.setString(1, snack.getType());
      pstmt.setString(2, snack.getSize());
      pstmt.setDouble(3, snack.getPrice());
      pstmt.setInt(4, order.getOrderID());
      pstmt.executeUpdate();
    }
  }

  public static void deleteMovie(String title) throws SQLException{
    String sql =  "DELETE * FROM Movie WHERE name = ?";
    try (Connection conn = getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql))
    {
      pstmt.setString(1, title);
      pstmt.executeUpdate();
    }
  }
  public static void deleteOrder(int orderID) throws SQLException{
    String sql = "DELETE * FROM orders WHERE orderID = ?";
    try (Connection conn = getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql))
    {
      pstmt.setInt(1, orderID);
      pstmt.executeUpdate();
    }
  }

  // METHOD TO GET ALL THE CUSTOMERS ( USERS ) FROM DATABASE
  public static ArrayList<User> getAllCustomers() throws SQLException
  {
    ArrayList<User> customers = new ArrayList<>();
    try (Connection connection = getConnection())
    {
      String query = "SELECT * FROM Customer";
      try (Statement statement = connection.createStatement();
          ResultSet resultSet = statement.executeQuery(query))
      {
        while (resultSet.next())
        {
          String username = resultSet.getString("username");
          String fstName = resultSet.getString("name");
          String lstName = resultSet.getString("surname");
          String phoneNumber = resultSet.getString("phone_number");
          String email = resultSet.getString("email");
          String password = resultSet.getString("password");
          User user = new User(username, fstName, lstName, phoneNumber, email,
              password);
          customers.add(user);
        }
      }
    }
    return customers;
  }



  // METHOD TO GET ALL THE ROOMS FROM DATABASE
  public static ArrayList<Room> getAllRooms() throws SQLException
  {
    ArrayList<Room> rooms = new ArrayList<>();
    try (Connection connection = getConnection())
    {
      String query = "SELECT * FROM room";
      try (Statement statement = connection.createStatement();
          ResultSet resultSet = statement.executeQuery(query))
      {
        while (resultSet.next())
        {
          int roomID = resultSet.getInt("roomID");
          int numberOfSeats = resultSet.getInt("numberOfSeats");
          Room room = new Room(roomID, numberOfSeats);
          rooms.add(room);
        }
      }
    }
    return rooms;
  }
  public static PricesManager fetchPrices() throws SQLException{
    PricesManager pricesManager = new PricesManager();
    String sql = "SELECT * FROM pricesTable";
    try (Connection conn = getConnection();
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql)) {
      while (resultSet.next())
      {
        pricesManager.setCandiesPrice(resultSet.getDouble("CandiesPrice"));

        pricesManager.setColaPrice(resultSet.getDouble("ColaPrice"));

        pricesManager.setFantaPrice(resultSet.getDouble("FantaPrice"));

        pricesManager.setNachosPrice(resultSet.getDouble("NachosPrice"));
        pricesManager.setOreoPrice(resultSet.getDouble("OreoPrice"));
        pricesManager.setPeanutsPrice(resultSet.getDouble("PeanutsPrice"));
        pricesManager.setPopcornPrice(resultSet.getDouble("PopcornPrice"));
        pricesManager.setRedbullPrice(resultSet.getDouble("RedbullPrice"));
        pricesManager.setPepsiPrice(resultSet.getDouble("PepsiPrice"));
        pricesManager.setStandardTicketPrice(
            resultSet.getDouble("SticketPrice"));
        pricesManager.setTuborgPrice(resultSet.getDouble("TuborgPrice"));
        pricesManager.setVipTicketPrice(resultSet.getDouble("VticketPrice"));
      }
    }
    return pricesManager;
  }

  private static void wipePrices() throws SQLException{
    String sql = "DELETE * FROM pricesTable";
    try (Connection conn = getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.executeUpdate();
    }
  }
  public static void changePrices(PricesManager manager) throws SQLException{

    String sql = "INSERT INTO pricesTable(SticketPrice, VticketPrice, NachosPrice, PopcornPrice, CandiesPrice, PeanutsPrice, TuborgPrice, RedbullPrice, ColaPrice, PepsiPrice, OreoPrice, FantaPrice) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    try(Connection conn = getConnection();
        PreparedStatement statement = conn.prepareStatement(sql)){
      wipePrices();
      statement.setDouble(1,manager.getStandardTicketPrice());
      statement.setDouble(2,manager.getVipTicketPrice());
      statement.setDouble(3,manager.getNachosPrice());
      statement.setDouble(4,manager.getPopcornPrice());
      statement.setDouble(5,manager.getCandiesPrice());
      statement.setDouble(6,manager.getPeanutsPrice());
      statement.setDouble(7,manager.getTuborgPrice());
      statement.setDouble(8,manager.getRedbullPrice());
      statement.setDouble(9,manager.getColaPrice());
      statement.setDouble(10,manager.getPepsiPrice());
      statement.setDouble(11,manager.getOreoPrice());
      statement.setDouble(12,manager.getFantaPrice());
    }
  }

  // method to get all the movies //
  public static ArrayList<Movie> getAllMovies() throws SQLException
  {
    ArrayList<Movie> movies = new ArrayList<>();
    try (Connection connection = getConnection())
    {
      String query = "SELECT * FROM movie";
      try (Statement statement = connection.createStatement();
          ResultSet resultSet = statement.executeQuery(query))
      {
        while (resultSet.next())
        {
          String name = resultSet.getString("name");
          String length = resultSet.getString("length");
          String description = resultSet.getString("description");
          String genre = resultSet.getString("genre");
          LocalDate releaseDate = resultSet.getDate("releasedate")
              .toLocalDate();
          Movie movie = new Movie( length, description,name, genre,releaseDate);
          movies.add(movie);
        }
      }
    }
    return movies;
  }


  public static ArrayList<Order> getAllOrders(UsersList usersList)
  {
    ArrayList<Order> orders = new ArrayList<>();


    String query =
        "  SELECT o.orderID, c.username, t.ticketID, t.ticketPrice, t.ticketType, "
            + "t.seatID, s.screeningHour, s.screeningDate, s.id, r.roomID, r.numberOfSeats, m.name, "
            + " m.length, m.description, m.genre, m.releaseDate, p.snackName, p.size, p.price "
            + " FROM orders o " + "JOIN Customer c ON c.username = o.username "
            + "JOIN ticket t ON o.orderID = t.orderID "
            + "JOIN Screening s ON s.id = t.id "
            + "JOIN Movie m ON m.name = s.name "
            + "JOIN room r ON s.roomID = r.roomID "
            + "LEFT JOIN SNACK p ON p.orderID = o.orderID;";

    try (Connection connection = getConnection();
        Statement statement = connection.createStatement(
            ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery(query))
    {

      while (resultSet.next())
      {
        int orderID = resultSet.getInt("orderID");
        String username = resultSet.getString("username");
        Order order = new Order(orderID);
        User user = usersList.getByUsername(username);
        user.addOrder(order);

        do
        {
          String ticketID = resultSet.getString("ticketID");
          double price = resultSet.getDouble("ticketPrice");
          String type = resultSet.getString("ticketType");
          String seatID = resultSet.getString("seatID");
          Time screeningHour = resultSet.getTime("screeningHour");
          LocalDate screeningDate = resultSet.getDate("screeningDate")
              .toLocalDate();
          int screeningId = resultSet.getInt("id");
          String movieName = resultSet.getString("name");
          String movieLength = resultSet.getString("length");
          String movieDescription = resultSet.getString("description");
          String movieGenre = resultSet.getString("genre");
          LocalDate movieReleaseDate = resultSet.getDate("releaseDate")
              .toLocalDate();
          int roomID = resultSet.getInt("roomID");
          int numberOfSeats = resultSet.getInt("numberOfSeats");
          String snackName = resultSet.getString("snackName");
          int snackPrice = resultSet.getInt("price");
          String snackSize = resultSet.getString("size");

          Movie movie = new Movie(movieLength, movieDescription, movieName,
              movieGenre, movieReleaseDate);
          Room room = new Room(roomID, numberOfSeats);
          Seat seat = new Seat(seatID, false);
          Screening screening = new Screening( screeningId,
              screeningHour.toLocalTime().getHour(),
              screeningHour.toLocalTime().getMinute(), screeningDate, movie,
              room);

          Snack snack;
          if (snackName != null)
          {
            snack = new Snack(snackPrice, snackName,snackSize);
            order.addSnack(snack);
          }

          Ticket ticket = null;
          if ("Standard".equals(type))
          {
            ticket = new StandardTicket(ticketID, price, seat, screening);
          }
          else if ("VIP".equals(type))
          {
            ticket = new VIPTicket(ticketID, price, seat, screening);
          }
          else
          {
            continue; // Skip unknown ticket types
          }
          order.addTicket(ticket);
        }
        while (resultSet.next() && resultSet.getString("orderID")
            .equals(orderID));

        orders.add(order);
        resultSet.previous(); // Move back one step for the outer while loop to function correctly
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace(); // Log the exception or handle it accordingly
    }

    return orders;
  }


  public static ArrayList<Screening> getAllScreenings() throws SQLException
  {
    ArrayList<Screening> screenings = new ArrayList<>();
    String query =
        "SELECT s.screeningHour,s.id, s.screeningDate, m.name AS movieName, m.length AS movieLength, "
            + "m.description AS movieDescription, m.genre AS movieGenre, m.releaseDate AS movieReleaseDate, "
            + "r.roomID, r.numberOfSeats " + "FROM SEP2_Cinema.Screening s "
            + "JOIN SEP2_Cinema.Movie m ON s.name = m.name "
            + "JOIN SEP2_Cinema.Room r ON s.roomID = r.roomID";

    try (Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query))
    {

      while (resultSet.next())
      {
        Time screeningHour = resultSet.getTime("screeningHour");
        LocalDate screeningDate = resultSet.getDate("screeningDate")
            .toLocalDate();
        int screeningId = resultSet.getInt("id");
        String movieName = resultSet.getString("movieName");
        String movieLength = resultSet.getString("movieLength");
        String movieDescription = resultSet.getString("movieDescription");
        String movieGenre = resultSet.getString("movieGenre");
        LocalDate movieReleaseDate = resultSet.getDate("movieReleaseDate")
            .toLocalDate();
        int roomID = resultSet.getInt("roomID");
        int numberOfSeats = resultSet.getInt("numberOfSeats");

        Movie movie = new Movie(movieLength, movieDescription, movieName,
            movieGenre,movieReleaseDate);
        Room room = new Room(roomID, numberOfSeats);
        Screening screening = new Screening(screeningId,
            screeningHour.toLocalTime().getHour(),
            screeningHour.toLocalTime().getMinute(), screeningDate, movie,
            room);
        screenings.add(screening);
      }
    }
    return screenings;
  }
}







