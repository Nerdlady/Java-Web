package com.gameOfNerds.areas.clan.entities;

import com.gameOfNerds.areas.user.entities.User;
import com.gameOfNerds.areas.user.entities.UserGameInfo;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "clans")
public class Clan {
    private Long id;
    private String name;
    private User owner;
    private Double totalScore;
    private Set<UserGameInfo> users;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToOne()
    @JoinColumn(name = "owner_id",referencedColumnName = "id")
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Double totalScore) {
        this.totalScore = totalScore;
    }

    @OneToMany(mappedBy = "clan")
    public Set<UserGameInfo> getUsers() {
        return users;
    }

    public void setUsers(Set<UserGameInfo> users) {
        this.users = users;
    }
}
