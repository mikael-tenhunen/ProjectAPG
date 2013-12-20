package view;

import controller.ShoppingCartFacade;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.ShoppingCartItem;

@Named("shoppingCartManager")
@SessionScoped
public class ShoppingCartManager implements Serializable {
    @EJB
    private ShoppingCartFacade shoppingCartFacade;
    @Inject
    private ShopManager shopManager;
//    @EJB
//    private ShoppingCartDTO shoppingCart;
    
    @PostConstruct
    public void init() {
        shoppingCartFacade.createShoppingCart();
//        shoppingCart = shoppingCartFacade.getShoppingCart();
    }
    
    public List<ShoppingCartItem> getShoppingCart() {
        return shoppingCartFacade.getShoppingCart();
    }
    
    public void addToShoppingCart(ShoppingCartItem item, int quantity) {
        if (quantity > item.getQuantity()) {
            //Cannot add this many items to cart
        }
        else {
            shoppingCartFacade.addItemToCart(item.getName(), quantity, item.getPrice());
            shopManager.removeItemFromInventory(item, quantity);
        }
    }
    
    public void removeFromShoppingCart(ShoppingCartItem item, int quantity) {
        if (quantity > item.getQuantity()) {
            //Cannot remove this many items to cart
        }
        else {
            shoppingCartFacade.removeItemFromCart(item, quantity);
            shopManager.addItemToInventory(item, quantity);
        }
    }
    
    public void checkout() {
        shoppingCartFacade.checkout();
    }
    
    public BigDecimal getTotal() {
        return shoppingCartFacade.getTotal();
    }
}
