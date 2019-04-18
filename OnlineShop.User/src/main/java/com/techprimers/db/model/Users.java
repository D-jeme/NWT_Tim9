package com.techprimers.db.model;

import org.hibernate.validator.constraints.Email;
import org.springframework.data.annotation.Transient;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "user")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull (message = "Polje za ime ne smije biti prazno")
    @Size(min=2, max=30, message = "Polje za ime treba sadržavati najmanje 2, a najviše 30 karaktera")
    private String ime;
    @NotNull (message = "Polje za prezime treba biti popunjeno")
    @Size(min=2, max=50, message = "Polje za prezime treba sadržavati najmanje 2, a najviše 50 karaktera")
    private String prezime;
    @NotNull (message = "Polje za email treba biti popunjeno")
    @Email (message = "Email treba biti u validnom formatu")
    private String email;
    private String password;

    private String newPassword;


    @ManyToOne
    @JoinColumn(name = "role_id")
    private Roles role;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {

      /*  if (salt) {
            this.password = BCrypt.hashpw(password, BCrypt.gensalt());
        } else {
            this.password = password;
        }*/
        BCryptPasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
        String newpassword=passwordEncoder.encode(password);
        System.out.println("Ovaj PASSWORD MI JE IZ SETTERA"+ newpassword);

        this.password = newpassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }
   /* public Set<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }*/

}