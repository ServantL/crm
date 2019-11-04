package cn.servant.crm.pojo.po;

import java.io.Serializable;
import java.util.Date;

public class Visit implements Serializable {

    private Integer visitId;

    private Date visitDate;

    private CustomerBasic visitCustomer;

    public Integer getVisitId() {
        return visitId;
    }

    public void setVisitId(Integer visitId) {
        this.visitId = visitId;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public CustomerBasic getVisitCustomer() {
        return visitCustomer;
    }

    public void setVisitCustomer(CustomerBasic visitCustomer) {
        this.visitCustomer = visitCustomer;
    }
}
