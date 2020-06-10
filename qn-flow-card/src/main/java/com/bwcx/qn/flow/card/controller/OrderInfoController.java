package com.bwcx.qn.flow.card.controller;

import com.bwcx.qn.flow.card.config.QNResult;
import com.bwcx.qn.flow.card.entity.OrderInfo;
import com.bwcx.qn.flow.card.service.OrderService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/order")
public class OrderInfoController {

    @Resource(name = "orderService")
    private OrderService orderService;

    @PostMapping(value = "/add")
    public QNResult addOrder(@RequestBody OrderInfo orderInfo) {
        return orderService.addOrder(orderInfo);
    }

    @DeleteMapping(value = "/del")
    public QNResult delOrderById(@RequestParam(value = "ids") Integer[] ids) {
        boolean flag = orderService.delOrderById(ids);
        if (flag) {
            return new QNResult(true, 20000, "删除成功");
        }
        return new QNResult(false, 50000, "删除失败");
    }

    @DeleteMapping(value = "/del/{id}")
    public QNResult delById(@PathVariable Integer id) {
        boolean flag = orderService.deleteById(id);
        if (flag) {
            return new QNResult(true, 20000, "删除成功");
        }
        return new QNResult(false, 50000, "删除失败");
    }

    @GetMapping(value = "/find/{id}")
    public QNResult findOrderById(@PathVariable Integer id) {
        return new QNResult(true, 20000, "请求成功", orderService.findOrderById(id));
    }

    @PutMapping(value = "/update")
    public QNResult updateOrder(@RequestBody OrderInfo orderInfo) {
        boolean flag = orderService.updateOrder(orderInfo);
        if (flag) {
            return new QNResult(true, 20000, "修改成功");
        }
        return new QNResult(false, 50000, "修改失败");
    }

    @PostMapping(value = "/list/{pageIndex}/{pageSize}/{roleId}")
    public QNResult getListByParamsForPage(@PathVariable Integer pageIndex,
                                           @PathVariable Integer pageSize,
                                           @PathVariable Integer roleId,
                                           @RequestBody OrderInfo orderInfo) {
        System.out.println();
        PageInfo<OrderInfo> orderList = orderService.getOrderList(pageIndex, pageSize, orderInfo, roleId);
        return new QNResult(true, 20000, "请求成功", orderList);
    }

    @GetMapping(value = "/count")
    public QNResult countOrder() {
        return new QNResult(true, 20000, "请求成功", orderService.countOrders());
    }
}
