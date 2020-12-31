import utils.Picdownload;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class StringTest {

    public static void main(String[] args) {
        String path = "/Users/wangyuliang/Desktop/Fig.SD3_T1.w10m.Asia.2020051601.png";
        try {
//             Image croppedImage = getCroppedImage(path);
//            ImageIO.write((RenderedImage) croppedImage, "png", new File(path));
            File file = new File(path);
            Picdownload.compressPictureByQality(file, 0.5f);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private static Image getCroppedImage(String address) throws IOException {
        BufferedImage source = ImageIO.read(new File(address));

        boolean flag = false;
        int upperBorder = -1;
        do {
            upperBorder++;
            for (int c1 = 0; c1 < source.getWidth(); c1++) {
                if (source.getRGB(c1, upperBorder) != Color.white.getRGB()) {
                    flag = true;
                    break;
                }
            }

            if (upperBorder >= source.getHeight())
                flag = true;
        } while (!flag);

        BufferedImage destination = new BufferedImage(source.getWidth(), source.getHeight() - upperBorder, BufferedImage.TYPE_INT_ARGB);
        destination.getGraphics().drawImage(source, 0, upperBorder * -1, null);

        return destination;
    }


    public void targetZoomOut(String sourcePath) { //将目标图片缩小成256*256并保存
        File file1 = new File(sourcePath); //用file1取得图片名字
        String name = file1.getName();

        try {
            BufferedImage input = ImageIO.read(file1);
            Image big = input.getScaledInstance(256, 256, Image.SCALE_DEFAULT);
            BufferedImage inputbig = new BufferedImage(256, 256, BufferedImage.TYPE_INT_BGR);
            inputbig.getGraphics().drawImage(input, 0, 0, 256, 256, null); //画图


            File file2 = new File("C:/imageSort/targetPIC"); //此目录保存缩小后的关键图
            if (file2.exists()) {
                System.out.println("多级目录已经存在不需要创建！！");
            } else {
                //如果要创建的多级目录不存在才需要创建。
                file2.mkdirs();
            }
            ImageIO.write(inputbig, "jpg", new File("C:/imageSort/targetPIC/" + name)); //将其保存在C:/imageSort/targetPIC/下
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
