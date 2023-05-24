package users;

public interface IUser {
    public User login(String username, String password);
    public void register(User user);
    public String getUsername();
    public void setUsername(String username);
    public Integer getId();
    public String getPassword();
    public void setPassword(String password);
    public String getFirstName();
    public void setFirstName(String firstName);
    public String getLastName();
    public void setLastName(String lastName);
    public String getEmail();
    public void setEmail(String email);
}
