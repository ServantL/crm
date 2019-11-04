package cn.servant.crm.pojo.vo;

import cn.servant.crm.pojo.Page;
import cn.servant.crm.pojo.po.Linkman;
import cn.servant.crm.pojo.po.User;

public class LinkmanVo {

    private Linkman linkman;

    private Page page;

    private User user;

    public Linkman getLinkman() {
        return linkman;
    }

    public void setLinkman(Linkman linkman) {
        this.linkman = linkman;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
