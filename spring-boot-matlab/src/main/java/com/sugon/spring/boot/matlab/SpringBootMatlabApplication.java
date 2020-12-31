package com.sugon.spring.boot.matlab;

import com.alibaba.fastjson.JSON;
import com.mathworks.toolbox.javabuilder.MWException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import yumaiBoltFatigue.ZCYPMatlabUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SpringBootApplication
@RestController
public class SpringBootMatlabApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMatlabApplication.class, args);
    }

    @GetMapping(value = "/boltSet")
    public String getRes(@RequestParam(value = "m") Double m,
                         @RequestParam(value = "D") Double D,
                         @RequestParam(value = "markovParentPath") String markovParentPath,
                         @RequestParam(value = "computeType") String computeType) {
        try {
            ZCYPMatlabUtil zcfdMatlabUtil = new ZCYPMatlabUtil();
            if (!markovParentPath.endsWith("/")) {
                markovParentPath = markovParentPath + "/";
            }

            if ("1".equalsIgnoreCase(computeType)) {
                final Object[] objects = zcfdMatlabUtil.compute20161(1, m, D, markovParentPath);
                return objects[0].toString();
            }
            if ("2".equalsIgnoreCase(computeType)) {
                final Object[] objects = zcfdMatlabUtil.compute20162(1, m, D, markovParentPath);
                return objects[0].toString();
            }
            if ("3".equalsIgnoreCase(computeType)) {
                final Object[] objects = zcfdMatlabUtil.compute2019(1, m, D, markovParentPath);
                return objects[0].toString();
            }
        } catch (MWException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/safetyFactor")
    public String getSafetyFactor(
            @RequestBody Map<String, Object> params) {
        System.out.println(JSON.toJSONString(params));
        Set<String> keySet = params.keySet();
        for (String item : keySet) {
            if (!item.equals("x0") && !item.equals("y0") && !item.equals("markovParentPath")) {
                String value = params.get(item).toString();
                if (!value.contains(".")) {
                    params.put(item, value + ".0");
                } else {
                    params.put(item, value);
                }
            }
        }
        Double Nd = Double.parseDouble((String) params.get("Nd"));
        Double ks = Double.parseDouble((String) params.get("ks"));
        Double m = Double.parseDouble((String) params.get("m"));
        Double d = Double.parseDouble((String) params.get("d"));
        Double As = Double.parseDouble((String) params.get("As"));
        Double D = Double.parseDouble((String) params.get("D"));

        List<Object> x0Ary = (List<Object>) params.get("x0");
        List<Object> y0Ary = (List<Object>) params.get("y0");
        for (int i = 0; i < x0Ary.size(); i++) {
            String numX = x0Ary.get(i).toString();
            String numY = y0Ary.get(i).toString();
            if (!numX.contains(".")) {
                numX = numX + ".0";
                x0Ary.set(i, Double.parseDouble(numX));
            }

            if (!numY.contains(".")) {
                numY = numY + ".0";
                y0Ary.set(i, Double.parseDouble(numY));
            }

        }
        Object[] x0 = x0Ary.toArray();
        Object[] y0 = y0Ary.toArray();
        String markovParentPath = (String) params.get("markovParentPath");
        try {
            System.out.println(Nd);
            System.out.println(ks);
            System.out.println(m);
            System.out.println(d);
            System.out.println(As);
            System.out.println(D);
            System.out.println(Arrays.toString(x0));
            System.out.println(Arrays.toString(y0));

            ZCYPMatlabUtil zcfdMatlabUtil = new ZCYPMatlabUtil();
            Object[] objects = zcfdMatlabUtil.yumaiBoltFatigue(1, Nd, ks, m, d, As, D, x0, y0, markovParentPath);
            return objects[0].toString();
        } catch (MWException e) {
            e.printStackTrace();
        }
        return null;
    }


}
