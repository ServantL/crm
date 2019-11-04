package cn.servant.crm.mapper;

import cn.servant.crm.pojo.po.Linkman;
import cn.servant.crm.pojo.vo.LinkmanVo;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface LinkmanMapper {

    public void insertLinkman(Linkman linkman) throws Exception;

    public Linkman selectLinkmanById(Integer id) throws Exception;

    public void deleteLinkmanById(int id) throws Exception;

    public int getLinkmanTotalCount(LinkmanVo linkmanVo) throws Exception;

    public List<Linkman> selectLinkmanByConditions(LinkmanVo linkmanVo) throws Exception;

    public List<Linkman> selectLinkmanNameId() throws Exception;

    public void updateLinkman(Linkman linkman) throws Exception;

    public Integer selectLinkmanIdByName(String linkName) throws Exception;

    public Integer selectLinkmanIdByPhone(String phone) throws Exception;

    /**
     * 删除联系人时，因为有外键约束，所以先进行客户转移或删除所有客户，再进行删除
     * 在LinkManMapper中进行了一对多的延迟联合查询，在点击删除时，
     * 将linkman中的客户List传到后台后进行判断，如果为空，则返回弹窗确定是否删除，
     * 如果有客户则弹出一个新的页面显示客户的基本信息然后选择的客户的操作
     */

    /**
     * 前端传输数据最好都改成Vo类型的，而后台需要什么再从该Vo中提取即可，
     * 这样方便与前端交互，省去了不需要知道参数而花费的时间，
     * 所有前端会需要用到什么参数即在Vo类型中加即可，
     * 这样也方便因修改需求而带来的不便
     */


    /**
     * 修改pojo结构
     * 与数据库表对应的对象改为PO，则表中的每一行数据则对应则一个PO对象，
     * 用于与数据库交互的对象改DTO,则数据库查出来的数据都封装到DTO中，而要传参的也都为DTO，
     * 在service中，则将所有的对象封装到BO对象中，因为有些业务需要联合多个PO，
     * 在service与controller交互的对象则全都该为VO，VO中存储则BO对象。
     * 由底往上的关系如下：
     * dao <-- DTO（封装了PO） --> service <-- VO（封装了BO） --> controller
     */
}
