package cn.servant.crm.pojo.vo;

import cn.servant.crm.pojo.Customer;
import cn.servant.crm.pojo.Page;

public class CustomerVo {

    private Customer customer;

    private Page page;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
