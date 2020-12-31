package com.wilson.im.commons.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @quthor wlison
 * Restful风格接口的统一返回值
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WlisonResult<T> {

    //状态码
    private Integer errCode;

    //消息提示
    private String errMsg;

    //返回的结果
    private T data;

    public WlisonResult(Integer errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }
}
