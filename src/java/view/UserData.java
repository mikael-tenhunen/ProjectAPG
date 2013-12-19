package view;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import model.UserInfoDTO;

@Named("userData")
@SessionScoped
public class UserData implements Serializable {
    private UserInfoDTO userInfo;

    public UserInfoDTO getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoDTO userInfo) {
        this.userInfo = userInfo;
    }
}
