/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cn.hengqin.utils;

/**
 *
 * @author yuanweilin
 */
public class ConfigData {

    private static String passportLogin = "";
    private static String key = "";
    private static String destValue = "";
    private static String SrcSsDeviceNo = "";
    private static String test = "";
    private static String passportLogout = "";
    private static String returnURL="";

    public static String getReturnURL() {
        return returnURL;
    }

    public static void setReturnURL(String returnURL) {
        ConfigData.returnURL = returnURL;
    }
    
    

    public static String getPassportLogout() {
        return passportLogout;
    }

    public static void setPassportLogout(String passportLogout) {
        ConfigData.passportLogout = passportLogout;
    }



    public static String getSrcSsDeviceNo() {
        return SrcSsDeviceNo;
    }

    public static void setSrcSsDeviceNo(String SrcSsDeviceNo) {
        ConfigData.SrcSsDeviceNo = SrcSsDeviceNo;
    }

    public static String getDestValue() {
        return destValue;
    }

    public static void setDestValue(String destValue) {
        ConfigData.destValue = destValue;
    }

    public static String getKey() {
        return key;
    }

    public static void setKey(String key) {
        ConfigData.key = key;
    }

    public static String getPassportLogin() {
        return passportLogin;
    }

    public static void setPassportLogin(String passportLogin) {
        ConfigData.passportLogin = passportLogin;
    }

    public static String getTest() {
        return test;
    }

    public static void setTest(String test) {
        ConfigData.test = test;
    }





}
