package controller;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.UserInfo;
import model.UserInfoDTO;

/**
 *
 * @author Kalle
 */
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateless
public class LoginFacade {
    @PersistenceContext(unitName = "ProjectAPGPU")
    private EntityManager em;

    /**
     * Persists a new user in the database
     * @param username Name of the new customer
     * @param password Password of the new customer
     */
    public void registerUser(String username, String password) {
        UserInfo userInfo = new UserInfo(username, password);
        em.persist(userInfo);
    }
    
    /**
     * Checks the user has entered the correct password and if the user is banned
     * @param username username of the person trying to log in
     * @param password password the user entered when trying to log in
     * @return wether the login was allowed or not.
     */
    public boolean login(String username, String password) {
        UserInfoDTO userInfo = getUserInfoDTO(username);
        String correctPassword = getPassword(username);
        boolean banned = userInfo.isBanned();
        if (correctPassword.equals(password) && !banned) {
        return true;
        }
        else {
            return false;
        }
    }
    
    /**
     * Retreives the password of the user from the database
     * @param username name of the user
     * @return the corrent password that the user registered with
     * @throws EJBException 
     */
    private String getPassword(String username) throws EJBException{
        UserInfo userInfo = em.find(UserInfo.class, username);
        return userInfo.getPassword();
    }
    
    /**
     * returns the whole representation the specific user row in the dataaes
     * @param username name of the user
     * @return All the availible information about the user.
     */
    public UserInfoDTO getUserInfoDTO(String username) {
        return em.find(UserInfo.class, username);
    }
}
