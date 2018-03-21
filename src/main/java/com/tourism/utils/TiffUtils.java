package com.tourism.utils;

import java.io.File;
import java.io.InputStream;

/**
 * Created by ppm on 2017/9/28.
 */
public class TiffUtils {
    public static void cmdConvert(String dirName,String batPath,String srcPath,String newPath) {
        try {
           //Thread.sleep(30000);
            File dir = new File(dirName);
            String command = batPath + " " + srcPath + " " + newPath;
            Runtime r = Runtime.getRuntime();
            Process p = r.exec(command, null, dir);
            InputStream inputStream = p.getInputStream();
            byte[] by = new byte[1024];
            if ((new String(by, "utf-8")).contains("echo success")) {
                System.out.print("bat is success");
            }
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        public static  void main(String args[]){
/*        try {
            File dir = new File("D:\\");
            String command ="D:\\hengqingliangye\\hq-fx\\hq\\hq-fx\\src\\main\\resources\\IrfanView.bat C:\\mysoftware\\test\\test\\00004413000000000000064001\\2.tif C:\\mysoftware\\test\\test\\00004413000000000000064001\\2.jpeg";
            Runtime r = Runtime.getRuntime();
            Process p = r.exec(command, null, dir);
            InputStream inputStream = p.getInputStream();
            byte[] by = new byte[1024];
            if((new String(by,"utf-8")).contains("echo success")){
                System.out.print("bat is success");
            }
            inputStream.close();*/
            try {
                File dir = new File("C:\\");
                String command = "C:\\Users\\ppm\\Desktop\\tiff\\IrfanView.bat D:\\2.tif D:\\93.jpeg";
                Runtime r = Runtime.getRuntime();
                Process p = r.exec(command, null, dir);
            } catch (Exception e) {
                e.printStackTrace();
            }


    }


}
