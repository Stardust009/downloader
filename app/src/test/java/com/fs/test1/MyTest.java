package com.fs.test1;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

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

            }
//            fileOutputStream.write(byteArrayOutputStream.toByteArray());
//            byteArrayOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void myTest() {

        String s  ="";
    }

}
