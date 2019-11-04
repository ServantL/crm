package cn.servant.crm.service;

import cn.servant.crm.pojo.po.Visit;
import cn.servant.crm.pojo.vo.CustomerVo;
import cn.servant.crm.pojo.vo.VisitVo;

import java.util.List;

public interface VisitService {

    public List<Visit> selectVisitByConditions(VisitVo visitVo) throws Exception;

    public int getVisitTotalCount(VisitVo visitVo) throws Exception;

    public int getVisitTotalPages(VisitVo visitVo) throws Exception;

    public void insertVisit(Visit visit) throws Exception;

}
