package com.fs.test1;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyTest {


    @Test
    public void downloadTest() {

        byte[] bytes = new byte[1024 * 1024];
        try {
            URL url = new URL("http://dl.2345.com/haozip/2345haozip_6.2.0.11032_setup.exe");
            URLConnection connection = url.openConnection();
//            BufferedInputStream inputStream  = new BufferedInputStream(connection.getInputStream());
            InputStream inputStream = connection.getInputStream();
            File file = new File("2345.exe");
            FileOutputStream fileOutputStream = new FileOutputStream(file);

            int len = 0;
            while ((len = inputStream.read(bytes)) != -1) {
                fileOutputStream.write(bytes, 0, len);
                System.out.println(file.length());
            }

            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void myTest() {

        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2, 4, 0L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r);
            }
        });

        poolExecutor.submit(() -> {
            byte[] bytes = new byte[1024 * 1024];
            try {
                URL url = new URL("http://dl.2345.com/haozip/2345haozip_6.2.0.11032_setup.exe");
                URLConnection connection = url.openConnection();
//            BufferedInputStream inputStream  = new BufferedInputStream(connection.getInputStream());
                InputStream inputStream = connection.getInputStream();
                File file = new File("2345.exe");
                FileOutputStream fileOutputStream = new FileOutputStream(file);

                int len = 0;
                while ((len = inputStream.read(bytes)) != -1) {
                    fileOutputStream.write(bytes, 0, len);
                    System.out.println(file.length());
                }

                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }



}
