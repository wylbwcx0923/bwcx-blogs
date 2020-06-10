package com.bwcx.qn.flow.card.controller;

import com.bwcx.qn.flow.card.config.QNResult;
import com.bwcx.qn.flow.card.entity.Product;
import com.bwcx.qn.flow.card.service.ProductService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Resource(name = "productService")
    private ProductService productService;

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public QNResult addProduct(@RequestBody Product product) {
        boolean flag = productService.addProduct(product);
        if (flag) {
            return new QNResult(true, 20000, "添加成功");
        }
        return new QNResult(false, 50000, "添加失败");
    }

    @RequestMapping(value = "del/{id}", method = RequestMethod.DELETE)
    public QNResult delProductById(@PathVariable Integer id) {
        boolean flag = productService.delProductById(id);
        if (flag) {
            return new QNResult(true, 20000, "删除成功");
        }
        return new QNResult(false, 50000, "删除失败");
    }


    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public QNResult updateProduct(@RequestBody Product product) {
        boolean flag = productService.updateProduct(product);
        if (flag) {
            return new QNResult(true, 20000, "修改成功");
        }
        return new QNResult(false, 50000, "修改失败");
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public QNResult findList() {
        return new QNResult(true, 20000, "请求成功", productService.findAllList());
    }

    @RequestMapping(value = "find/{id}", method = RequestMethod.GET)
    public QNResult findProductById(@PathVariable Integer id) {
        Product product = productService.findProductById(id);
        return new QNResult(true, 20000, "请求成功", product);

    }


}
