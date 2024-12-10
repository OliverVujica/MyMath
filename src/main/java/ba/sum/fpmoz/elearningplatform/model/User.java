package ba.sum.fpmoz.elearningplatform.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Size(min = 2, max = 50)
    @NotBlank(message = "Ime je obavezno")
    private String firstname;
    @Size(min = 2, max = 50)
    @NotBlank(message = "Prezime je obavezno")
    private String lastname;
    @NotBlank(message = "Email je obavezan")
    @Email(message = "Email nije validan")
    private String email;
    @NotBlank(message = "Molimo unesite lozinku")
    private String password;
    @NotBlank(message = "Molimo potvrdite lozinku")
    @Transient
    private String passwordConfirm;

    public User() {
    }

    public User(int id, String firstname, String lastname, String email, String password, String passwordConfirm) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
    }

    public int getId() {
        return id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    @AssertTrue(message = "Lozinke se ne podudaraju")
    public boolean isPasswordMatch() {
        try {
            return this.password.equals(this.passwordConfirm);
        } catch (Exception e) {
            return false;
        }
    }
}