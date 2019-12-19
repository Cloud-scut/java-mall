package priv.jesse.mall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import priv.jesse.mall.dao.OrderDao;
import priv.jesse.mall.dao.OrderItemDao;
import priv.jesse.mall.dao.ProductDao;
import priv.jesse.mall.entity.Order;
import priv.jesse.mall.entity.OrderItem;
import priv.jesse.mall.entity.Product;
import priv.jesse.mall.entity.User;
import priv.jesse.mall.service.OrderService;
import priv.jesse.mall.service.ShopCartService;
import priv.jesse.mall.service.exception.LoginException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderItemDao orderItemDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ShopCartService shopCartService;


    @Override
    public Order findById(int id) {
        return orderDao.getOne(id);
    }

    @Override
    public Page<Order> findAll(Pageable pageable) {
        return orderDao.findAll(pageable);
    }

    @Override
    public List<Order> findAllExample(Example<Order> example) {
        return orderDao.findAll(example);
    }

    @Override
    public void update(Order order) {
        orderDao.save(order);
    }

    @Override
    public int create(Order order) {
        Order order1 = orderDao.save(order);
        return order1.getId();
    }

    @Override
    public void delById(int id) {
        orderDao.delete(id);
    }


    @Override
    public List<OrderItem> findItems(int orderId) {
        List<OrderItem> list = orderItemDao.findByOrderId(orderId);
        for (OrderItem orderItem : list) {
            Product product = productDao.findOne(orderItem.getProductId());
            orderItem.setProduct(product);
        }
        return list;
    }


    @Override
    public void updateStatus(int id, int status) {
        Order order = orderDao.findOne(id);
        order.setState(status);
        orderDao.save(order);
    }

    @Override
    public List<Order> findUserOrder(HttpServletRequest request) {
        //从session中获取登录用户的id，查找他的订单
        Object user = request.getSession().getAttribute("user");
        if (user == null)
            throw new LoginException("请登录！");
        User loginUser = (User) user;
        List<Order> orders = orderDao.findByUserId(loginUser.getId());
        return orders;
    }

    @Override
    public void pay(int orderId) {
        //具体逻辑就不实现了，直接更改状态为 待发货
        Order order = orderDao.findOne(orderId);
        if (order == null)
            throw new RuntimeException("订单不存在");
        orderDao.updateState(STATE_WAITE_SEND,order.getId());
    }
    @Override
    @Transactional
    public void submit(String name, String phone, String addr, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Object user = request.getSession().getAttribute("user");
        if (user == null)
            throw new LoginException("请登录！");
        User loginUser = (User) user;
        Order order = new Order();
        order.setName(name);
        order.setPhone(phone);
        order.setAddr(addr);
        order.setOrderTime(new Date());
        order.setUserId(loginUser.getId());
        order.setState(STATE_NO_PAY);
        List<OrderItem> orderItems = shopCartService.listCart(request);
        Double total = 0.0;
        order.setTotal(total);
        order = orderDao.save(order);
        for (OrderItem orderItem : orderItems) {
            orderItem.setOrderId(order.getId());
            total += orderItem.getSubTotal();
            orderItemDao.save(orderItem);
        }
        order.setTotal(total);
        orderDao.save(order);
        //重定向到订单列表页面
        response.sendRedirect("/mall/order/toList.html");
    }

    @Override
    public void receive(int orderId) {
        Order order = orderDao.findOne(orderId);
        if (order == null)
            throw new RuntimeException("订单不存在");
        orderDao.updateState(STATE_COMPLETE,order.getId());
    }
}
