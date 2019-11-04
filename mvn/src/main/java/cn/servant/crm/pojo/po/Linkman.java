package cn.servant.crm.pojo.po;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(value = "linkCustomerList")
public class Linkman implements Serializable {

    // 联系人主键id
    private Integer linkId;
    // 联系人账号
//    private String linkUserId;
    // 联系人密码
//    private String linkUserPwd;
    // 联系人姓名
    private String linkName;
    // 联系人姓名
    private String linkSex;
    // 联系人手机
    private String linkPhone;
    // 联系人邮箱
    private String linkEmail;
    // 联系人职位
    private String linkPosition;
    // 联系人描述
    private String linkMemo;

    // 联系人所管理的客户
    private List<CustomerBasic> linkCustomerList;


    public Integer getLinkId() {
        return linkId;
    }

    public void setLinkId(Integer linkId) {
        this.linkId = linkId;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public String getLinkSex() {
        return linkSex;
    }

    public void setLinkSex(String linkSex) {
        this.linkSex = linkSex;
    }

    public String getLinkPhone() {
        return linkPhone;
    }

    public void setLinkPhone(String linkPhone) {
        this.linkPhone = linkPhone;
    }

    public String getLinkEmail() {
        return linkEmail;
    }

    public void setLinkEmail(String linkEmail) {
        this.linkEmail = linkEmail;
    }

    public String getLinkPosition() {
        return linkPosition;
    }

    public void setLinkPosition(String linkPosition) {
        this.linkPosition = linkPosition;
    }

    public String getLinkMemo() {
        return linkMemo;
    }

    public void setLinkMemo(String linkMemo) {
        this.linkMemo = linkMemo;
    }

    public List<CustomerBasic> getLinkCustomerList() {
        return linkCustomerList;
    }

    public void setLinkCustomerList(List<CustomerBasic> linkCustomerList) {
        this.linkCustomerList = linkCustomerList;
    }
}
