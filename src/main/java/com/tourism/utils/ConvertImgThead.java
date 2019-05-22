package com.tourism.utils;

/**
 * Created by ppm on 2017/9/29.
 */
public class ConvertImgThead implements Runnable {
     public  static String dirName;
    public  static String batPath;
    public  static String srcPath;
    public  static String  newPath;
    @Override
    public void run() {
        TiffUtils.cmdConvert(dirName,batPath,srcPath,newPath);
    }
}
