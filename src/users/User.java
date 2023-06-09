package users;

import db.DatabaseConnector;

public class User implements IUser{
    protected Integer id;
    protected String username;
    protected String password;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String address;
    protected String role;

    protected final DatabaseConnector db = DatabaseConnector.getInstance();

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

    public User login(String username, String password) {
        User user = db.loginUser(username, password);
        return user;
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

}
