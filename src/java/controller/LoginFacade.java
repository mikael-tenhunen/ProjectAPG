package controller;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.UserInfo;
import model.UserInfoDTO;

@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateless
public class LoginFacade {
    @PersistenceContext(unitName = "ProjectAPGPU")
    private EntityManager em;

    public void registerUser(String username, String password) {
        UserInfo userInfo = new UserInfo(username, password);
        em.persist(userInfo);
    }
    
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
    
    private String getPassword(String username) throws EJBException{
        UserInfo userInfo = em.find(UserInfo.class, username);
        return userInfo.getPassword();
    }
    
    public UserInfoDTO getUserInfoDTO(String username) {
        return em.find(UserInfo.class, username);
    }
}
