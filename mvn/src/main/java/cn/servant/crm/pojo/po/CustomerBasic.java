package cn.servant.crm.pojo.po;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Map;

public class CustomerBasic implements Serializable {

    // 客户主键id
    private Integer custId;
    // 客户名字
    @Length(min = 1, max = 10, message = "{customer.name.lengthError}")
    private String custName;
    // 客户性别
    private String custSex;
    // 客户级别
//    @NotEmpty(message = "{customer.level.isEmpty}")
    private Map<String, Object> custLevel;
    // 客户的联系人
    private Linkman custLinkman;
    // 客户来源
    private Map<String, Object> custSource;
//    private String custSource;
    // 客户行业
//    private String custIndustry;
    private Map<String, Object> custIndustry;
    // 客户手机
    @Pattern(regexp = "^[1][3,4,5,7,8][0-9]{9}$",message = "{customer.phone.formatError}")
    private String custPhone;
    // 客户的总访问次数
    private Integer custVisitTime;

    // 测试
//    private Map<String, Object> testSource;

//    public Map<String, Object> getTestSource() {
//        return testSource;
//    }
//
//    public void setTestSource(Map<String, Object> testSource) {
////        if (testSource.size() > 1) {
////            testSource = DictResultUtil.getBaseMap(testSource);
////        }
//        this.testSource = testSource;
//    }

    public CustomerBasic(){}

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustSex() {
        return custSex;
    }

    public void setCustSex(String custSex) {
        this.custSex = custSex;
    }

    public Map<String, Object> getCustLevel() {
        return custLevel;
    }

    public void setCustLevel(Map<String, Object> custLevel) {
        this.custLevel = custLevel;
    }

    public Linkman getCustLinkman() {
        return custLinkman;
    }

    public void setCustLinkman(Linkman custLinkman) {
        this.custLinkman = custLinkman;
    }

    public Map<String, Object> getCustSource() {
        return custSource;
    }

    public void setCustSource(Map<String, Object> custSource) {
        this.custSource = custSource;
    }

    public Map<String, Object> getCustIndustry() {
        return custIndustry;
    }

    public void setCustIndustry(Map<String, Object> custIndustry) {
        this.custIndustry = custIndustry;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    public Integer getCustVisitTime() {
        return custVisitTime;
    }

    public void setCustVisitTime(Integer custVisitTime) {
        this.custVisitTime = custVisitTime;
    }

}