package cn.servant.crm.service.impl;

import cn.servant.crm.mapper.CustomerBasicMapper;
import cn.servant.crm.mapper.CustomerDetailMapper;
import cn.servant.crm.pojo.Customer;
import cn.servant.crm.pojo.po.CustomerBasic;
import cn.servant.crm.pojo.po.CustomerDetail;
import cn.servant.crm.pojo.po.Visit;
import cn.servant.crm.pojo.vo.CustomerVo;
import cn.servant.crm.service.CustomerService;
import cn.servant.crm.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerBasicMapper customerBasicMapper;
    @Autowired
    private CustomerDetailMapper customerDetailMapper;
    @Autowired
    private VisitService visitService;

    @Override
    public void insertCustomer(Customer customer) throws Exception {
        // 当进行插入用户的时候，同时也说明了该客户是第一次访问，所以再次需要为其插入一条访问记录，
        // 因为VisitService中的插入包含了令该客户的访问次数 +1，所以前端默认初始访问次数为0
        // 或者可以修改为数据库该字段的默认值为0
        customerBasicMapper.insertCustomerBasic(customer.getCustomerBasic());
        customer.getCustomerDetail().setCustId(customer.getCustomerBasic().getCustId());
        customerDetailMapper.insertCustomerDetail(customer.getCustomerDetail());
        Visit visit = new Visit();
        visit.setVisitDate(new Date());
        visit.setVisitCustomer(customer.getCustomerBasic());
        visitService.insertVisit(visit);
    }

    @Override
    public Customer selectCustomerById(int id) throws Exception {
        CustomerBasic customerBasic = customerBasicMapper.selectCustomerBasicById(id);
        CustomerDetail customerDetail = customerDetailMapper.selectCustomerDetailById(id);
        Customer customer = new Customer();
        customer.setCustomerBasic(customerBasic);
        customer.setCustomerDetail(customerDetail);
        return customer;
    }

    @Override
    public void deleteCustomerById(int id) throws Exception {
        customerBasicMapper.deleteCustomerBasicById(id);
        customerDetailMapper.deleteCustomerDetailById(id);
    }

    @Override
    public int getCustomerTotalCount(CustomerVo customerVo) throws Exception {
        return customerBasicMapper.getCustomerTotalCount(customerVo);
    }

    @Override
    public int getCustomerTotalPages(CustomerVo customerVo) throws Exception {
        int totalCount = getCustomerTotalCount(customerVo);
        return (int) Math.ceil(totalCount*1.0 / customerVo.getPage().getPageSize() );
    }

    @Override
    public List<CustomerBasic> selectCustomerByConditions(CustomerVo customerVo) throws Exception {
        if (customerVo.getPage().getTotalCount() == 0) {
            return null;
        }
        return customerBasicMapper.selectCustomerByConditions(customerVo);
    }

    @Override
    public void updateCustomer(Customer customer) throws Exception {
        customer.getCustomerDetail().setCustId(customer.getCustomerBasic().getCustId());
        customerBasicMapper.updateCustomerBasic(customer.getCustomerBasic());
        customerDetailMapper.updateCustomerDetail(customer.getCustomerDetail());
    }

    @Override
    public CustomerBasic selectCustomerBasicByPhone(String phone) throws Exception {
        return customerBasicMapper.selectCustomerBasicByPhone(phone);
    }

    @Override
    public void removeCustomers(Map<String, Integer> map) throws Exception {
        customerBasicMapper.removeCustomers(map);
    }

    @Override
    public Integer selectCustomerIdByPhone(String phone) throws Exception {
        return customerBasicMapper.selectCustomerIdByPhone(phone);
    }

    @Override
    public CustomerBasic selectCustomerBasicById(int id) throws Exception {
        return customerBasicMapper.selectCustomerBasicById(id);
    }

    @Override
    public void updateCustomerBasic(CustomerBasic customerBasic) throws Exception {
        customerBasicMapper.updateCustomerBasic(customerBasic);
    }
}
