package cn.servant.crm.service;

import cn.servant.crm.pojo.po.User;

public interface UserService {

    public User selectUserByAccountAndPassword(User user) throws Exception;

    public User updateUserPassword(User user) throws Exception;

    public void insertUser(User user) throws Exception;
}
