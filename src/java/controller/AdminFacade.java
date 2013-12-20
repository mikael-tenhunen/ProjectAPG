package controller;

import java.math.BigDecimal;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Item;
import model.UserInfo;

/**
 * EJB that handles administrator operations.
 */
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateless
public class AdminFacade {
    @PersistenceContext(unitName = "ProjectAPGPU")
    private EntityManager em;
    
    /**
     * Changes the status of a user to banned or unbanned
     * @param username name of the person to be banned7unbanned
     * @param ban true if user is to be banned, false if user is to be unbanned
     * @return retunr sthe current ban status of the user
     */
    public boolean changeBanned(String username, boolean ban) {
        UserInfo user = em.find(UserInfo.class, username);
        user.setBanned(ban);
        return user.isBanned();
    }
    
    /**
     *Changes the quantity of a certain item in the shop.
     * @param productName Name of the product to be altered
     * @param quantity number to be added to the current quantity
     */
    public void changeQuantity(String productName, int quantity) {
        Item item = em.find(Item.class, productName);
        item.setQuantity(item.getQuantity() + quantity);
    }
    
    /**
     * Removes a product from the database
     * @param productName Name of the product to be removed
     */
    public void removeProduct(String productName) {
        Item item = em.find(Item.class, productName);
        em.remove(item);
    }
    
    /**
     * Creates a new item object and persists it in the database.
     * @param productName Name of the new product
     * @param quantity Quantity of the new product
     * @param price Price of the new procudt.
     */
    public void addProduct(String productName, int quantity, BigDecimal price) {
        Item item = new Item(productName, quantity, price);
        em.persist(item);
    }
}
