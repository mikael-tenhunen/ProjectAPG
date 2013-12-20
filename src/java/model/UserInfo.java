package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Entity class representing the table UserInfo.
 */
@Entity
public class UserInfo implements Serializable, UserInfoDTO {
    private static final long serialVersionUID = 1L;
    @Id
    private String name;
    private String password;
    private boolean admin;
    private boolean banned;
    
    /**
     *
     */
    public UserInfo() {
    }
    
    /**
     * @param name
     * @param password
     */
    public UserInfo(String name, String password) {
        this.name = name;
        this.password = password;
        this.admin = false;
        this.banned = false;
    }
    
    /**
     * @param name
     * @param password
     * @param admin
     */
    public UserInfo(String name, String password, boolean admin) {
        this.name = name;
        this.password = password;
        this.admin = admin;
        this.banned = false;
    }

    /**
     * @return name of user
     */
    public String getname() {
        return name;
    }

    /**
     *
     * @param name name of user
     */
    public void setname(String name) {
        this.name = name;
    }

    /**
     * @return user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return
     */
    public boolean isAdmin() {
        return admin;
    }

    /**
     * @param admin admin status
     */
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    /**
     * @return banned status
     */
    public boolean isBanned() {
        return banned;
    }

    /**
     * @param banned banned status
     */
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
