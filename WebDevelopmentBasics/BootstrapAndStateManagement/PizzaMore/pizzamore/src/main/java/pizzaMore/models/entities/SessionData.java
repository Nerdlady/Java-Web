package pizzaMore.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "sessions_data")
public class SessionData {

    private Long id;

    private String key;

    private String value;

    private Session pizzaSession;

    public SessionData() {
    }

    public SessionData(String key, String value, Session session) {
        this.key = key;
        this.value = value;
        this.pizzaSession = session;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "key_data")
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Column(name = "value_data")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @ManyToOne()
    @JoinColumn(name = "session_id")
    public Session getPizzaSession() {
        return pizzaSession;
    }

    public void setPizzaSession(Session session) {
        this.pizzaSession = session;
    }
}
