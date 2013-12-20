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

@Stateful
public class ShoppingCart implements ShoppingCartFacade {
    @EJB
    private ShopFacade shopFacade;
    private List<ShoppingCartItem> shoppingCart;
    
    @PreDestroy
    public void preDestroy() {
        putBackItemsFromCart();
    }
    
    public void createShoppingCart() {
        this.shoppingCart = new LinkedList();
    }
    
    public List<ShoppingCartItem> getShoppingCart() {
        return shoppingCart;
    }
    
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
    
    public void checkout() {
        shoppingCart.clear();
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void putBackItemsFromCart() {
        for (ShoppingCartItem item : shoppingCart) {
            shopFacade.changeItemQuantity(item.getName(), item.getQuantity());
        }
    }
}
