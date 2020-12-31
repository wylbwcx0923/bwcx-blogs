package utils;

import net.coobird.thumbnailator.Thumbnails;

import java.io.IOException;

public class ImageZipTest {

    public static void main(String[] args) throws IOException {
        String path="/Users/wangyuliang/Desktop/Fig.SD3_T1.t2m.global.2020070108.png";
        String targetFilePath="/Users/wangyuliang/Desktop/target.png";
        Thumbnails.of(path).scale(0.6f).toFile(targetFilePath);
    }
}
