package cn.servant.crm.service;

import cn.servant.crm.pojo.Customer;
import cn.servant.crm.pojo.po.CustomerBasic;
import cn.servant.crm.pojo.vo.CustomerVo;

import java.util.List;
import java.util.Map;

public interface CustomerService {

    public void insertCustomer(Customer customer) throws Exception;

    public Customer selectCustomerById(int id) throws Exception;

    public void deleteCustomerById(int id) throws Exception;

    public int getCustomerTotalCount(CustomerVo customerVo) throws Exception;

    public int getCustomerTotalPages(CustomerVo customerVo) throws Exception;

    public List<CustomerBasic> selectCustomerByConditions(CustomerVo customerVo) throws Exception;

    public void updateCustomer(Customer customer) throws Exception;

    public CustomerBasic selectCustomerBasicByPhone(String phone) throws Exception;

    public void removeCustomers(Map<String, Integer> map) throws Exception;

    public Integer selectCustomerIdByPhone(String phone) throws Exception;

    // 以下可能待修改？？？提供给VisitService使用，因为只需要basic部分，单独basic可以减少数据库的压力？
    public CustomerBasic selectCustomerBasicById(int id) throws Exception;

    public void updateCustomerBasic(CustomerBasic customerBasic) throws Exception;




}
