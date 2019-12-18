package priv.jesse.mall.service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpRequest;
import priv.jesse.mall.entity.AdminUser;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface AdminUserService {

    AdminUser findById(int id);
    Page<AdminUser> findAll(Pageable pageable);
    List<AdminUser> findAllExample(Example<AdminUser> example);
    void update(AdminUser adminUser);
    int create(AdminUser adminUser);
    void delById(int id);
    AdminUser checkLogin(HttpServletRequest request,String username, String pwd);

}
