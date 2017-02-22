package dtos;

import enums.OrderType;

public class OrderDto {
    private String buyerName;
    private String buyerNumber;
    private String buyerAddress;
    private OrderType orderType;
    private KnifeDto product;

    public OrderDto() {
    }

    public OrderDto(String buyerName, String buyerNumber, String buyerAddress,OrderType orderType,KnifeDto product) {
        this.setBuyerName(buyerName);
        this.setBuyerNumber(buyerNumber);
        this.setBuyerAddress(buyerAddress);
        this.setProduct(product);
        this.setOrderType(orderType);
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerNumber() {
        return buyerNumber;
    }

    public void setBuyerNumber(String buyerNumber) {
        this.buyerNumber = buyerNumber;
    }

    public String getBuyerAddress() {
        return buyerAddress;
    }

    public void setBuyerAddress(String buyerAddress) {
        this.buyerAddress = buyerAddress;
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
}
