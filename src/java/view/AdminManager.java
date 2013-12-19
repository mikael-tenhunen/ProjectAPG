package view;

import controller.AdminFacade;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named("adminManager")
@SessionScoped
public class AdminManager implements Serializable {
    @EJB
    private AdminFacade adminFacade;
    private String newItemName;
    private int newItemQuantity;
    private BigDecimal newItemPrice;
    private String userToBan;
    
    public void changeBanned(boolean ban) {
        adminFacade.changeBanned(userToBan, ban);
    }
    
    public void changeQuantity(String productName, int quantity) {
        adminFacade.changeQuantity(productName, quantity);
    } 
    
    public void removeProduct(String productName) {
        adminFacade.removeProduct(productName);
    }
    
    public void addProduct() {
        adminFacade.addProduct(newItemName, newItemQuantity, newItemPrice);
    }

    public String getNewItemName() {
        return newItemName;
    }

    public void setNewItemName(String newItemName) {
        this.newItemName = newItemName;
    }

    public int getNewItemQuantity() {
        return newItemQuantity;
    }

    public void setNewItemQuantity(int newItemQuantity) {
        this.newItemQuantity = newItemQuantity;
    }

    public BigDecimal getNewItemPrice() {
        return newItemPrice;
    }

    public void setNewItemPrice(BigDecimal newItemPrice) {
        this.newItemPrice = newItemPrice;
    }

    public String getUserToBan() {
        return userToBan;
    }

    public void setUserToBan(String userToBan) {
        this.userToBan = userToBan;
    }
}
