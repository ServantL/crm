package cn.servant.crm.mapper;

import cn.servant.crm.pojo.po.CustomerBasic;
import cn.servant.crm.pojo.vo.CustomerVo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

//@Repository
public interface CustomerBasicMapper {

    public int insertCustomerBasic(CustomerBasic customerBasic) throws Exception;

    public CustomerBasic selectCustomerBasicById(int id) throws Exception;

    public void deleteCustomerBasicById(int id) throws Exception;

    public int getCustomerTotalCount(CustomerVo customerVo) throws Exception;

    public List<CustomerBasic> selectCustomerByConditions(CustomerVo customerVo) throws Exception;

    public void updateCustomerBasic(CustomerBasic customerBasic) throws Exception;

    public List<CustomerBasic> selectCustomerByLinkId(int linkId) throws Exception;

    public CustomerBasic selectCustomerBasicByPhone(String phone) throws Exception;

    public Integer selectCustomerIdByPhone(String phone) throws Exception;

    /**
     * 因为显示全部客户的时候只需要显示客户的基本信息，
     * 所以将有关分页的查询操作都放在此接口即CustomerBasicMapper中即可
     * <p>
     * <p>
     * 在查看客户详细信息的时候，希望能够点击客户的联系人名字而跳转的联系人的信息页面
     * 此时需要注意该客户不拥有修改的功能（即可以设置有关权限来限制客户的操作）
     */

    public Map<String, Integer> selectCustomerToStatistic(int typeCode) throws Exception;


    public void removeCustomers(Map<String, Integer> map ) throws Exception;



    // 测试
    public CustomerBasic selectTest();

    public CustomerBasic selectTest1(Map<Object, Object> map);

}
