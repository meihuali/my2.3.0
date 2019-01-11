package com.example.a77299007.myapplication.base.baseBean;


import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.util.Base64;


import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.util.zip.DataFormatException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.Inflater;

public class EncryptUtil {

    private static final int BUFFER_SIZE = 1024;
    private static final String CIPHER_MODE = "AES/CBC/PKCS5Padding";

    /*
     * 加密用的Key 可以用26个字母和数字组成 此处使用AES-128-CBC加密模式，key需要为16位。
     */
    private String sKey = "";
    private String ivParameter = "";


    /**
     * AES加密,结果用base64编码
     *
     * @param encData   加密内容
     * @param secretKey 密钥
     * @param vector    密钥偏移量
     * @return 加密编码后的内容
     * @throws Exception
     */
    public static String encrypt(String encData, String secretKey, String vector) throws Exception {
        if (secretKey == null) {
            return null;
        }
        if (secretKey.length() != 16) {
            return null;
        }
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] raw = secretKey.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        IvParameterSpec iv = new IvParameterSpec(vector.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(encData.getBytes("utf-8"));
        return Base64.encodeToString(encrypted, Base64.NO_WRAP);// 此处使用BASE64做转码。
    }

    public static String encrypt(String content) throws Exception {
        return encrypt(content, Const.secrekey, Const.iv);
    }

    /**
     * AES解密
     *
     * @param sSrc 待解密的内容
     * @param key  密钥
     * @param ivs  密钥偏移量
     * @return 解密后的结果
     * @throws Exception
     */
    public static String decrypt(String sSrc, String key, String ivs) throws Exception {
        try {
            byte[] raw = key.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(ivs.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 = Base64.decode(sSrc, Base64.NO_WRAP);// 先用base64解密
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original, "utf-8");
            return originalString;
        } catch (Exception ex) {
            return null;
        }
    }

    public static String decrypt(String sSrc) throws Exception {
        return decrypt(sSrc, Const.secrekey, Const.iv);
    }


    public static String encryptAES(String content, String password) {
        try {
            SecretKeySpec key = generateKey(password);
            //创建密码器
            Cipher cipher = Cipher.getInstance(CIPHER_MODE);
//            cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(new byte[cipher.getBlockSize()]));
            cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec("0392039203920300".getBytes()));
            byte[] bytes = content.getBytes("UTF-8");
            byte[] result = cipher.doFinal(bytes);
            return Base64.encodeToString(result, Base64.DEFAULT);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decryptAES(String content, String password) {
        try {
            byte[] decode = Base64.decode(content, Base64.DEFAULT);
            SecretKeySpec key = generateKey(password);
            Cipher cipher = Cipher.getInstance(CIPHER_MODE);
            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(new byte[cipher.getBlockSize()]));
            byte[] bytes = cipher.doFinal(decode);
            return new String(bytes, "UTF-8");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        return null;
    }

    @NonNull
    private static SecretKeySpec generateKey(String password) throws NoSuchAlgorithmException, NoSuchProviderException {
        KeyGenerator instance = KeyGenerator.getInstance("AES");
        //防止linux下 随机生成key
        //第二个参数在4.2系统上不能去掉
        @SuppressLint("DeletedProvider") SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG", "Crypto");
        secureRandom.setSeed(password.getBytes());
        instance.init(128, secureRandom);
        SecretKey secretKey = instance.generateKey();
        byte[] encoded = secretKey.getEncoded();
        return new SecretKeySpec(encoded, "AES");
    }

    /**
     * BASE64 加密
     *
     * @param encode
     * @return
     */
    public static byte[] encryptBASE64(byte[] encode) {
        return Base64.encode(encode, 0, encode.length, Base64.NO_WRAP);
    }


    /**
     * BASE64 解密
     *
     * @param encode
     * @return
     */
    public static byte[] decryptBASE64(byte[] encode) {
        return Base64.decode(encode, 0, encode.length, Base64.NO_WRAP);
    }

    /**
     * GZIP 加密
     *
     * @param str
     * @return
     */
    public static byte[] encryptGZIP(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }

        try {
            // gzip压缩 
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            GZIPOutputStream gzip = new GZIPOutputStream(baos);
            gzip.write(str.getBytes("utf-8"));

            gzip.close();

            byte[] encode = baos.toByteArray();

            baos.flush();
            baos.close();

            // base64 加密 
            return encode;
//          return new String(encode, "utf-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * GZIP 解压
     *
     * @param decode
     * @return
     */
    public static String decryptGZIP(byte[] decode) {
        try {

            //gzip 解压缩
            ByteArrayInputStream bais = new ByteArrayInputStream(decode);
            GZIPInputStream gzip = new GZIPInputStream(bais);

            byte[] buf = new byte[BUFFER_SIZE];
            int len = 0;

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            while ((len = gzip.read(buf, 0, BUFFER_SIZE)) != -1) {
                baos.write(buf, 0, len);
            }
            gzip.close();
            baos.flush();

            decode = baos.toByteArray();

            baos.close();

            return new String(decode, "utf-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String unZip(String zipped) throws DataFormatException, IOException {
        byte[] bytes = zipped.getBytes("utf-8");
        Inflater decompressed = new Inflater();
        decompressed.setInput(bytes);

        byte[] result = new byte[100];
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        while (decompressed.inflate(result) != 0)
            buffer.write(result);

        decompressed.end();

        return new String(buffer.toByteArray(), "utf-8");
    }


    /**
     * 十六进制字符串 转换为 byte[]
     *
     * @param hexString the hex string
     * @return byte[]
     */
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    /**
     * Convert char to byte
     *
     * @param c char
     * @return byte
     */
    private static byte charToByte(char c) {
        return (byte) "0123456789abcdef".indexOf(c);
        // return (byte) "0123456789ABCDEF".indexOf(c); 
    }

    /**
     * byte[] 转换为 十六进制字符串
     *
     * @param src
     * @return
     */
    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");

        if (src == null || src.length <= 0) {
            return null;
        }

        for (byte aSrc : src) {
            int v = aSrc & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
}