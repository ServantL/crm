package cn.servant.crm.pojo.po;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class User implements Serializable {

    private Integer userId;

    @NotEmpty(message="{user.account.isEmpty}")
    private String userAccount;

    @NotEmpty(message="{user.password.isEmpty}")
    private String userPassword;

    private Integer userRights;

    private Linkman userLinkman;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Integer getUserRights() {
        return userRights;
    }

    public void setUserRights(Integer userRights) {
        this.userRights = userRights;
    }

    public Linkman getUserLinkman() {
        return userLinkman;
    }

    public void setUserLinkman(Linkman userLinkman) {
        this.userLinkman = userLinkman;
    }
}
