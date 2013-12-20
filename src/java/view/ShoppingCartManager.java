package view;

import controller.ShoppingCartFacade;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import model.ItemDTO;
import model.ShoppingCartItem;

/**
 *
 * @author Kalle
 */
@Named("shoppingCartManager")
@SessionScoped
public class ShoppingCartManager implements Serializable {
    @EJB
    private ShoppingCartFacade shoppingCartFacade;
    
    /**
     *Run at bean construction. Initializes shopping cart
     */
    @PostConstruct
    public void init() {
        shoppingCartFacade.createShoppingCart();
    }
    
    /**
     * calls the controller so it can reveive the current status of the shopping
     * cart
     * @return the list of items in the shopping cart
     */
    public List<ShoppingCartItem> getShoppingCart() {
        return shoppingCartFacade.getShoppingCart();
    }
    
    /**
     * Called when a user wants to add something to their shopping cart.
     * @param item the item object the user wants to buy
     * @param quantity the ammount of this specific item the user wants to buy
     */
    public void addToShoppingCart(ItemDTO item, int quantity) {
        if (quantity > item.getQuantity()) {
            //Cannot add this many items to cart
        }
        else {
            shoppingCartFacade.addItemToCart(item.getName(), quantity, item.getPrice());
        }
    }
    
    /**
     *Called when a user wants to remove an item from the shopping cart
     * @param item item that is to be removed
     * @param quantity number of that specific item to be removed
     */
    public void removeFromShoppingCart(ShoppingCartItem item, int quantity) {
        if (quantity > item.getQuantity()) {
            //Cannot remove this many items to cart
        }
        else {
            shoppingCartFacade.removeItemFromCart(item, quantity);
        }
    }
    
    /**
     * called when a user logs out so all the items in his/her shopping cart 
     * are returned to the database.
     */
    public void putBackItemsFromCart() {
        shoppingCartFacade.putBackItemsFromCart();
    }
    
    /**
     * called when a user decides to buy all the items currently in his/her 
     * shopping cart.
     */
    public void checkout() {
        shoppingCartFacade.checkout();
    }
    
    /**
     * 
     * @return total cost of all the items in the shopping cart combined
     */
    public BigDecimal getTotal() {
        return shoppingCartFacade.getTotal();
    }
}
