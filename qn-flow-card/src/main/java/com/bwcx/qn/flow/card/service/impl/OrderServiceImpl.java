package com.bwcx.qn.flow.card.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bwcx.qn.flow.card.config.QNResult;
import com.bwcx.qn.flow.card.entity.OrderInfo;
import com.bwcx.qn.flow.card.entity.Product;
import com.bwcx.qn.flow.card.mapper.OrderInfoMapper;
import com.bwcx.qn.flow.card.mapper.ProductMapper;
import com.bwcx.qn.flow.card.service.CheckIdCardService;
import com.bwcx.qn.flow.card.service.OrderService;
import com.bwcx.qn.flow.card.utils.IPUtil;
import com.bwcx.qn.flow.card.utils.OrderStatusUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Service(value = "orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderInfoMapper orderMapper;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private IPUtil ipUtil;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private CheckIdCardService checkIdCardService;


    @Override
    public QNResult addOrder(OrderInfo order) {
        Example example = new Example(OrderInfo.class);
        example.createCriteria().andEqualTo("idCard", order.getIdCard());
        //校验该手机是否领取过
        if (orderMapper.selectByExample(example).size() > 0) {
            return new QNResult(false, 50000, "每个用户身份证只能申请一次");
        }
        Example example1 = new Example(OrderInfo.class);
        example1.createCriteria().andEqualTo("phoneNum", order.getPhoneNum());
        if (orderMapper.selectByExample(example1).size() > 0) {
            return new QNResult(false, 50000, "每个手机号码只能申请一次");
        }
        QNResult qnResult = checkIdCardService.checkIdCard(order.getRealName(), order.getIdCard(), order.getProvince(), order.getCity());
        if (qnResult != null) {
            String data = (String) qnResult.getData();
            JSONObject jsonObject = JSON.parseObject(data);
            boolean flagCheckUser = (boolean) jsonObject.get("success");
            String message = (String) jsonObject.get("message");
            if (!flagCheckUser) {
                return new QNResult(false, 50000, message);
            }
        }
        //程序执行到此,说明用户可以正常申请
        //下面开始下单
        order.setOrderId("r" + System.currentTimeMillis());
        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());
        order.setOperator("尚未处理");
        order.setTrackNum("尚未发货");
        order.setStatus(1);//订单状态未处理
        order.setStatusText(OrderStatusUtil.getOrderStatusText(1));
        order.setAddressInfo(order.getProvince() + "-" + order.getCity() + "-" + order.getCounty() + "-" + order.getAddressInfo());
        order.setDownOrderIp(ipUtil.getIpAddress(request));
        int rows = orderMapper.insertSelective(order);
        if (rows > 0) {
            return new QNResult(true, 20000, "申请成功，我们会尽快为您发货");
        }
        return new QNResult(false, 50000, "申请失败,系统内部错误");
    }

    @Override
    public boolean delOrderById(Integer[] ids) {
        int rows = 0;
        if (ids == null || ids.length > 0) {
            for (Integer id : ids) {
                rows += orderMapper.deleteByPrimaryKey(id);
            }
        }
        return rows > 0;
    }

    @Override
    public boolean deleteById(Integer id) {
        return orderMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public OrderInfo findOrderById(Integer id) {
        OrderInfo orderInfo = orderMapper.selectByPrimaryKey(id);
        if (orderInfo != null) {
            Example example = new Example(Product.class);
            example.createCriteria().andEqualTo("productId", orderInfo.getProductType());
            List<Product> products = productMapper.selectByExample(example);
            if (products != null && products.size() > 0) {
                orderInfo.setProductType(products.get(0).getProductId());
            }
        }
        return orderInfo;
    }

    @Override
    public boolean updateOrder(OrderInfo order) {
        order.setStatusText(OrderStatusUtil.getOrderStatusText(order.getStatus()));
        order.setUpdateTime(new Date());
        order.setAddressInfo(order.getProvince() + "-" + order.getCity() + "-" + order.getCounty() + "-" + order.getAddressInfo());
        int rows = orderMapper.updateByPrimaryKeySelective(order);
        return rows > 0;
    }

    @Override
    public PageInfo<OrderInfo> getOrderList(Integer pageIndex, Integer pageSize
            , OrderInfo order, Integer roleId) {
        PageHelper.startPage(pageIndex, pageSize);
        List<OrderInfo> orderInfos = orderMapper.selectOrderByParamsForPage(order);
        List<OrderInfo> orderInfosNotAdmin = new ArrayList<>();
        if (orderInfos != null && orderInfos.size() > 0) {
            for (OrderInfo orderInfo : orderInfos) {
                orderInfo.setProductType(productMapper.selectByProductId(orderInfo.getProductType()).getProductName());
                if ("尚未处理".equals(orderInfo.getOperator()) || order.getOperator().equals(orderInfo.getOperator())) {
                    orderInfosNotAdmin.add(orderInfo);
                }
            }
        }
        if (roleId == 1) {
            return new PageInfo<>(orderInfos);
        }
        return new PageInfo<>(orderInfosNotAdmin);
    }

    @Override
    public List<OrderInfo> getOrderListPOI(OrderInfo order, Integer roleId) {
        List<OrderInfo> orderInfos = orderMapper.selectOrderByParamsForPage(order);
        List<OrderInfo> orderInfosNotAdmin = new ArrayList<>();
        if (orderInfos != null && orderInfos.size() > 0) {
            for (OrderInfo orderInfo : orderInfos) {
                orderInfo.setProductType(productMapper.selectByProductId(orderInfo.getProductType()).getProductName());
                if ("尚未处理".equals(orderInfo.getOperator()) || order.getOperator().equals(orderInfo.getOperator())) {
                    orderInfosNotAdmin.add(orderInfo);
                }
            }
        }
        if (roleId == 1) {
            return orderInfos;
        }
        return orderInfosNotAdmin;
    }

    @Override
    public Map<String, Object> countOrders() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //当日订单数
        Integer dayOrder = orderMapper.countOrder(sdf.format(new Date()));
        //当月订单数
        sdf = new SimpleDateFormat("yyyy-MM");
        Integer monthOrder = orderMapper.countOrder(sdf.format(new Date()));
        //本年订单数
        sdf = new SimpleDateFormat("yyyy");
        Integer yearOrder = orderMapper.countOrder(sdf.format(new Date()));
        //总订单数
        Integer totalOrder = orderMapper.countOrder(null);
        Map<String, Object> map = new HashMap<>();
        map.put("dayOrder", dayOrder);
        map.put("monthOrder", monthOrder);
        map.put("yearOrder", yearOrder);
        map.put("totalOrder", totalOrder);
        return map;
    }


}
