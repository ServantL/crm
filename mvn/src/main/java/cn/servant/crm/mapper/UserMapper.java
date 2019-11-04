package cn.servant.crm.mapper;

import cn.servant.crm.pojo.po.User;

public interface UserMapper {

    public User selectUserByAccountAndPassword(User user) throws Exception;

    public void updateUserPassword(User user) throws Exception;

    public User selectUserById(int id) throws Exception;

    public void insertUser(User user)throws Exception;
}
