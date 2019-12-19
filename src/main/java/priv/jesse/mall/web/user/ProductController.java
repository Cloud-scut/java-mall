package priv.jesse.mall.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import priv.jesse.mall.entity.Classification;
import priv.jesse.mall.entity.OrderItem;
import priv.jesse.mall.entity.Product;
import priv.jesse.mall.entity.pojo.ResultBean;
import priv.jesse.mall.service.ClassificationService;
import priv.jesse.mall.service.ProductService;
import priv.jesse.mall.service.ShopCartService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ClassificationService classificationService;
    @Autowired
    private ShopCartService shopCartService;

    @RequestMapping("/get.do")//传送展示的商品信息
    public ResultBean<Product> getProduct(int id) {
        Product product = productService.findById(id);
        return new ResultBean<>(product);
    }

    @RequestMapping("/get.html")//生成展示页面
    public String toProductPage(int id, Map<String, Object> map) {
        Product product = productService.findById(id);
        map.put("product", product);
        return "mall/product/info";
    }

    @ResponseBody
    @RequestMapping("/hot.do")
    public ResultBean<List<Product>> getHotProduct() {
        List<Product> products = productService.findHotProduct();
        return new ResultBean<>(products);
    }

    @ResponseBody
    @RequestMapping("/new.do")
    public ResultBean<List<Product>> getNewProduct(int pageNo, int pageSize) {
        Pageable pageable = new PageRequest(pageNo, pageSize);
        List<Product> products = productService.findNewProduct(pageable);
        return new ResultBean<>(products);
    }

    @RequestMapping("/category.html")//生成分类后的商品展示页面
    public String toCatePage(int cid, Map<String, Object> map) {
        Classification classification = classificationService.findById(cid);
        map.put("category", classification);
        return "mall/product/category";
    }

    @ResponseBody
    @RequestMapping("/category.do")
    public ResultBean<List<Product>> getCategoryProduct(int cid, int pageNo, int pageSize) {
        Pageable pageable = new PageRequest(pageNo, pageSize);
        List<Product> products = productService.findByCid(cid, pageable);
        return new ResultBean<>(products);
    }

    @ResponseBody
    @RequestMapping("/categorySec.do")
    public ResultBean<List<Product>> getCategorySecProduct(int csId, int pageNo, int pageSize) {
        Pageable pageable = new PageRequest(pageNo, pageSize);
        List<Product> products = productService.findByCsid(csId, pageable);
        return new ResultBean<>(products);
    }

    @ResponseBody
    @RequestMapping("/getCategorySec.do")
    public ResultBean<List<Classification>> getCategorySec(int cid){
        List<Classification> list = classificationService.findByParentId(cid);
        return new ResultBean<>(list);
    }

    @RequestMapping("/toCart.html")
    public String toCart(){
        return "mall/product/cart";
    }
    @ResponseBody
    @RequestMapping("/addCart.do")//将商品存入购物车
    public ResultBean<Boolean> addToCart(int productId, HttpServletRequest request) throws Exception {
        shopCartService.addCart(productId, request);//传递request获取session找到对应用户的购物车
        return new ResultBean<>(true);
    }
    @ResponseBody
    @RequestMapping("/delCart.do")//删除购物车里的条目
    public ResultBean<Boolean> delToCart(int productId, HttpServletRequest request) throws Exception {
        shopCartService.remove(productId, request);
        return new ResultBean<>(true);
    }
    @ResponseBody
    @RequestMapping("/listCart.do")//展示购物车
    public ResultBean<List<OrderItem>> listCart(HttpServletRequest request) throws Exception {
        List<OrderItem> orderItems = shopCartService.listCart(request);
        return new ResultBean<>(orderItems);
    }

}
