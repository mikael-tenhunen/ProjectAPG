package model;

import java.math.BigDecimal;

/**
 * Representation of product in shopping cart. When an item is added to shopping 
 * cart, a ShoppingCartItem is instantiated.
 */
public class ShoppingCartItem {
    private String name;
    private int quantity;
    private BigDecimal price;
    
    /**
     * @param name
     * @param quantity
     * @param price
     */
    public ShoppingCartItem(String name, int quantity, BigDecimal price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    /**
     * @return name of item
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return number of this item in shopping cart
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return price of item
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * @param price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ShoppingCartItem)) {
            return false;
        }
        ShoppingCartItem other = (ShoppingCartItem) object;
        if ((this.name == null && other.getName() != null) || (this.name != null && !this.name.equals(other.getName()))) {
            return false;
        }
        return true;
    }
}
