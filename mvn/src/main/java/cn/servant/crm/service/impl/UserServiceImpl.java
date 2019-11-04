package cn.servant.crm.service.impl;

import cn.servant.crm.mapper.UserMapper;
import cn.servant.crm.pojo.po.User;
import cn.servant.crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectUserByAccountAndPassword(User user) throws Exception {
        return userMapper.selectUserByAccountAndPassword(user);
    }

    @Override
    public User updateUserPassword(User user) throws Exception {
        // 修改user
        userMapper.updateUserPassword(user);
        // 根据之前user的id返回修改后的user，controller可以的之前的user进行更新（更新session）
        return userMapper.selectUserById(user.getUserId());
    }

    @Override
    public void insertUser(User user) throws Exception {
        userMapper.insertUser(user);
    }
}
