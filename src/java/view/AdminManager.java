package view;

import controller.AdminFacade;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * Backing bean for the administrator view adminfront
 */
@Named("adminManager")
@SessionScoped
public class AdminManager implements Serializable {
    @EJB
    private AdminFacade adminFacade;
    private String newItemName;
    private int newItemQuantity;
    private BigDecimal newItemPrice;
    private String userToBan;
    
    /**
     * Called to change the "banned" status of a specific user
     * @param ban True if user is to be banned. False is user is
     * to be unbanned
     */
    public void changeBanned(boolean ban) {
        adminFacade.changeBanned(userToBan, ban);
    }
    
    /**
     * Called when admin wants to change the quantity of an already
     * existing product.
     * @param productName Name of the product to be altered
     * @param quantity Can be positive or negative, depending on whether you want to increase 
     * or decrease the quantity.
     */
    public void changeQuantity(String productName, int quantity) {
        adminFacade.changeQuantity(productName, quantity);
    }

    /**
     * Called when the admin wants a specific product removed from the database
     * completely.
     * @param productName Name of the product that is going to be removed
     */
    public void removeProduct(String productName) {
        adminFacade.removeProduct(productName);
    }
    
    /**
     * Called when the admin wants a specific product added to the database.
     */
    public void addProduct() {
        adminFacade.addProduct(newItemName, newItemQuantity, newItemPrice);
    }

    /**
     * @return returns the name of the product that the admin wants to add.
     */
    public String getNewItemName() {
        return newItemName;
    }

    /**
     * @param newItemName name of the new item
     */
    public void setNewItemName(String newItemName) {
        this.newItemName = newItemName;
    }

    /**
     * @return the quantity of the item that admin wants to add
     */
    public int getNewItemQuantity() {
        return newItemQuantity;
    }

    /**
     * @param newItemQuantity returns the new item's quantity
     */
    public void setNewItemQuantity(int newItemQuantity) {
        this.newItemQuantity = newItemQuantity;
    }

    /**
     * @return returns the price of the new item
     */
    public BigDecimal getNewItemPrice() {
        return newItemPrice;
    }

    /**
     * @param newItemPrice the price of the new item
     */
    public void setNewItemPrice(BigDecimal newItemPrice) {
        this.newItemPrice = newItemPrice;
    }

    /**
     * @return the name of the person that is to be banned/unbanned
     */
    public String getUserToBan() {
        return userToBan;
    }

    /**
     * @param userToBan name of the user that is going to be banned/unbanned
     */
    public void setUserToBan(String userToBan) {
        this.userToBan = userToBan;
    }
}
