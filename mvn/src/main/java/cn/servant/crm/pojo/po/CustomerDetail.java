package cn.servant.crm.pojo.po;

import java.io.Serializable;
import java.math.BigInteger;

public class CustomerDetail implements Serializable {

    // 客户主键id
    private Integer custId;
    // 客户联系地址
    private String custAddress;
    // 客户地区
    private String custRegion;
    // 客户邮政编码
    private String custZip;
    // 客户网址
    private String custWebsite;
    // 客户营业执照注册号
    private String custLicence;
    // 企业法人
    private String custCorporation;
    // 客户注册资金
    private BigInteger custCapital;
    // 开启银行及账号
    private String custBank;
    // 客户资质图片
    private String custPic;
    // 客户简介
    private String custMemo;


    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public String getCustRegion() {
        return custRegion;
    }

    public void setCustRegion(String custRegion) {
        this.custRegion = custRegion;
    }

    public String getCustZip() {
        return custZip;
    }

    public void setCustZip(String custZip) {
        this.custZip = custZip;
    }

    public String getCustWebsite() {
        return custWebsite;
    }

    public void setCustWebsite(String custWebsite) {
        this.custWebsite = custWebsite;
    }

    public String getCustLicence() {
        return custLicence;
    }

    public void setCustLicence(String custLicence) {
        this.custLicence = custLicence;
    }

    public String getCustCorporation() {
        return custCorporation;
    }

    public void setCustCorporation(String custCorporation) {
        this.custCorporation = custCorporation;
    }

    public BigInteger getCustCapital() {
        return custCapital;
    }

    public void setCustCapital(BigInteger custCapital) {
        this.custCapital = custCapital;
    }

    public String getCustBank() {
        return custBank;
    }

    public void setCustBank(String custBank) {
        this.custBank = custBank;
    }

    public String getCustPic() {
        return custPic;
    }

    public void setCustPic(String custPic) {
        this.custPic = custPic;
    }

    public String getCustMemo() {
        return custMemo;
    }

    public void setCustMemo(String custMemo) {
        this.custMemo = custMemo;
    }
}
