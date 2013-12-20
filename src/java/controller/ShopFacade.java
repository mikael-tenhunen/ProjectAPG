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

@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateless
public class ShopFacade {
    @PersistenceContext(unitName = "ProjectAPGPU")
    private EntityManager em;
    
    public List<ItemDTO> getInventory() {
        Query allItems = em.createQuery("SELECT i FROM Item i");
        return allItems.getResultList();
    }

    public void changeItemQuantity(String name, int quantityChange){
        Item item = em.find(Item.class, name);
        item.setQuantity(item.getQuantity() + quantityChange);
    }
}
