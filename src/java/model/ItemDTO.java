package model;

import java.math.BigDecimal;

/**
 * An interface for the entity class Item. Exposes only the methods that should 
 * be accessible from the view layer.
 */
public interface ItemDTO {
    public String getName();
    public int getQuantity();
    public BigDecimal getPrice();
}
