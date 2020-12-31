package test;

import net.coobird.thumbnailator.Thumbnails;

import java.io.IOException;

public class ThumbnailsTest {

    public static void main(String[] args) throws IOException {
        Thumbnails.of("/Users/wangyuliang/Desktop/WechatIMG69.png")
                .scale(0.2f)
//                .rotate(-90)
                .toFile("/Users/wangyuliang/Desktop/WechatIMG69_copy.png");
    }
}
