package model;

import controller.ShopFacade;
import controller.ShoppingCartFacade;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 * EJB representing the shopping cart. 
 */
@Stateful
public class ShoppingCart implements ShoppingCartFacade {
    @EJB
    private ShopFacade shopFacade;
    private List<ShoppingCartItem> shoppingCart;
    
    /**
     * Before destroying this bean, all the items in the shopping cart should 
     * be returned to database.
     */
    @PreDestroy
    public void preDestroy() {
        putBackItemsFromCart();
    }
    
    /**
     * Initialize shopping cart
     */
    public void createShoppingCart() {
        this.shoppingCart = new LinkedList();
    }
    
    /**
     * @return a list of ShoppingCartItem objects
     */
    public List<ShoppingCartItem> getShoppingCart() {
        return shoppingCart;
    }
    
    /**
     * Adds an item to the cart and makes a call to remove it from database.
     * @param name
     * @param quantity
     * @param price 
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addItemToCart(String name, int quantity, BigDecimal price) {
        ShoppingCartItem newItem = new ShoppingCartItem(name, quantity, price);
        if (shoppingCart.contains(newItem)) {
            //Increase quantity if the item already is in shoppingcart
            int index = shoppingCart.indexOf(newItem);
            ShoppingCartItem oldItem = shoppingCart.get(index);
            oldItem.setQuantity(oldItem.getQuantity() + quantity);
        }
        else {
            shoppingCart.add(newItem);
        }
        shopFacade.changeItemQuantity(name, -quantity);
    }
    
    /**
     * Removes and item from the cart and makes a call to put it back in the database.
     * @param item
     * @param quantity
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void removeItemFromCart(ShoppingCartItem item, int quantity) {
        if (item.getQuantity() == quantity) {
            shoppingCart.remove(item);
        }
        else {
            item.setQuantity(item.getQuantity() - quantity);
        }
        shopFacade.changeItemQuantity(item.getName(), quantity);
    }
    
    /**
     * @return Total cost of items in shopping cart
     */
    public BigDecimal getTotal() {
        if (shoppingCart.isEmpty()) {
            return BigDecimal.ZERO;
        }
        else {
            BigDecimal total = new BigDecimal(0);
            BigDecimal factor;
            for (ShoppingCartItem item : shoppingCart) {
                factor = new BigDecimal(item.getQuantity());
                total = total.add(item.getPrice().multiply(factor));
            }
            return total;
        }
    }

    /**
     * A dummy method that simply empties the shopping cart.
     */
    public void checkout() {
        shoppingCart.clear();
    }
    
    /**
     * Puts back everything in shopping cart to database.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void putBackItemsFromCart() {
        for (ShoppingCartItem item : shoppingCart) {
            shopFacade.changeItemQuantity(item.getName(), item.getQuantity());
        }
    }
}
