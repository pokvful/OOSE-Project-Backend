package nl.han.aim.oosevt.lamport.data.entity;

public class User {
    private final int userId;
    private final String username;
    private final String email;
    private final String password;
    private final Role role;

    public User(int userId, String username, String email, String password, Role role) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }
}
