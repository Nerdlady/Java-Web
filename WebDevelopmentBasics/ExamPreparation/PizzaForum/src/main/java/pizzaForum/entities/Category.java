package pizzaForum.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Scanner;

@Entity
@Table(name = "categories")
public class Category {
    private Long id;
    private String name;
    private List<Topic> topics;

    public Category() {
    }

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

    @OneToMany(mappedBy = "category")
    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }
}
