package com.ji;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * ClassName: Test
 * Description:
 * date: 2019/5/16 10:15
 *
 * @author ji
 * @version 1.0
 * @since JDK 1.8
 */
public class Test {
    private static final String key0 = "FECAG()*&<MNCXZPKL";
    private static final Charset charset = Charset.forName("UTF-8");
    private static byte[] keyBytes = key0.getBytes(charset);
    @org.junit.Test
    public void Test1(){
        try {
            //=================客户端=================
            //hello, i am infi, good night!加密
            String message = "hello, i am infi, good night!";
            //生成AES秘钥，并Base64编码
            String base64Str = AESUtil.genKeyAES();
            System.out.println("AES秘钥Base64编码:" + base64Str);
            //将Base64编码的字符串，转换成AES秘钥
            SecretKey aesKey = AESUtil.loadKeyAES(base64Str);
            //加密
            byte[] encryptAES = AESUtil.encryptAES(message.getBytes(), aesKey);
            //加密后的内容Base64编码
            String byte2Base64 = AESUtil.byte2Base64(encryptAES);
            System.out.println("加密并Base64编码的结果：" + byte2Base64);


            //##############	网络上传输的内容有Base64编码后的秘钥 和 Base64编码加密后的内容		#################


            //===================服务端================
            //将Base64编码的字符串，转换成AES秘钥
            SecretKey aesKey2 = AESUtil.loadKeyAES(base64Str);
            //加密后的内容Base64解码
            byte[] base642Byte = AESUtil.base642Byte(byte2Base64);
            //解密
            byte[] decryptAES = AESUtil.decryptAES(base642Byte, aesKey2);
            //解密后的明文
            System.out.println("解密后的明文: " + new String(decryptAES));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @org.junit.Test
    public void test() throws IOException {
        /*final BASE64Encoder encoder = new BASE64Encoder();
        final BASE64Decoder decoder = new BASE64Decoder();
        final String text = "字串文字6464";
        final byte[] textByte = text.getBytes("UTF-8");
//编码
        final String encodedText = encoder.encode(textByte);
        System.out.println(encodedText);
//解码
        System.out.println(new String(decoder.decodeBuffer(encodedText), "UTF-8"));*/
        String code= "test";
        String encode = encode(code);
        System.out.println(encode);
    }
    public static String encode(String enc){
        byte[] b = enc.getBytes(charset);
        for(int i=0,size=b.length;i<size;i++){
            for(byte keyBytes0:keyBytes){
                b[i] = (byte) (b[i]^keyBytes0);
            }
        }
        return new String(b);
    }

    public static String decode(String dec){
        byte[] e = dec.getBytes(charset);
        byte[] dee = e;
        for(int i=0,size=e.length;i<size;i++){
            for(byte keyBytes0:keyBytes){
                e[i] = (byte) (dee[i]^keyBytes0);
            }
        }
        return new String(e);
    }
}
