package priv.jesse.mall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import priv.jesse.mall.dao.UserDao;
import priv.jesse.mall.entity.User;
import priv.jesse.mall.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public User findById(int id) {
        return userDao.getOne(id);
    }//根据id查询用户
    @Override
    public Page<User> findAll(Pageable pageable) {
        return userDao.findAll(pageable);
    }//分页管理（没有实现）

    @Override
    public void update(User user) {
        userDao.save(user);
    }//用户注册后更新到数据库
    @Override
    public int create(User user) {
        return userDao.save(user).getId();
    }//返回新注册用户id
    @Override
    public void delById(int id) {
        userDao.delete(id);
    }//根据id删除用户
    @Override
    public List<User> findByUsername(String username) {
        return userDao.findByUsername(username);
    }//根据用户名查询用户
    @Override
    public User checkLogin(String username, String password) {
        return userDao.findByUsernameAndPassword(username, password);//登录匹配
    }
    @Override
    public List<User> findAllExample(Example<User> example) {
        return userDao.findAll(example);
    }
}
