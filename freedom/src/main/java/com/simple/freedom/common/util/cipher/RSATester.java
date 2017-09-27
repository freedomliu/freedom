package com.simple.freedom.common.util.cipher;
import java.util.Map;  
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class RSATester {  
	
    static String privateKey="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKTuxfp4AL4hpl5kiz/2VsXkBFLLO5Z4SkXn0dLUBkAbx6dQMO2JwvG5KkReGooke0xIwzfe68nTi26dR0nhRuJYImP8RZFRlx4H8WnoN+KCy1e/FiDZMqi/Zxx3D6wrjCXr2i803+l6U44I+14XRjIN997W+9+YOKRJawdvtS+nAgMBAAECgYA102bxEvDLHBg7ffyj5zTyaDOxAXp05NT4c2tarq9LhMVnqRgOrYLdeFx+62cl5joG70gml63DtDIw60s0pTyIYyV+4AaoUsxut6RozfuRzrv2+442HYtF+phHi3EcLN41HmqDigNjYP9Ecl/2yEqbeORMe4Y+1ZOz6bFxUqTB4QJBAOEXnrqmVTDwTnG2BQC7gjLAl4GrbGDCEib6OVpcsBDOsNcf4uNK7qq1WWXs/X8Fsv21Aql60tKy4weBonRdn+UCQQC7lHEFnQdA6XAeW4/3FuD6tBKL+B1TIHKFfJz5pScfWwuyDpYv46Agc3tVAt93cHjkshAH74XCedDNBuYO2eCbAkBM7mS6yGSvgEJfTOWy1WNvczGVCQDWpR3NIpJ9ltY6OFpqfCRwqMifobG3S2NsiYcefUI2qJrrlmZPx3e9q84dAkBXIBN+7/i2hba14wFzWwUUbR9QM+Yq/h8zMTYYHkWVosxD39KSW7+8UucuDS+OYodEchBQH0iZk8sP7+xbaYeRAkEAyrQftmY+CcTliBafUIgv/7fJSXunvXHOLONfhIPUzjrl2oyh34bTRnm1llSaJLZi6LtdbPo26Pmd0uKbIPrFKQ==";  
    static String publicKey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCk7sX6eAC+IaZeZIs/9lbF5ARSyzuWeEpF59HS1AZAG8enUDDticLxuSpEXhqKJHtMSMM33uvJ04tunUdJ4UbiWCJj/EWRUZceB/Fp6DfigstXvxYg2TKov2ccdw+sK4wl69ovNN/pelOOCPteF0YyDffe1vvfmDikSWsHb7UvpwIDAQAB";  
  
    /*static {  
        try {  
            Map<String, Object> keyMap = RSAUtils.genKeyPair();  
            publicKey = RSAUtils.getPublicKey(keyMap);  
            privateKey = RSAUtils.getPrivateKey(keyMap);  
            System.err.println("公钥: \n\r" + publicKey);  
            System.err.println("私钥： \n\r" + privateKey);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  */
      
    public static void main(String[] args) throws Exception {  
        //test();  
        testSign();  
    }  
  
    static void test() throws Exception {  
        System.err.println("公钥加密——私钥解密");  
        String source = "639ccd0e46783d720f0a18dd900fb35e36a3058c";  
        System.out.println("\r加密前文字：\r\n" + source);  
        byte[] data = source.getBytes();  
        byte[] encodedData = RSAUtils.encryptByPublicKey(data, publicKey);  
        System.out.println("加密后文字：\r\n" + new String(encodedData));  
        byte[] decodedData = RSAUtils.decryptByPrivateKey(encodedData, privateKey);  
        String target = new String(decodedData);  
        System.out.println("解密后文字: \r\n" + target);  
    }  
  
    static void testSign() throws Exception {  
        System.err.println("私钥加密——公钥解密");  
        String source = "639ccd0e46783d720f0a18dd900fb35e36a3058c";  
        System.out.println("原文字：\r\n" + source);  
        byte[] data = source.getBytes();  
        byte[] encodedData = RSAUtils.encryptByPrivateKey(data, privateKey);  
        System.out.println("加密后：\r\n" + new String(encodedData));  
        System.out.println("加密后：\r\n" + (new BASE64Encoder()).encodeBuffer(encodedData));  
        byte[] decodedData = RSAUtils.decryptByPublicKey(encodedData, publicKey);  
        String target = new String(decodedData);  
        System.out.println("解密后: \r\n" + target);  
        System.err.println("私钥签名——公钥验证签名");  
        String sign = RSAUtils.sign(encodedData, privateKey);  
        System.err.println("签名:\r" + sign);  
        boolean status = RSAUtils.verify(encodedData, publicKey, sign);  
        System.err.println("验证结果:\r" + status);  
    }  
      
}
