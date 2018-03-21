/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cn.hengqin.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author yuanweilin
 */
public class TimeUtil {

    //获取当前时间：yyyy-MM-dd HH:mm:ss
    public static String getNowTime(){
        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simple.format(new Date());
    }


    public static void main(String args[]){
        System.out.println("Time:"+getNowTime());
    }


}
