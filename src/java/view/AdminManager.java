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
    
    /**
     * is called when admin wants to change the "banned" status of a specific user
     * @param ban True if user is to be banned. False is user is supposed
     * to be unbanned
     */
    public void changeBanned(boolean ban) {
        adminFacade.changeBanned(userToBan, ban);
    }
    
    /**
     * Called when admin wants to increase or decrease the quantity of an already
     * existing product.
     * @param productName Name of the product to be altered
     * @param quantity Can be -1 or 1 depending on wether you want to increase 
     * or decrease the quantity.
     */
    public void changeQuantity(String productName, int quantity) {
        adminFacade.changeQuantity(productName, quantity);
    }

    /**
     *Called when the admin wants a specific product removed from the list
     * completely.
     * @param productName Name of the product that is going to be removed
     */
    public void removeProduct(String productName) {
        adminFacade.removeProduct(productName);
    }
    
    /**
     * Called when the admin wants a specific product added to the list of
     * availalbe purchases
     */
    public void addProduct() {
        adminFacade.addProduct(newItemName, newItemQuantity, newItemPrice);
    }

    /**
     *
     * @return returns the name of the product that the admin wants to add.
     */
    public String getNewItemName() {
        return newItemName;
    }

    /**
     *
     * @param newItemName name of the new item
     */
    public void setNewItemName(String newItemName) {
        this.newItemName = newItemName;
    }

    /**
     *
     * @return the quantity of the item that admin wants to add
     */
    public int getNewItemQuantity() {
        return newItemQuantity;
    }

    /**
     *
     * @param newItemQuantity returns the new item's quantity
     */
    public void setNewItemQuantity(int newItemQuantity) {
        this.newItemQuantity = newItemQuantity;
    }

    /**
     *
     * @return returns the price of the new item
     */
    public BigDecimal getNewItemPrice() {
        return newItemPrice;
    }

    /**
     *
     * @param newItemPrice sets the price of the new item
     */
    public void setNewItemPrice(BigDecimal newItemPrice) {
        this.newItemPrice = newItemPrice;
    }

    /**
     *
     * @return returns the name of the person that is to be banned/unbanned
     */
    public String getUserToBan() {
        return userToBan;
    }

    /**
     *
     * @param userToBan sets name of the user that is going to be banned/unbanned
     */
    public void setUserToBan(String userToBan) {
        this.userToBan = userToBan;
    }
}
