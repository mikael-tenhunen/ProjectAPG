
package view;

import controller.ShopFacade;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import model.ItemDTO;

/**
 * Backing bean for shop front. Exposes inventory with the method getInventory 
 */
@Named("shopManager")
@SessionScoped
public class ShopManager implements Serializable {
    @EJB
    private ShopFacade shopFacade;
    @Inject
    private UserData userData;
    @Inject
    private ShoppingCartManager shoppingCartManager;
    
    /**
     * This method is called when  user wants to log out from the shop
     */
    public void logout() {
        //Put all items in shopping cart back in database
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
     * @return reference to a backing bean containing data access object to the
     * entity with user information
     */
    public UserData getUserData() {
        return userData;
    }

    /**
     *
     * @return a list of data access objects (interfaces exposing items in database).
     */
    public List<ItemDTO> getInventory() {
        return shopFacade.getInventory();
    }
}
