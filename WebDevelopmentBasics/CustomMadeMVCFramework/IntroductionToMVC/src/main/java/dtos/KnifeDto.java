package dtos;

import java.math.BigDecimal;

public class KnifeDto {
    private Long id;
    private String name;
    private BigDecimal price;
    private String imageURL;

    public KnifeDto() {
    }

    public KnifeDto(String name, BigDecimal price, String imageURL) {
        this.name = name;
        this.price = price;
        this.imageURL = imageURL;
    }

    public KnifeDto(Long id, String name, BigDecimal price, String imageURL) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageURL = imageURL;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
