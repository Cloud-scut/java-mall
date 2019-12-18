package priv.jesse.mall.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import priv.jesse.mall.entity.User;

import java.util.List;

public interface UserDao extends JpaRepository<User, Integer> {

    User findByUsernameAndPassword(String username, String password);//用户名、密码找用户

    List<User> findByUsername(String username);//匹配用户名
}
