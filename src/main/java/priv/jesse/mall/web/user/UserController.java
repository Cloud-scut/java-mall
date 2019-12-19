package priv.jesse.mall.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import priv.jesse.mall.entity.User;
import priv.jesse.mall.entity.pojo.ResultBean;
import priv.jesse.mall.service.UserService;
import priv.jesse.mall.service.exception.LoginException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/toRegister.html")
    public String toRegister() {
        return "mall/user/register";
    }//加载注册页面

    @RequestMapping("/toLogin.html")
    public String toLogin() {
        return "mall/user/login";
    }//加载登录页面

    @RequestMapping("/login.do")//确认登录传递请求、参数
    public void login(String username,
                      String password,
                      HttpServletRequest request,
                      HttpServletResponse response) throws IOException {
        User user = userService.checkLogin(username, password);
        if (user != null) {
            //登录成功 重定向到首页
            request.getSession().setAttribute("user", user);//设置session绑定用户
            response.sendRedirect("/mall/index.html");
        } else {
            throw new LoginException("登录失败！ 用户名或者密码错误");
        }

    }

    @RequestMapping("/register.do")//确认注册传递请求、参数
    public void register(String username,
                         String password,
                         String name,
                         String phone,
                         String email,
                         String addr,
                         HttpServletResponse response) throws IOException {
        User user = new User();
        user.setUsername(username);
        user.setPhone(phone);
        user.setPassword(password);
        user.setName(name);
        user.setEmail(email);
        user.setAddr(addr);
        userService.create(user);
        // 注册完成后重定向到登录页面
        response.sendRedirect("/mall/user/toLogin.html");
    }

    @RequestMapping("/logout.do")//注销
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("user");//接触Session
        response.sendRedirect("/mall/index.html");//重定向回到首页
    }

    @ResponseBody
    @RequestMapping("/checkUsername.do")
    public ResultBean<Boolean> checkUsername(String username){
        List<User> users = userService.findByUsername(username);
        if (users==null||users.isEmpty()){
            return new ResultBean<>(true);
        }
        return new ResultBean<>(false);
    }

    @RequestMapping("/error.html")
    public String error(HttpServletResponse response, HttpServletRequest request) {
        return "error";
    }
}
