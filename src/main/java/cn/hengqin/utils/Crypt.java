/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cn.hengqin.utils;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *
 * @author yuanweilin
 */
public class Crypt {

    public static String base64Encode(byte[] src){
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(src);
    }

    public static byte[] sha1Digest(byte[] src) throws NoSuchAlgorithmException {
        java.security.MessageDigest msg = java.security.MessageDigest.getInstance("SHA-1");
        msg.update(src);
        return msg.digest();
    }

    /**
     * 对给定字符进行 URL 编码.
     *
     * @param src
     *            String
     * @return String
     */
    public static String urlEncode(String src) {
        try {
            src = java.net.URLEncoder.encode(src, "GB2312");

            return src;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return src;
    }

    public static byte[] base64Decode(String src){
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            return decoder.decodeBuffer(src);
        } catch (IOException ex) {
            Logger.getLogger(Crypt.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }


}
