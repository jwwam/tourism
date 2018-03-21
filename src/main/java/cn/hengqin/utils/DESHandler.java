package cn.hengqin.utils;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class DESHandler {
 private static String strDefaultKey = "national";


 private Cipher encryptCipher = null;

 private Cipher decryptCipher = null;

 /**
  * 将byte数组转换为表示16进制值的字符串， 如：byte[]{8,18}转换
  * 为：0813， 和public static byte[]
  * hexStr2ByteArr(String strIn) 互为可逆的转换过程
  *
  * @param arrB
  *            需要转换的byte数组
  * @return 转换后的字符串
  * @throws Exception
  *             本方法不处理任何异常，所有异常全部抛出
  */

 /**
  * 默认构造方法，使用默认密钥
  *
  * @throws Exception
  */


 /**
  * 指定密钥构造方法
  *
  * @param strKey
  *            指定的密钥
  * @throws Exception
  */
 public DESHandler() throws Exception {
  Security.addProvider(new com.sun.crypto.provider.SunJCE());
   //Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
 }

    private Key key ;

   private String algorithm ="DESede/CBC/PKCS5Padding";


    private byte[] desInitValue;

    public byte[] getDesInitValue() {
        return desInitValue;
    }

    public void setDesInitValue(byte[] desInitValue) {
        this.desInitValue = desInitValue;
    }



    public void setKey(Key key) {
        this.key = key;
    }
 /**
  * 加密字节数组
  *
  * @param arrB
  *            需加密的字节数组
  * @return 加密后的字节数组
  * @throws Exception
  */
 public byte[] encrypt(byte[] arrB) throws Exception {
  IvParameterSpec ivspec = new IvParameterSpec(desInitValue);
   encryptCipher= Cipher.getInstance(algorithm);
   encryptCipher.init(Cipher.ENCRYPT_MODE,  key,ivspec);

  //encryptCipher = Cipher.getInstance("DES");
  //encryptCipher.init(Cipher.ENCRYPT_MODE, key);

  return encryptCipher.doFinal(arrB);
 }

 /**
  * 加密字符串
  *
  * @param strIn
  *            需加密的字符串
  * @return 加密后的字符串
  * @throws Exception
  */
 public String encrypt(String strIn) throws Exception {
  IvParameterSpec ivspec = new IvParameterSpec(desInitValue);
   encryptCipher= Cipher.getInstance(algorithm);
   encryptCipher.init(Cipher.ENCRYPT_MODE,  key, ivspec);
   return HexUtils.toHexString(encrypt(strIn.getBytes()));
  //return ByteCodeConvert.byteArr2HexStr(encrypt(strIn.getBytes()));
 }

 /**
  * 解密字节数组
  *
  * @param arrB
  *            需解密的字节数组
  * @return 解密后的字节数组
  * @throws Exception
  */
 public byte[] decrypt(byte[] arrB) throws Exception {
       IvParameterSpec ivspec = new IvParameterSpec(desInitValue);
     decryptCipher= Cipher.getInstance(algorithm);
  decryptCipher.init(Cipher.DECRYPT_MODE, key,ivspec);
  return decryptCipher.doFinal(arrB);
 }

 /**
  * 解密字符串
  *
  * @param strIn
  *            需解密的字符串
  * @return 解密后的字符串
  * @throws Exception
  */
 public String decrypt(String strIn) throws Exception {
//     IvParameterSpec ivspec = new IvParameterSpec(desInitValue);
//   decryptCipher= Cipher.getInstance(algorithm);
//   decryptCipher.init(Cipher.DECRYPT_MODE,  key, ivspec);
//     decryptCipher = Cipher.getInstance("DES");
//  decryptCipher.init(Cipher.DECRYPT_MODE, key);

  return new String(decrypt(HexUtils.fromHexString(strIn)));
 }

 /**
  * 从指定字符串生成密钥，密钥所需的字节数组长度为8位 不足8位时后面补0，超出8位只取前8位
  *
  * @param arrBTmp
  *            构成该字符串的字节数组
  * @return 生成的密钥
  * @throws java.lang.Exception
  * 3DES 也就是 DESede 的密钥由于需要经过 3 次的 DES 处理，因此长度应该得是 24 个字节，并不是一个字节串。
  */
 public static byte[] HexStringToByteArray(String s) {
		byte[] buf = new byte[s.length() / 2];
		for (int i = 0; i < buf.length; i++) {
			buf[i] = (byte) (chr2hex(s.substring(i * 2, i * 2 + 1)) * 0x10 + chr2hex(s
					.substring(i * 2 + 1, i * 2 + 2)));
		}
//                System.out.println("buf:"+buf.length);
		return buf;
	}
 private static byte chr2hex(String chr) {
		if (chr.equals("0")) {
			return 0x00;
		} else if (chr.equals("1")) {
			return 0x01;
		} else if (chr.equals("2")) {
			return 0x02;
		} else if (chr.equals("3")) {
			return 0x03;
		} else if (chr.equals("4")) {
			return 0x04;
		} else if (chr.equals("5")) {
			return 0x05;
		} else if (chr.equals("6")) {
			return 0x06;
		} else if (chr.equals("7")) {
			return 0x07;
		} else if (chr.equals("8")) {
			return 0x08;
		} else if (chr.equals("9")) {
			return 0x09;
		} else if (chr.equals("A")) {
			return 0x0a;
		} else if (chr.equals("B")) {
			return 0x0b;
		} else if (chr.equals("C")) {
			return 0x0c;
		} else if (chr.equals("D")) {
			return 0x0d;
		} else if (chr.equals("E")) {
			return 0x0e;
		} else if (chr.equals("F")) {
			return 0x0f;
		}
		return 0x00;
	}

 public  Key getKey(String str)
 {
     SecretKeySpec myKey = new SecretKeySpec(HexStringToByteArray(str),"DESede");
     return  myKey;
 }

// public Key getKey2(String str) throws NoSuchAlgorithmException{
//     KeyGenerator kg=KeyGenerator.getInstance("DESede");
// 
// }
 

}
