package controller;

import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Local;
import model.ShoppingCartItem;

/**
 * Interface that exposes methods of the shopping cart to the controller layer.
 */
@Local
public interface ShoppingCartFacade {

    public void createShoppingCart();
    public List<ShoppingCartItem> getShoppingCart();

    /**
     * @param name Price of the item to be added
     * @param quantity Number of that item to be added
     * @param price Prce of the item.
     */
    public void addItemToCart(String name, int quantity, BigDecimal price);

    /**
     * @param item object to remove from shopping cart
     * @param quantity 
     */
    public void removeItemFromCart(ShoppingCartItem item, int quantity);

    /**
     * @return total cost of items in shopping cart
     */
    public BigDecimal getTotal();

    /**
     * Items in shopping cart are removed so they can be shipped.
     */
    public void checkout();

    /**
     * Should be done before shopping cart bean is destroyed: put all items back 
     * in database.
     */
    public void preDestroy();

    /**
     * Put all items in cart back in database.
     */
    public void putBackItemsFromCart();
}