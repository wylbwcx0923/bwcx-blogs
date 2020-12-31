package com.sugon;

import bushingFatigue.ZCFDMatlabUtil;
import com.mathworks.toolbox.javabuilder.MWException;

public class Main2 {
    public static void main(String[] args) {
        Object[] x0 = {-103.3, -92.9, -82.6, -72.3, -62.0, -51.6, -41.3, -31.0, -20.7, -10.3, 0.0, 10.3, 20.7, 31.0, 41.3, 51.6, 62.0, 72.3, 82.6, 92.9, 103.3};
        Object[] y0 = {513.200, 521.906, 530.384, 538.087, 545.499, 552.156, 558.309, 564.450, 570.568, 576.447, 583.238, 591.176, 600.033, 609.171, 618.389, 627.704, 637.240, 647.872, 660.174, 674.978, 691.708};
//        List<Object> x0 = Collections.singletonList(x0Ary);
//        List<Object> y0 = Collections.singletonList(y0Ary);
        try {
            ZCFDMatlabUtil zcfdMatlabUtil = new ZCFDMatlabUtil();
            final Object[] objects = zcfdMatlabUtil.yumaiBoltFatigue(1, 36.0, 1.265, 92.0, 26.0, 817.0, 2.3,
                    x0, y0, "/Users/wangyuliang/Desktop/zcyp/markov/");
            System.out.println(objects[0]);
        } catch (MWException e) {
            e.printStackTrace();
        }

    }
}
