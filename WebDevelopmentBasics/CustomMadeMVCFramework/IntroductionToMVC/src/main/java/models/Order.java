package models;

import enums.OrderType;
import enums.Status;

import javax.persistence.*;

@Entity(name = "orders")
public class Order {
    private Long id;
    private String name;
    private String number;
    private String address;
    private OrderType orderType;
    private Status status;
    private Knife product;

    public Order() {
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

    public void setName(String buyerName) {
        this.name = buyerName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String buyerNumber) {
        this.number = buyerNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String buyerAddress) {
        this.address = buyerAddress;
    }

    @ManyToOne
    @JoinColumn(name = "knife_id",referencedColumnName = "id")
    public Knife getProduct() {
        return product;
    }

    public void setProduct(Knife product) {
        this.product = product;
    }

    @Enumerated(EnumType.STRING)
    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    @Enumerated(EnumType.STRING)
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
