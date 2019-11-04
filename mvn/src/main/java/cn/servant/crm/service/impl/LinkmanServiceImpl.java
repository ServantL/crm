package cn.servant.crm.service.impl;

import cn.servant.crm.exception.LinkmanException;
import cn.servant.crm.mapper.LinkmanMapper;
import cn.servant.crm.pojo.po.Linkman;
import cn.servant.crm.pojo.po.User;
import cn.servant.crm.pojo.vo.LinkmanVo;
import cn.servant.crm.service.LinkmanService;
import cn.servant.crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("linkmanService")
public class LinkmanServiceImpl implements LinkmanService {

    @Autowired
    private LinkmanMapper linkmanMapper;

    @Autowired
    private UserService userService;

    @Override
    public List<Linkman> selectLinkmanByConditions(LinkmanVo linkmanVo) throws Exception {
        if (linkmanVo.getPage().getTotalCount() == 0) {
            return null;
        }
        return linkmanMapper.selectLinkmanByConditions(linkmanVo);
    }

    @Override
    public int getLinkmanTotalCount(LinkmanVo linkmanVo) throws Exception {
        return linkmanMapper.getLinkmanTotalCount(linkmanVo);
    }

    @Override
    public int getLinkmanTotalPages(LinkmanVo linkmanVo) throws Exception {
        int totalCount = linkmanMapper.getLinkmanTotalCount(linkmanVo);
        int pages = (int) Math.ceil(totalCount * 1.0 / linkmanVo.getPage().getPageSize());
        return pages;
    }

    /**
     * 联合查询，提供给customer修改页面修改联系人时使用，其值只有联系人id以及对应的姓名
     * @return
     */
    @Override
    public List<Linkman> selectLinkmanNameId() throws Exception {
        return linkmanMapper.selectLinkmanNameId();
    }


    @Override
    public void deleteLinkmanById(int id) throws Exception {
        linkmanMapper.deleteLinkmanById(id);
    }

    @Override
    public Linkman selectLinkmanById(Integer id) throws Exception {
        return linkmanMapper.selectLinkmanById(id);
    }

    @Override
    public void insertLinkman(LinkmanVo linkmanVo) throws Exception {
        Linkman linkman = linkmanVo.getLinkman();
        User user = linkmanVo.getUser();
        user.setUserRights(2);
        linkmanMapper.insertLinkman(linkman);
        user.setUserLinkman(linkman);
        userService.insertUser(user);
    }

    @Override
    public void updateLinkman(Linkman linkman) throws Exception {
        linkmanMapper.updateLinkman(linkman);
    }

    @Override
    public Integer selectLinkmanIdByName(String linkName) throws Exception {
        return linkmanMapper.selectLinkmanIdByName(linkName);
    }

    @Override
    public Integer selectLinkmanIdByPhone(String phone) throws Exception {
        return linkmanMapper.selectLinkmanIdByPhone(phone);
    }
}
