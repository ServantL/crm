package cn.servant.crm.mapper;

import cn.servant.crm.pojo.po.Visit;
import cn.servant.crm.pojo.vo.VisitVo;

import java.util.List;

public interface VisitMapper {

    public List<Visit> selectVisitByConditions(VisitVo visitVo) throws Exception;

    public int getVisitTotalCount(VisitVo visitVo) throws Exception;

    public void insertVisit(Visit visit) throws Exception;


}
