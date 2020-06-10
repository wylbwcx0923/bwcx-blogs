package com.bwcx.qn.flow.card.service;

import com.bwcx.qn.flow.card.entity.Product;

import java.util.List;

/**
 * 商品服务
 */
public interface ProductService {

    /**
     * 添加商品
     *
     * @param product
     * @return
     */
    boolean addProduct(Product product);

    /**
     * 删除商品
     *
     * @param id
     * @return
     */
    boolean delProductById(Integer id);

    /**
     * 修改商品
     *
     * @param product
     * @return
     */
    boolean updateProduct(Product product);

    /**
     * 通过ID获得商品
     *
     * @param id
     * @return
     */
    Product findProductById(Integer id);

    /**
     * 获得所有商品
     *
     * @return
     */
    List<Product> findAllList();
}
