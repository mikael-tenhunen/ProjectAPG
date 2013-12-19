package controller;

import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.inject.Named;
import model.ShoppingCartItem;

@Local
public interface ShoppingCartFacade {
    public void createShoppingCart();
    public List<ShoppingCartItem> getShoppingCart();
    public void addItemToCart(String name, int quantity, BigDecimal price);
    public void removeItemFromCart(ShoppingCartItem item, int quantity);
    public BigDecimal getTotal();
    public void putBackItemsFromCart();
    public void checkout();
}



//package controller;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//import javax.ejb.EJB;
//import javax.ejb.Stateful;
//import model.ShoppingCart;
//import model.ShoppingCartDTO;
//import model.ShoppingCartItem;
//
//
//@Stateful
//public class ShoppingCartFacade implements ShoppingCart{
//    @EJB 
//    private ShoppingCart shoppingCart;
//    
////    public ShoppingCartDTO getShoppingCart() {
////        return shoppingCart.getShoppingCartDTO();
////    }
//    
//    public void createShoppingCart() {
//        shoppingCart.createShoppingCart();
//    }
//    
////    private List<ShoppingCartItem> shoppingCart;
////    
////    public List<ShoppingCartItem> getShoppingCart() {
////        return shoppingCart;
////    }
////    
////    public void createShoppingCart() {
////        this.shoppingCart = new ArrayList();
////    }
////    
////    public void addItemToCart(String name, int quantity, BigDecimal price) {
////        ShoppingCartItem newItem = new ShoppingCartItem(name, quantity, price);
////        if (shoppingCart.contains(newItem)) {
////            //Increase quantity if the item already is in shoppingcart
////            int index = shoppingCart.indexOf(newItem);
////            ShoppingCartItem oldItem = shoppingCart.get(index);
////            oldItem.setQuantity(oldItem.getQuantity() + quantity);
////        }
////        else {
////            shoppingCart.add(newItem);
////        }
////    }
////    
////    public void removeItemFromCart(ShoppingCartItem item, int quantity) {
////        if (item.getQuantity() == quantity) {
////            shoppingCart.remove(item);
////        }
////        else {
////            item.setQuantity(item.getQuantity() - quantity);
////        }
////    }
////    
////    public BigDecimal getTotal() {
////        if (shoppingCart.isEmpty()) {
////            return BigDecimal.ZERO;
////        }
////        else {
////            BigDecimal total = new BigDecimal(0);
////            BigDecimal factor;
////            for (ShoppingCartItem item : shoppingCart) {
////                factor = new BigDecimal(item.getQuantity());
////                total = total.add(item.getPrice().multiply(factor));
////            }
////            return total;
////        }
////    }
//}
