package com.sugon.thread;

import com.sugon.runnable.ZipRunnable;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ZipThread {
    private final static int THREAD_COUNT = 30;
    private static final ExecutorService service = Executors.newFixedThreadPool(THREAD_COUNT);

    public static void fileZip(File fileArgs) {
        if (!fileArgs.isDirectory()) {
            String filePath = fileArgs.getAbsolutePath();
            if (filePath.endsWith(".png") || filePath.endsWith(".jpg")) {
                //传去的是原图
                if (!fileArgs.getName().startsWith("ZIP_IMG_")) {
                    ZipRunnable zipRunnable = new ZipRunnable(fileArgs);
                    service.submit(zipRunnable);
                }
            }

        } else {
            File[] files = fileArgs.listFiles();
            assert files != null;
            for (File f : files) {
                fileZip(f);
            }
        }
    }

    public static void shutdown() {
        service.shutdown();
    }
}
