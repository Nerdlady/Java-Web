package dtos;

import enums.OrderType;
import enums.Status;

public class OrderDto {
    private Long id;
    private String name;
    private String number;
    private String address;
    private OrderType orderType;
    private KnifeDto product;
    private Status status;

    public OrderDto() {
    }

    public OrderDto(String buyerName, String buyerNumber, String buyerAddress,OrderType orderType,KnifeDto product) {
        this.setName(buyerName);
        this.setNumber(buyerNumber);
        this.setAddress(buyerAddress);
        this.setProduct(product);
        this.setOrderType(orderType);
    }

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public KnifeDto getProduct() {
        return product;
    }

    public void setProduct(KnifeDto product) {
        this.product = product;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
