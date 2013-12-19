package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class UserInfo implements Serializable, UserInfoDTO {
    private static final long serialVersionUID = 1L;
    @Id
    private String name;
    private String password;
    private boolean admin;
    private boolean banned;
    
    public UserInfo() {
    }
    
    public UserInfo(String name, String password) {
        this.name = name;
        this.password = password;
        this.admin = false;
        this.banned = false;
    }
    
    public UserInfo(String name, String password, boolean admin) {
        this.name = name;
        this.password = password;
        this.admin = admin;
        this.banned = false;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (name != null ? name.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserInfo)) {
            return false;
        }
        UserInfo other = (UserInfo) object;
        if ((this.name == null && other.name != null) || (this.name != null && !this.name.equals(other.name))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.UserInfo[ name=" + name + " ]";
    }
    
}
