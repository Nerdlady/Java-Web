package com.entities;


import com.entities.enums.Role;

import javax.persistence.*;
import java.util.Set;

@Entity()
@Table(name = "users")
public class User {
    private Long id;
    private String email;
    private String fullName;
    private String password;
    private Role role;
    private Set<Game> boughtGames;
    private Set<Game> cartGames;

    public User() {
    }

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Enumerated(EnumType.STRING)
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @ManyToMany(mappedBy = "usersBought")
    public Set<Game> getBoughtGames() {
        return boughtGames;
    }

    public void setBoughtGames(Set<Game> games) {
        this.boughtGames = games;
    }

    @ManyToMany(mappedBy = "usersCart")
    public Set<Game> getCartGames() {
        return cartGames;
    }

    public void setCartGames(Set<Game> cartGames) {
        this.cartGames = cartGames;
    }
}
