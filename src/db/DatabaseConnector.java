package db;
import bibliotec.Carte;
import users.Bibliotecar;
import users.User;

import java.sql.*;
import java.util.ArrayList;

public final class DatabaseConnector {
    private static DatabaseConnector instance;
    private final String db_path = "jdbc:postgresql://localhost:32768/postgres";
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
        logareActiune("Inserare carte");
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
        logareActiune("Cautare dupa ISBN");
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
        logareActiune("Cautare dupa titlu");
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
        logareActiune("Modificare carte");
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE carti SET title = ?, author = ?, genre = ?, description = ?, quantity = ?, numcheckedout = ?, last_accessed_date = current_timestamp WHERE isbn = ?");
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

    public void updateBook(Carte carte, Integer id_client) {
        Integer id_carte = null;
        try {
            // update num checked out
            PreparedStatement statement = connection.prepareStatement("UPDATE carti SET  numcheckedout = ? WHERE isbn = ?");
            statement.setInt(1, carte.getNumCheckedOut());
            statement.setString(2, carte.getIsbn());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        get book id
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT id FROM carti WHERE isbn = ?");
            statement.setString(1, carte.getIsbn());
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            id_carte = resultSet.getInt("id");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // verifica daca era imprumutata cartea
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM imprumut WHERE id_carte = ? AND id_client = ?");
            statement.setInt(1, id_carte);
            statement.setInt(2, id_client);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                // daca era imprumutata, adauga data returnare
                logareActiune("returnare carte");
                try {
                    PreparedStatement statement2 = connection.prepareStatement("UPDATE imprumut SET data_returnare = ? WHERE id_carte = ? AND id_client = ?");
                    statement2.setDate(1, new Date(System.currentTimeMillis()));
                    statement2.setInt(2, id_carte);
                    statement2.setInt(3, id_client);
                    statement2.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                // adauga in imprumut
                logareActiune("imprumutare carte");
                try {
                    PreparedStatement statement2 = connection.prepareStatement("INSERT INTO imprumut(id_carte, id_client) VALUES (?, ?)");
                    statement2.setInt(1, id_carte);
                    statement2.setInt(2, id_client);
                    statement2.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void registerUser(User user) {
        logareActiune("registerUser");
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO client(username, password, email, last_name, first_name) VALUES (?, ?, ?, ?, ?)");
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getLastName());
            statement.setString(5, user.getFirstName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Integer getUserId(String username, String password) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT id FROM client WHERE username = ? AND password = ?");
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt("id");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public User loginUser(String username, String password) {
        logareActiune("login");

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM client WHERE username = ? AND password = ?");
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            Integer id = resultSet.getInt("id");
            String email = resultSet.getString("email");
            String lastName = resultSet.getString("last_name");
            String firstName = resultSet.getString("first_name");
            String gender = resultSet.getString("gender");
            String ocupation = resultSet.getString("ocupation");

            User user = new User(id, username, password, firstName, lastName, email, gender, ocupation);
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    private void logareActiune(String numeActiune) {
        // logare actiune
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO loguri(nume_actiune) VALUES (?)");
            statement.setString(1, numeActiune);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Bibliotecar loginBibliotecar(String username, String password) {
        logareActiune("loginBibliotecar");
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM bibliotecar WHERE username = ? AND password = ?");
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            Integer id = resultSet.getInt("id");
            String lastName = resultSet.getString("last_name");
            String firstName = resultSet.getString("first_name");
            Bibliotecar bibliotecar = new Bibliotecar(id, username, password, firstName, lastName);
            return bibliotecar;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteBook(String isbn) {
        // delete book from carti
        logareActiune("sterge carte");
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM carti WHERE isbn = ?");
            statement.setString(1, isbn);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Carte> getCheckedOutBooks(Integer client_id) {
        logareActiune("carti imprumutate");
        ArrayList<Carte> carti = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM imprumut WHERE id_client = ? AND data_returnare IS NULL");
            statement.setInt(1, client_id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Integer id_carte = resultSet.getInt("id_carte");
                PreparedStatement statement2 = connection.prepareStatement("SELECT * FROM carti WHERE id = ?");
                statement2.setInt(1, id_carte);
                ResultSet resultSet2 = statement2.executeQuery();
                resultSet2.next();
                String isbn = resultSet2.getString("isbn");
                String title = resultSet2.getString("title");
                String author = resultSet2.getString("author");
                String genre = resultSet2.getString("genre");
                String description = resultSet2.getString("description");
                int quantity = resultSet2.getInt("quantity");
                int numCheckedOut = resultSet2.getInt("numcheckedout");
                Carte carte = new Carte(isbn, title, genre, description, author, quantity, numCheckedOut);
                carti.add(carte);
            }
                return carti;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Carte> getAvailableBooks() {
        logareActiune("carti disponibile");
        ArrayList<Carte> carti = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM carti WHERE quantity > numcheckedout");
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
                carti.add(carte);
            }
            return carti;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
