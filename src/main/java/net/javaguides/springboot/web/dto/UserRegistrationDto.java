package net.javaguides.springboot.web.dto;

public class UserRegistrationDto {
    private String username; // Ensure this field exists
    private String firstName;
    private String lastName;
    private String email;
    private String mobile; // Ensure this field exists
    private String password;

    // Default constructor
    public UserRegistrationDto() {
    }

    // Parameterized constructor
    public UserRegistrationDto(String username, String firstName, String lastName, String email, String mobile, String password) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
    }

    // Getter and Setter methods
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
