package priv.jesse.mall.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import priv.jesse.mall.entity.Product;

import java.util.Date;
import java.util.List;

public interface ProductDao extends JpaRepository<Product, Integer> {

    List<Product> findByCsid(int csid, Pageable pageable);//按类别查找

    List<Product> findByCsidIn(List<Integer> csids,Pageable pageable);

    List<Product> findByIsHot(int isHot,Pageable pageable);//找出有存货的商品

}
