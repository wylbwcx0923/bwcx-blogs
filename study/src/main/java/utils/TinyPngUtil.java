package utils;

import com.tinify.Source;
import com.tinify.Tinify;

import javax.swing.*;
import java.io.IOException;

public class TinyPngUtil {
    public static void main(String[] args) throws IOException {

        Tinify.setKey("DLxDpjPCwsT2R4qKyb72yhx5vs3016L4");

        String path="/Users/wangyuliang/Desktop/Fig.SD3_T1.t2m.global.2020070108.png";

        Source source=Tinify.fromFile(path);
        source.toFile("/Users/wangyuliang/Desktop/Fig.SD3_T1.t2m.global.2020070108_bak.png");

    }
}
