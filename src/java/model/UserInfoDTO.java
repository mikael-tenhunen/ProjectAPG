package model;

public interface UserInfoDTO {

    /**
     *
     * @return Name of user
     */
    public String getname();

    /**
     *
     * @return Whether user is admin
     */
    public boolean isAdmin();

    /**
     *
     * @return Whether user is banned
     */
    public boolean isBanned();
}
