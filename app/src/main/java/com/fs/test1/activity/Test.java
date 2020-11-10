package com.fs.test1.activity;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Test {


    public static void main(String[] args) {
        test();
    }

    public static void test() {

        byte[] bytes = new byte[1024 * 1024];
        try {
            URL url = new URL("https://dldir1.qq.com/weixin/Windows/WeChatSetup.exe");
            URLConnection connection = url.openConnection();
//            BufferedInputStream inputStream  = new BufferedInputStream(connection.getInputStream());
            InputStream inputStream = connection.getInputStream();
            File file = new File("weixin.exe");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024 * 1024);
            int len = 0;
            while ((len = inputStream.read(bytes)) != -1) {
//                fileOutputStream.write(bytes, 0, len);
                byteArrayOutputStream.write(bytes, 0, len);
            }
            fileOutputStream.write(byteArrayOutputStream.toByteArray());
            byteArrayOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
