package com.example.car_message.utils;



import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * 签名扩展工具类
 */
public class SignUtil {


  private static final String SYMBOLS = "0123456789abcdefghigklmnopqrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ";

  private static final Random RANDOM = new SecureRandom();

  /**
   * 验证签名是否正确，使用MD5签名
   */
  public static boolean isSignatureValid(Map<String, String> data, String key) throws Exception {
    if (data.containsKey(SignConstants.FIELD_SIGN_TYPE)) {
      String signType = data.get(SignConstants.FIELD_SIGN_TYPE);
      if (SignConstants.SignType.MD5.name().equals(signType)) {
        return isSignatureValid(data, key, SignConstants.SignType.MD5);
      } else if (SignConstants.SignType.HMACSHA256.name().equals(signType)) {
        return isSignatureValid(data, key, SignConstants.SignType.HMACSHA256);
      }
    }
    return isSignatureValid(data, key, SignConstants.SignType.MD5);
  }

  /**
   * 验证签名是否正确，必须宝行sign字段，否则放回false
   */
  public static boolean isSignatureValid(Map<String, String> data, String key, SignConstants.SignType signType)
      throws Exception {
    if (!data.containsKey(SignConstants.FIELD_SIGN)) {
      return false;
    }
    String sign = data.get(SignConstants.FIELD_SIGN);
    return generateSignature(data, key, signType).equals(sign);

  }

  /**
   * 生成签名 使用MD5
   *
   * @param data 待签名数据
   * @param key API秘钥
   */
  public static String generateSignature(final Map<String, String> data, String key)
      throws Exception {
    return generateSignature(data, key, SignConstants.SignType.MD5);
  }

  /**
   * 生成签名
   *
   * @param data 待签名数据
   * @param key API秘钥
   * @param signType 签名方式
   * @return 签名
   */
  public static String generateSignature(final Map<String, String> data, String key,
                                         SignConstants.SignType signType) throws Exception {
    Set<String> keySet = data.keySet();
    String[] keyArray = keySet.toArray(new String[keySet.size()]);
    Arrays.sort(keyArray);
    StringBuilder sb = new StringBuilder();
    for (String k : keyArray) {
      if (k.equals(SignConstants.FIELD_SIGN)) {
        continue;
      }
      if (data.get(k).trim().length() > 0) {
        sb.append(k).append("=").append(data.get(k).trim()).append("&");
      }
    }
    sb.append(SignConstants.FIELD_SECRET + "=").append(key);
    if (SignConstants.SignType.MD5.equals(signType)) {
      return MD5(sb.toString());
    } else if (SignConstants.SignType.HMACSHA256.equals(signType)) {
      return HMACSHA256(sb.toString(), key);
    } else {
      throw new Exception(String.format("Invalid sign_type: %s", signType));
    }
  }

  /**
   * 生成随机编码
   */
  public static String generateNonceStr() {
    char[] nonceChars = new char[32];
    for (int index = 0; index < nonceChars.length; index++) {
      nonceChars[index] = SYMBOLS.charAt(RANDOM.nextInt(SYMBOLS.length()));
    }
    return new String(nonceChars);
  }

  /**
   * 生成 MD5
   *
   * @param data 待处理数据
   * @return MD5结果
   */
  public static String MD5(String data) throws Exception {
    MessageDigest md = MessageDigest.getInstance("MD5");
    byte[] array = md.digest(data.getBytes("UTF-8"));
    StringBuilder sb = new StringBuilder();
    for (byte item : array) {
      sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
    }
    return sb.toString().toUpperCase();
  }

  /**
   * 生成HMACSHA256
   *
   * @param data 待加密数据
   * @param key 秘钥
   * @return 加密结果
   */
  public static String HMACSHA256(String data, String key) throws Exception {
    Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
    SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
    sha256_HMAC.init(secret_key);
    byte[] array = sha256_HMAC.doFinal(data.getBytes("UTF-8"));
    StringBuilder sb = new StringBuilder();
    for (byte item : array) {
      sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
    }
    return sb.toString().toUpperCase();
  }

  /**
   * 生成 32大写MD5
   *
   * @param sourceStr 待处理数据
   * @return MD5结果
   */
  public static String MD5by32Bits(String sourceStr){
    String result = "";
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      md.update(sourceStr.getBytes());
      byte[] b = md.digest();
      int i ;
      StringBuffer buf = new StringBuffer("");
      for(int offest = 0; offest < b.length;offest++){
        i = b[offest];
        if(i<0)
          i+=256;
        if(i<16)
          buf.append("0");

        buf.append(Integer.toHexString(i));
      }
      result = buf.toString();
    }catch (NoSuchAlgorithmException e){
      System.out.print(e);
    }
    return result.toUpperCase();
  }


  public static String getUuid(){
    UUID uuid = UUID.randomUUID();
    String str = uuid.toString();
    String uuidStr = str.replace("-","");
    return uuidStr;
  }

}
