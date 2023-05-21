package db;
import bibliotec.Carte;

import java.sql.*;
import java.util.ArrayList;

public final class DatabaseConnector {
    private static DatabaseConnector instance;
    private String db_path = "jdbc:postgresql://localhost:32768/postgres";
    private Connection connection;

    private DatabaseConnector() {
        try {
            this.connection = DriverManager.getConnection(this.db_path, "postgres", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DatabaseConnector getInstance() {
        if (instance == null) {
            instance = new DatabaseConnector();
        }
        return instance;
    }

    public void insertBook(Carte carte) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO carti (isbn, title, author,genre, description, quantity, numcheckedout) VALUES (?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, carte.getIsbn());
            statement.setString(2, carte.getTitle());
            statement.setString(3, carte.getAuthor());
            statement.setString(4, carte.getGenre());
            statement.setString(5, carte.getDescription());
            statement.setInt(6, carte.getQuantity());
            statement.setInt(7, carte.getNumCheckedOut());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Carte findByIsbn(String isbn) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM carti WHERE isbn = ?");
            statement.setString(1, isbn);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            String genre = resultSet.getString("genre");
            String description = resultSet.getString("description");
            int quantity = resultSet.getInt("quantity");
            int numCheckedOut = resultSet.getInt("numcheckedout");

            Carte carte = new Carte(isbn, title, genre, description, author, quantity, numCheckedOut);
            return carte;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Carte> findByTitle(String keyword) {
        ArrayList<Carte> booksFound = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM carti WHERE upper(title) LIKE upper(?) ");
            statement.setString(1, "%" + keyword + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String isbn = resultSet.getString("isbn");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String genre = resultSet.getString("genre");
                String description = resultSet.getString("description");
                int quantity = resultSet.getInt("quantity");
                int numCheckedOut = resultSet.getInt("numcheckedout");

                Carte carte = new Carte(isbn, title, genre, description, author, quantity, numCheckedOut);
                booksFound.add(carte);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return booksFound;
    }

    public void updateBook(Carte carte) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE carti SET title = ?, author = ?, genre = ?, description = ?, quantity = ?, numcheckedout = ? WHERE isbn = ?");
            statement.setString(1, carte.getTitle());
            statement.setString(2, carte.getAuthor());
            statement.setString(3, carte.getGenre());
            statement.setString(4, carte.getDescription());
            statement.setInt(5, carte.getQuantity());
            statement.setInt(6, carte.getNumCheckedOut());
            statement.setString(7, carte.getIsbn());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

//            // Process the results
//            while (resultSet.next()) {
//                String data = resultSet.getString("title");
//                System.out.println(data);
//                // Process data
//            }
//                if (resultSet != null) resultSet.close();
//                if (statement != null) statement.close();
