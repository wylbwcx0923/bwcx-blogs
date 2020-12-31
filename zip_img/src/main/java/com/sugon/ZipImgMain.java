package com.sugon;

import com.sugon.runnable.PicUtils;
import com.sugon.util.LinuxCmdUtils;

import java.io.File;

public class ZipImgMain {

   /* public static void main(String[] args) {
        String pathDir = File.separator
                + "opt"
                + File.separator
                + "gridview"
                + File.separator
                + "gridview_resource"
                + File.separator
                + "meteorological"
                + File.separator
                + "FIG";
        File fileDir = new File(pathDir);
        if (!fileDir.exists()) {
            return;
        }
        //文件夹一定存在
        String[] dateTimeFileNames = fileDir.list();
        if (dateTimeFileNames != null && dateTimeFileNames.length > 0) {
            Arrays.sort(dateTimeFileNames);
            //获取到最新的文件夹
            String baseNewDirName = pathDir
                    + File.separator
                    + dateTimeFileNames[dateTimeFileNames.length - 1] + File.separator + "stage6";
            ZipThread.fileZip(new File(baseNewDirName));
            ZipThread.shutdown();
        }
    }*/

    public static void main(String[] args) {
        String originalPath = args[0];
        String zipPath = args[1];

        File file = new File(originalPath);
//        File zipFile = new File(zipPath);
//        if (zipFile.exists()) {
//            System.out.println("文件" + zipPath + "已经存在了,不会重复压缩!");
//            return;
//        }
        try {
            if (file.length() > 500 * 1024) {
                PicUtils.commpressPicForScale(originalPath
                        , zipPath
                        , 400
                        , 0.8
                        , 1920
                        , 1080);
            } else {
                //如果文件小于500K.那么就直接复制原来的文件,保证ZIP开头的文件是存在的
                String cpCmd = "cp " + file.getAbsolutePath() + " " + zipPath;
                boolean flag = LinuxCmdUtils.executeLinuxCmd(cpCmd);
                if (flag) {
                    System.out.println("文件" + file.getAbsolutePath() + "小于500k,直接复制成功");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
