package controller;

import java.util.ArrayList;
import model.ItemDTO;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Item;
import model.ShoppingCartItem;

@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateless
public class ShopFacade {
    @PersistenceContext(unitName = "ProjectAPGPU")
    private EntityManager em;
    
    public List<ShoppingCartItem> getInventory() {
        return createInventory();
    }
    
    private List<ShoppingCartItem> createInventory() {
        Query allItems = em.createQuery("SELECT i FROM Item i");
        List<ItemDTO> items = (List<ItemDTO>) allItems.getResultList();
        List<ShoppingCartItem> itemsRepresentation = new ArrayList();
        for (ItemDTO item : items) {
            itemsRepresentation.add(new ShoppingCartItem(item.getName(), item.getQuantity(), item.getPrice()));
        }
        return itemsRepresentation;
    } 
    
    public void buyItem(String name, int quantity){
        Item item = em.find(Item.class, name);
        item.setQuantity(item.getQuantity() - quantity);
    }
}
