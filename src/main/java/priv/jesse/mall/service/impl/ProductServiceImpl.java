package priv.jesse.mall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import priv.jesse.mall.dao.ClassificationDao;
import priv.jesse.mall.dao.ProductDao;
import priv.jesse.mall.entity.Classification;
import priv.jesse.mall.entity.Product;
import priv.jesse.mall.service.ProductService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ClassificationDao classificationDao;

    @Override
    public Product findById(int id) {
        return productDao.getOne(id);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productDao.findAll(pageable);
    }


    @Override
    public List<Product> findHotProduct() {
        return productDao.findByIsHot(1, null);
    }


    @Override
    public List<Product> findNewProduct(Pageable pageable) {
        // 查找两周内上架的商品
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.DAY_OF_MONTH, -14);
        return productDao.findNew(pageable);
    }

    @Override
    public List<Product> findByCid(int cid, Pageable pageable) {
        //查找出所有二级分类
        List<Classification> sec = classificationDao.findByParentId(cid);
        List<Integer> secIds = new ArrayList<>();
        for (Classification classification : sec) {
            secIds.add(classification.getId());
        }
        return productDao.findByCsidIn(secIds,pageable);
    }

    @Override
    public List<Product> findByCsid(int csid, Pageable pageable) {
        return productDao.findByCsid(csid,pageable);
    }


    @Override
    public void update(Product product) {
        productDao.save(product);
    }

    @Override
    public int create(Product product) {
        return productDao.save(product).getId();
    }

    @Override
    public void delById(int id) {
        productDao.delete(id);
    }
}
