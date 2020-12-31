import net.coobird.thumbnailator.Thumbnails;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class FileTest {
    public static void main(String[] args) {
        String dirPath = "/Users/wangyuliang/fsdownload/2020070108";
        traverseFolder(dirPath);

    }

    private static void traverseFolder(String dirPath) {
        File dirFile = new File(dirPath);
        if (!dirFile.exists() || Objects.requireNonNull(dirFile.listFiles()).length == 1) {
            return;
        }
        //文件一定存在
        File[] files = dirFile.listFiles();

        assert files != null;
        Arrays.stream(files).forEach(file -> {
            if (file.isDirectory()) {
                traverseFolder(file.getAbsolutePath());
            } else {
                String imagePath = file.getAbsolutePath();
                String targetFilePath = file.getAbsolutePath().replace("2020070108", "target");
                try {
                    //取到文件夹的名字
                    String targetDirPath = targetFilePath.replace(file.getName(), "");
                    File targetDir = new File(targetDirPath);
                    if (!targetDir.exists()) {
                        //先创建出文件夹
                        boolean mkdirs = targetDir.mkdirs();
                        if (mkdirs) {
                            File targetFile = new File(targetFilePath);
                            targetFile.createNewFile();
                        }
                    }
                    Thumbnails.of(imagePath).scale(0.5f).toFile(targetFilePath);
                    System.out.println(imagePath + "压缩成功");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
