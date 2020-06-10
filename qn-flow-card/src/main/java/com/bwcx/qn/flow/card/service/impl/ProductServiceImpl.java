package com.bwcx.qn.flow.card.service.impl;

import com.bwcx.qn.flow.card.entity.Product;
import com.bwcx.qn.flow.card.mapper.ProductMapper;
import com.bwcx.qn.flow.card.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service(value = "productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public boolean addProduct(Product product) {
        Product productKey = new Product();
        productKey.setProductId(product.getProductId());
        if (productMapper.selectOne(productKey) != null) {
            return false;
        }
        product.setCreateTime(new Date());
        return productMapper.insertUseGeneratedKeys(product) > 0;
    }

    @Override
    public boolean delProductById(Integer id) {
        return productMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public boolean updateProduct(Product product) {
        return productMapper.updateByPrimaryKeySelective(product) > 0;
    }

    @Override
    public Product findProductById(Integer id) {
        return productMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Product> findAllList() {
        List<Product> products = productMapper.selectAll();
        return products;
    }
}
