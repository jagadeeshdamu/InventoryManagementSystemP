package inventoryManagementSytem.login;

public class Login {
    private int id;
    private String email;
    private String password;

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    @Override
    public String toString() {
        return "Login[" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ']';
    }
}
