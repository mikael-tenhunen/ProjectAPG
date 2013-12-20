package model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Kalle
 */
@Entity
public class Item implements Serializable, ItemDTO {
    private static final long serialVersionUID = 1L;
    @Id
    private String name;
    private int quantity;
    private BigDecimal price;
    
    /**
     * Empty constructor for the Item object
     */
    public Item() {
    }

    /**
     * Object representing our items that are for sale in the store
     * @param productName Name of the product
     * @param quantity Quantity availible for sale
     * @param price Price of the item per unit
     */
    public Item(String productName, int quantity, BigDecimal price) {
        this.name = productName;
        this.quantity = quantity;
        this.price = price;
    }

    /**
     *
     * @return The name of the product
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name The name of the product
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return The quantity of the item
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     *
     * @param quantity Quantity of the product
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     *Getter for the price of the product
     * @return The price of the product
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     *Setter for the price of the product
     * @param price Price of the product
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (name != null ? name.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Item)) {
            return false;
        }
        Item other = (Item) object;
        if ((this.name == null && other.name != null) || (this.name != null && !this.name.equals(other.name))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Item[ name=" + name + " ]";
    }
    
}
