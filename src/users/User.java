package users;

import db.DatabaseConnector;

public class User {
    private Integer id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String role;

    private final DatabaseConnector db = DatabaseConnector.getInstance();

    public User(){

    }

    public User(Integer id, String username, String password,String firstName ,String lastName, String email, String address, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.role = role;
    }

    public void login(String username, String password) {
        System.out.println("User.login");
    }

    public void register(User user){
        db.registerUser(user);
    }

    public String getUsername() {
        return username;
    }

    public Integer getId() {
        id = db.getUserId(username, password);
        return id;
    }

    private void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
