/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cn.hengqin.utils;

import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**   
 *
 * @author yuanweilin
 */
public class CryptString {

    //创建文摘（digest），使用的是sha-1算法
    public static String buildDigest(String srcString) throws NoSuchAlgorithmException{
        return Crypt.base64Encode(Crypt.sha1Digest(srcString.getBytes()));
    }

    public static String bulidEncrypyString(String destValue,String key,String src) throws Exception{
        DESHandler desHandler = new DESHandler();
        desHandler.setKey(desHandler.getKey(key));
        desHandler.setDesInitValue(HexUtils.strToByte(destValue));
        return Crypt.base64Encode(desHandler.encrypt((src).getBytes()));
    }

    public static String getDecryptString(String src,String desInitValue,String key) throws Exception{
        DESHandler desHandler = new DESHandler();
        desHandler.setDesInitValue(HexUtils.strToByte(desInitValue));
        desHandler.setKey(desHandler.getKey(key));
        return new String(desHandler.decrypt(Crypt.base64Decode(src)));

    }
     public static boolean checkDigest(String digest,String digestString){
        try {
            String digest2 = CryptString.buildDigest(digestString);
            return digest.endsWith(digest2);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CryptString.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
     }
     
     
      public static String bulidAuthenticator(String key,String destInitValue,String encryptString) throws Exception{

          byte[] encryptByte = Crypt.sha1Digest(encryptString.getBytes());
          DESHandler handler = new DESHandler();
          handler.setDesInitValue(HexUtils.strToByte(destInitValue));
          handler.setKey(handler.getKey(key));
         return Crypt.base64Encode(handler.encrypt(encryptByte));
      }

}
