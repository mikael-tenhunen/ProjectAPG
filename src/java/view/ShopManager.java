
package view;

import controller.ShopFacade;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import model.ShoppingCartItem;

@Named("shopManager")
@SessionScoped
public class ShopManager implements Serializable {
    @EJB
    private ShopFacade shopFacade;
    @Inject
    private UserData userData;
    private List<ShoppingCartItem> inventory;
    
    @PostConstruct
    public void init() {
        updateInventory();
    }
    
    public void logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("../login.xhtml");
        } catch (IOException ex) {
            System.out.println("error redirecting");
        }
    }

    public UserData getUserData() {
        return userData;
    }
    
    public void updateInventory() {
        inventory = shopFacade.getInventory();
    }
    
    public void removeItemFromInventory(ShoppingCartItem item, int quantity) {
        int index = inventory.indexOf(item);
        ShoppingCartItem itemToRemove = inventory.get(index);
        itemToRemove.setQuantity(itemToRemove.getQuantity() - quantity);
        if (itemToRemove.getQuantity() <= 0) {
            inventory.remove(index);
        }
    }
    
    public void addItemToInventory(ShoppingCartItem item, int quantity) {
        if (inventory.contains(item)) {
            int index = inventory.indexOf(item);
            ShoppingCartItem itemToIncrease = inventory.get(index);
            itemToIncrease.setQuantity(itemToIncrease.getQuantity() + quantity);
        }
        else {
            inventory.add(new ShoppingCartItem(item.getName(), quantity, item.getPrice()));
        }
    }

    public List<ShoppingCartItem> getInventory() {
        return inventory;
    }
}
