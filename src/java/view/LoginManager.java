package view;

import controller.LoginFacade;
import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Kalle
 */
@Named("loginManager")
@RequestScoped
public class LoginManager implements Serializable {
    @Inject
    private UserData userData;
    @EJB
    private LoginFacade loginFacade;
    private String username;
    private String password;
    private String validatePassword;
        
    /**
     * Called from the html interface when a new customer wants to register.
     */
    public void register() {
        if (password.equals(validatePassword)) {
            try {
            loginFacade.registerUser(username, password);
            login();
            } catch (EJBException ex) {
                FacesContext.getCurrentInstance().addMessage("registerform:username", 
                        new FacesMessage("Username not allowed or already taken"));
            }
        }
        else {
            FacesContext.getCurrentInstance().addMessage("registerform:validatepassword", 
                    new FacesMessage("Passwords don't match"));
        }
    }
    
    /**
     * called when a user wants to log in or when a succesful registration has
     * occurred. also redirects the user to the correct page if login is successful.
     */
    public void login() {
        try {
            boolean login = loginFacade.login(username, password);
            if (login) {
                //Pass a DTO representing the user post in database to UserData backing bean
                userData.setUserInfo(loginFacade.getUserInfoDTO(username));
                boolean admin = userData.getUserInfo().isAdmin();
                try {
                    if (admin) {
                        FacesContext.getCurrentInstance().getExternalContext().
                                redirect("secured/adminfront.xhtml");                        
                    }
                    else {
                        FacesContext.getCurrentInstance().getExternalContext().
                                redirect("secured/shopfront.xhtml");
                    }
                } catch (IOException ex) {
                    System.out.println("Problem redirecting at succesful login");
                }
            }
            else {
                FacesContext.getCurrentInstance().addMessage("loginform:username", 
                        new FacesMessage("Access denied"));
            }
        } catch (EJBException ex) {
            FacesContext.getCurrentInstance().addMessage("loginform:username", 
                    new FacesMessage("Login failed: no such username"));
        }
    }
    
    /**
     *
     * @return username of the current user
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username username of the current user
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return The password of the current user
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password set password of the current user
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return The password validation 
     */
    public String getValidatePassword() {
        return validatePassword;
    }

    /**
     *
     * @param validatePassword sets the valitation password for comparison when
     * trying to register
     */
    public void setValidatePassword(String validatePassword) {
        this.validatePassword = validatePassword;
    }
       
}
