package productsDelivery.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "status")
public class  Status {
    private Long id;
    private String name;
    //private List<Product> products;

    public Status() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

//    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    public List<Product> getProducts() {
//        return products;
//    }
//
//    public void setProducts(List<Product> products) {
//        this.products = products;
//    }
}
