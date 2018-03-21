/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hengqin.utils;


/**
 *
 * @author hujiuyou
 */
public class CryptUtil {

    public static String udb3DesCrypt(String str, String key, String destValue) throws Exception {
       //String desInitValue = "12345678";
            //String key = "12345678901234567890123412345678123456781234567j";
            //digestSrcString = "200000000040030120000000000000013f2ab9b4-27eb-4464-b510-372ddaa53a0c2009-12-23 15:15:58";
            DESHandler desHandler = new DESHandler();
            desHandler.setKey(desHandler.getKey(key));
            desHandler.setDesInitValue(HexUtils.strToByte(destValue));
            return Crypt.base64Encode(desHandler.encrypt(Crypt.sha1Digest(str.getBytes())));
    }
    
}
