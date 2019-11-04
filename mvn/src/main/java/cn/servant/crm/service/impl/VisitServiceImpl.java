package cn.servant.crm.service.impl;

import cn.servant.crm.mapper.CustomerBasicMapper;
import cn.servant.crm.mapper.VisitMapper;
import cn.servant.crm.pojo.po.CustomerBasic;
import cn.servant.crm.pojo.po.Visit;
import cn.servant.crm.pojo.vo.VisitVo;
import cn.servant.crm.service.CustomerService;
import cn.servant.crm.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitServiceImpl implements VisitService {

    @Autowired
    private VisitMapper visitMapper;
    @Autowired
    private CustomerService customerService;

    @Override
    public List<Visit> selectVisitByConditions(VisitVo visitVo) throws Exception {
        if (visitVo.getPage().getPageNow() == 0) {
            return null;
        }
        return visitMapper.selectVisitByConditions(visitVo);
    }

    @Override
    public int getVisitTotalCount(VisitVo visitVo) throws Exception {
        return visitMapper.getVisitTotalCount(visitVo);
    }

    @Override
    public int getVisitTotalPages(VisitVo visitVo) throws Exception {
        int totalCount = getVisitTotalCount(visitVo);
        return (int) Math.ceil(totalCount*1.0 / visitVo.getPage().getPageSize() );
    }

    @Override
    public void insertVisit(Visit visit) throws Exception {
        // 每插入一条客户则该客户的访问总次数要 +1
        // 要先进行判断该是否存在有该手机的用户，如果没有，则跳转到添加客户的页面，所以插入访问记录要在后面
        // 所以此方法的方法的返回值可以改为boolean，以便controller进行判断
        // 做法：因为前端先用ajax查询出有无对应的手机客户，所以如果能传进来的话那么必存在该用户，
        //       而且已经将id赋给了visit属性中，减少从数据库查询的次数
        //       所以该方法不需要返回值了
        // 重点：visit的service最好只能调用自己的mapper类，而不要调用其他的mapper，
        //       如果需要关联到其他的数据库，则调用其对应的service即可？？？
        CustomerBasic customerBasic = null;
        if(visit.getVisitCustomer().getCustId() == null){
            customerBasic = customerService.selectCustomerBasicByPhone(visit.getVisitCustomer().getCustPhone());
        }else{
            customerBasic = customerService.selectCustomerBasicById(visit.getVisitCustomer().getCustId());
        }
        customerBasic.setCustVisitTime(customerBasic.getCustVisitTime() + 1);
        customerService.updateCustomerBasic(customerBasic);
        visit.setVisitCustomer(customerBasic);
        visitMapper.insertVisit(visit);

        /**
         * 前台提供插入客户访问记录的多种方式？？？
         * 可根据客户的名称？或只能通过手机号码进行添加？？？(暂时是考虑只能用手机号码插入）
         * 后再根据提供的数据查询出该客户的信息
         * 然后修改该客户的总访问次数并进行修改。
         * 因为visit表中对应客户的是主键id，所以修改顺序并不重要？？？
         */
    }
}
