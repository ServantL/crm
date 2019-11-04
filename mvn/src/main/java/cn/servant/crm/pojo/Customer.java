package cn.servant.crm.pojo;

import cn.servant.crm.pojo.po.CustomerBasic;
import cn.servant.crm.pojo.po.CustomerDetail;

import javax.validation.Valid;

public class Customer {

    // 客户的基本信息
    @Valid
    private CustomerBasic customerBasic;
    // 客户的详细信息
    @Valid
    private CustomerDetail customerDetail;

    public Customer(){}


    public Customer(CustomerBasic customerBasic, CustomerDetail customerDetail) {
        this.customerBasic = customerBasic;
        this.customerDetail = customerDetail;
    }

    public CustomerBasic getCustomerBasic() {
        return customerBasic;
    }

    public void setCustomerBasic(CustomerBasic customerBasic) {
        this.customerBasic = customerBasic;
    }

    public CustomerDetail getCustomerDetail() {
        return customerDetail;
    }

    public void setCustomerDetail(CustomerDetail customerDetail) {
        this.customerDetail = customerDetail;
    }

}
