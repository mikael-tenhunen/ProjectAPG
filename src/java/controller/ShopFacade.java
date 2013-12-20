package controller;

import model.ItemDTO;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Item;

/**
 *
 * @author Kalle
 */
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateless
public class ShopFacade {
    @PersistenceContext(unitName = "ProjectAPGPU")
    private EntityManager em;
    
    /**
     * Retreives all the items that are for sale
     * @return List of all the items available for sale
     */
    public List<ItemDTO> getInventory() {
        Query allItems = em.createQuery("SELECT i FROM Item i");
        return allItems.getResultList();
    }

    /**
     * Changes the availible quantity of an item
     * @param name Name of the item
     * @param quantityChange How much the quantity is to be increased or decreased
     * by.
     */
    public void changeItemQuantity(String name, int quantityChange){
        Item item = em.find(Item.class, name);
        item.setQuantity(item.getQuantity() + quantityChange);
    }
}
