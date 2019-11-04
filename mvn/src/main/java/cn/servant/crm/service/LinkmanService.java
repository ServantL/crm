package cn.servant.crm.service;

import cn.servant.crm.pojo.po.Linkman;
import cn.servant.crm.pojo.vo.LinkmanVo;

import java.util.List;

public interface LinkmanService {

    public List<Linkman> selectLinkmanByConditions(LinkmanVo linkmanVo) throws Exception;

    public int getLinkmanTotalCount(LinkmanVo linkmanVo) throws Exception;

    public int getLinkmanTotalPages(LinkmanVo linkmanVo) throws Exception;

    public List<Linkman> selectLinkmanNameId() throws Exception;

    public void deleteLinkmanById(int id) throws Exception;

    public Linkman selectLinkmanById(Integer id) throws Exception;

    public void insertLinkman(LinkmanVo linkmanVo) throws Exception;

    public void updateLinkman(Linkman linkman) throws Exception;

    public Integer selectLinkmanIdByName(String linkName) throws Exception;

    public Integer selectLinkmanIdByPhone(String phone) throws Exception;
}
