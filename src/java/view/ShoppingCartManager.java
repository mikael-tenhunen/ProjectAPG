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

@Named("shoppingCartManager")
@SessionScoped
public class ShoppingCartManager implements Serializable {
    @EJB
    private ShoppingCartFacade shoppingCartFacade;
    
    @PostConstruct
    public void init() {
        shoppingCartFacade.createShoppingCart();
    }
    
    public List<ShoppingCartItem> getShoppingCart() {
        return shoppingCartFacade.getShoppingCart();
    }
    
    public void addToShoppingCart(ItemDTO item, int quantity) {
        if (quantity > item.getQuantity()) {
            //Cannot add this many items to cart
        }
        else {
            shoppingCartFacade.addItemToCart(item.getName(), quantity, item.getPrice());
        }
    }
    
    public void removeFromShoppingCart(ShoppingCartItem item, int quantity) {
        if (quantity > item.getQuantity()) {
            //Cannot remove this many items to cart
        }
        else {
            shoppingCartFacade.removeItemFromCart(item, quantity);
        }
    }
    
    public void putBackItemsFromCart() {
        shoppingCartFacade.putBackItemsFromCart();
    }
    
    public void checkout() {
        shoppingCartFacade.checkout();
    }
    
    public BigDecimal getTotal() {
        return shoppingCartFacade.getTotal();
    }
}
