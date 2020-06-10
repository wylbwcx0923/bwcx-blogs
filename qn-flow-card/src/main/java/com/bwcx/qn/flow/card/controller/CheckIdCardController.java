package com.bwcx.qn.flow.card.controller;

import com.bwcx.qn.flow.card.config.QNResult;
import com.bwcx.qn.flow.card.service.CheckIdCardService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping(value = "/check")
public class CheckIdCardController {

    @Resource(name = "checkIdCardService")
    private CheckIdCardService checkIdCardService;


    @PostMapping(value = "/idCard")
    public QNResult checkId(@RequestBody Map<String, String> params) {
        String realName = params.get("realName");
        String idCard = params.get("idCard");
        String province = params.get("province");
        String city = params.get("city");
        return checkIdCardService.checkIdCard(realName, idCard, province, city);
    }
}
