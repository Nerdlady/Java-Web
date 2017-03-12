package com.models.bindingModels.game;

import com.constants.Constants;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

public class NewGame {
    @NotNull(message = Constants.GAME_TITLE_NOT_NULL)
    @Size(min = 3, max = 100, message = Constants.INVALID_GAME_TITLE)
    @Pattern(regexp = "[A-Z].*", message = Constants.INVALID_GAME_TITLE)
    private String title;

    @Size(min = 11, max = 11,message = Constants.INVALID_TRAILER_LENGTH)
    private String trailer;

    @Pattern(regexp = "[(http:\\/\\/)(https:\\/\\/)].*", message = Constants.INVALID_THUMBNAIL)
    private String imageURL;

    @NotNull(message = Constants.SIZE_NOT_NULL)
    @Min(value = 0, message = Constants.INVALID_SIZE)
    private Double size;

    @NotNull(message = Constants.PRICE_NOT_NULL)
    @Min(value = 0, message = Constants.INVALID_PRICE)
    private BigDecimal price;

    @Size(min = 20, message = Constants.INVALID_DESCRIPTION)
    private String description;

    private Date releaseDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
}
