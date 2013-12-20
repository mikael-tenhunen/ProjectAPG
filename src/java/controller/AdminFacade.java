package controller;

import java.math.BigDecimal;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Item;
import model.UserInfo;

@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateless
public class AdminFacade {
    @PersistenceContext(unitName = "ProjectAPGPU")
    private EntityManager em;
    
    public boolean changeBanned(String username, boolean ban) {
        UserInfo user = em.find(UserInfo.class, username);
        user.setBanned(ban);
        return user.isBanned();
    }
    
    public void changeQuantity(String productName, int quantity) {
        Item item = em.find(Item.class, productName);
        item.setQuantity(item.getQuantity() + quantity);
    }
    
    public void removeProduct(String productName) {
//        Query deleteProduct = em.createQuery("DELETE i FROM item where i.name = '" 
//                + productName + "'");
        Item item = em.find(Item.class, productName);
        em.remove(item);
    }
    
    public void addProduct(String productName, int quantity, BigDecimal price) {
        Item item = new Item(productName, quantity, price);
        em.persist(item);
    }
}
