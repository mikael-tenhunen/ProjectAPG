package view;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import model.UserInfoDTO;

/**
 *
 * @author Kalle
 */
@Named("userData")
@SessionScoped
public class UserData implements Serializable {
    private UserInfoDTO userInfo;

    /**
     *
     * @return The information about the customer in form of a UserIntoDTO
     */
    public UserInfoDTO getUserInfo() {
        return userInfo;
    }

    /**
     * @param userInfo Information about the user.
     */
    public void setUserInfo(UserInfoDTO userInfo) {
        this.userInfo = userInfo;
    }
}
