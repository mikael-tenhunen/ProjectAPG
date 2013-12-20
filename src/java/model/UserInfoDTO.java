package model;

/**
 *
 * @author Kalle
 */
public interface UserInfoDTO {

    /**
     *
     * @return
     */
    public String getname();

    /**
     *
     * @return
     */
    public boolean isAdmin();

    /**
     *
     * @return
     */
    public boolean isBanned();
}
