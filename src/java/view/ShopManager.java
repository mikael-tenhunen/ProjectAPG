
package view;

import controller.ShopFacade;
import controller.ShoppingCartFacade;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import model.ItemDTO;

/**
 *
 * @author Kalle
 */
@Named("shopManager")
@SessionScoped
public class ShopManager implements Serializable {
    @EJB
    private ShopFacade shopFacade;
//    @EJB
//    private ShoppingCartFacade shoppingCartFacade;
    @Inject
    private UserData userData;
    @Inject
    private ShoppingCartManager shoppingCartManager;
    
    /**
     * This method is called when  user wants to log out from the shop GUI
     */
    public void logout() {
        shoppingCartManager.putBackItemsFromCart();
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("../login.xhtml");
        } catch (IOException ex) {
            FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage("Problem redirecting when logging out"));;
        }
    }

    /**
     *
     * @return returns a data access object (DAO) that is an interface exposing 
     * some entity getters and setters for information about the user.
     */
    public UserData getUserData() {
        return userData;
    }

    /**
     *
     * @return returns a data access object (DAO) that is an interface exposing
     * the current items availible for sale.
     */
    public List<ItemDTO> getInventory() {
        return shopFacade.getInventory();
    }
}
