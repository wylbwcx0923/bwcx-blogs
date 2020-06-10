package com.bwcx.qn.flow.card.utils;

public class OrderStatusUtil {

    public static String getOrderStatusText(Integer status) {
        switch (status) {
            case 1:
                return "未处理";
            case 2:
                return "未发货";
            case 3:
                return "已发货";
            case 4:
                return "已签收";
            case 5:
                return "已确认";
            case 6:
                return "拒收";
            case 7:
                return "短信确认";
            case 8:
                return "短信撤单";
            case 9:
                return "电话不通";
            case 10:
                return "联系不到";
            case 11:
                return "不要了";
            case 12:
                return "考虑中";
        }
        return "状态错误";
    }
}
