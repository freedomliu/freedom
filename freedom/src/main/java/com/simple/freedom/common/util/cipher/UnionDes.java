package com.simple.freedom.common.util.cipher;
import java.io.UnsupportedEncodingException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * SecretUtils {3DES加密解密的工具类 }
 * @author William
 * @date 2013-04-19
 */
public class UnionDes {
 
    //定义加密算法，有DES、DESede(即3DES)、Blowfish
    private static final String Algorithm = "DESede";    
    private static final String PASSWORD_CRYPT_KEY = "2012PinganVitality075522628888ForShenZhenBelter075561869839";
    
    
    /**
     * 加密方法
     * @param src 源数据的字节数组
     * @return 
     */
    public static byte[] encryptMode(byte[] src) {
        try {
             SecretKey deskey = new SecretKeySpec(build3DesKey(PASSWORD_CRYPT_KEY), Algorithm);    //生成密钥
             Cipher c1 = Cipher.getInstance(Algorithm);    //实例化负责加密/解密的Cipher工具类
             c1.init(Cipher.ENCRYPT_MODE, deskey);    //初始化为加密模式
             return c1.doFinal(src);
         } catch (java.security.NoSuchAlgorithmException e1) {
             e1.printStackTrace();
         } catch (javax.crypto.NoSuchPaddingException e2) {
             e2.printStackTrace();
         } catch (java.lang.Exception e3) {
             e3.printStackTrace();
         }
         return null;
     }
    
    
    /**
     * 解密函数
     * @param src 密文的字节数组
     * @return
     */
    public static byte[] decryptMode(byte[] src) {      
        try {
            SecretKey deskey = new SecretKeySpec(build3DesKey(PASSWORD_CRYPT_KEY), Algorithm);
            Cipher c1 = Cipher.getInstance(Algorithm);
            c1.init(Cipher.DECRYPT_MODE, deskey);    //初始化为解密模式
            return c1.doFinal(src);
        } catch (java.security.NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (java.lang.Exception e3) {
            e3.printStackTrace();
        }
        return null;
     }
    
    
    /*
     * 根据字符串生成密钥字节数组 
     * @param keyStr 密钥字符串
     * @return 
     * @throws UnsupportedEncodingException
     */
    public static byte[] build3DesKey(String keyStr) throws UnsupportedEncodingException{
        byte[] key = new byte[24];    //声明一个24位的字节数组，默认里面都是0
        byte[] temp = keyStr.getBytes("UTF-8");    //将字符串转成字节数组
        
        /*
         * 执行数组拷贝
         * System.arraycopy(源数组，从源数组哪里开始拷贝，目标数组，拷贝多少位)
         */
        if(key.length > temp.length){
            //如果temp不够24位，则拷贝temp数组整个长度的内容到key数组中
            System.arraycopy(temp, 0, key, 0, temp.length);
        }else{
            //如果temp大于24位，则拷贝temp数组24个长度的内容到key数组中
            System.arraycopy(temp, 0, key, 0, key.length);
        }
        return key;
    } 

	/*
	 * · 3DES的密钥必须是24位的byte数组 随便拿一个String.getBytes()是不行的，会报如下错误
	 * java.security.InvalidKeyException: Invalid key length: 59 bytes
	 * 解决方法有很多，①按密钥固定长度重新定义字符串
	 * ；②先把字符串用Base64或者MD5加密，然后截取固定长度的字符转成byte数组；③字符串转成Byte数组
	 * ，针对该数组进行修改，若长度过长则只截取一部分，若长度不够则补零
	 * 
	 * · 加密结果的编码方式要一致 从byte数组转成字符串，一般有两种方式，base64处理和十六进制处理。
	 * 
	 * · 参考资料 3DES在线测试工具：http://www.seacha.com/tools/3des.php
	 */    
        public static void main(String[] args) throws Exception {
        String msg = "3DES加密解密案例";
        System.out.println("【加密前】：" + msg);
        
        //加密
        byte[] secretArr = UnionDes.encryptMode(msg.getBytes("UTF-8"));    
        System.out.println("【加密后】：" + Base64Utils.encode(secretArr));
        
        //解密
        byte[] myMsgArr = UnionDes.decryptMode(secretArr);  
        System.out.println("【解密后】：" + new String(myMsgArr));
    }
}
