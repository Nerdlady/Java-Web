package com.gameOfNerds.areas.user.entities;

import com.gameOfNerds.areas.clan.entities.Clan;

import javax.persistence.*;

@Entity
@Table(name = "user_game_info")
public class UserGameInfo {
    private Long id;
    private User user;
    private Clan clan;
    private Double score;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "clan_id",referencedColumnName = "id")
    public Clan getClan() {
        return clan;
    }

    public void setClan(Clan clan) {
        this.clan = clan;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
