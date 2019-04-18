package com.techprimers.db.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "role")


public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Polje za tip uloge ne smije biti prazno")
    @Size(min=2, max=30, message = "Polje za tip uloge treba sadržavati najmanje 2, a najviše 30 karaktera")
    private String tip;

   // @OneToMany(targetEntity = Users.class, mappedBy = "role", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //private Set<Users> users;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

   /*public Set<Users> getUsers() {
        return users;
    }

    public void setUsers(Set<Users> users) {
        this.users = users;
    }*/
}
