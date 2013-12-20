package controller;

import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Local;
import model.ShoppingCartItem;

/**
 *
 * @author Kalle
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

    public void removeItemFromCart(ShoppingCartItem item, int quantity);

    public BigDecimal getTotal();

    public void checkout();

    public void preDestroy();

    public void putBackItemsFromCart();
}