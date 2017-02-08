package pizzaMore.models.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pizza_sessions")
public class Session {
    private Long id;

    private Set<SessionData> sessionData;

    public Session() {
        this.setSessionData(new HashSet<>());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToMany(mappedBy = "pizzaSession", cascade = CascadeType.ALL, fetch = FetchType.EAGER,orphanRemoval = true)
    public Set<SessionData> getSessionData() {
        return sessionData;
    }

    public void setSessionData(Set<SessionData> sessionDatas) {
        this.sessionData = sessionDatas;
    }

    public void addSessionData(String key,String value){
        this.getSessionData().add(new SessionData(key,value,this));
    }
}
