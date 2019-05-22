package com.tourism.utils;

import cn.hengqin.utils.ConfigData;
import cn.hengqin.utils.Crypt;
import cn.hengqin.utils.CryptString;
import cn.hengqin.utils.TimeUtil;

/**
 * =可配置的数据，在AgentRest的构造函数读取property文件
 * 
 * @author 张贵明
 * @since 2014-9-1
 * 
 */
public class CommonData {

	

    public static String getudb3Des(String returnURL){
        try {
            String srcSsDeviceNo = ConfigData.getSrcSsDeviceNo();
            String key = ConfigData.getKey();
            String destValue = ConfigData.getDestValue();
            
            String time = TimeUtil.getNowTime();
            String display = "page";
            String digestString = srcSsDeviceNo + time + returnURL + display;
            String digest = CryptString.buildDigest(digestString);
            String srcString = time+"$"+returnURL+"$"+ display + "$"+digest;
            return Crypt.urlEncode(srcSsDeviceNo+"$"+CryptString.bulidEncrypyString(destValue, key, srcString));
        } catch (Exception e) {
        	e.printStackTrace();
        	return null;
        }
    }
}

