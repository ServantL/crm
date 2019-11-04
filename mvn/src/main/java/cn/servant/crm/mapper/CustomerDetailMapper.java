package cn.servant.crm.mapper;

import cn.servant.crm.pojo.po.CustomerDetail;
import org.springframework.stereotype.Repository;

//@Repository
public interface CustomerDetailMapper {

    // 根据客户id查询用户的详细信息
    public CustomerDetail selectCustomerDetailById(int id) throws Exception;

    // 插入客户的详细信息
    public void insertCustomerDetail(CustomerDetail customerDetail) throws Exception;

    // 根据id删除客户的详细信息
    public void deleteCustomerDetailById(int id) throws Exception;

    public void updateCustomerDetail(CustomerDetail customerDetail) throws Exception;


}
