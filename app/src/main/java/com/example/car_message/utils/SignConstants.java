package com.example.car_message.utils;

/**
 * 常量
 */
public class SignConstants {

  public enum SignType {
    MD5, HMACSHA256
  }


  public static final String HMACSHA256 = "HMAC-SHA256";

  public static final String MD5 = "MD5";

  public static final String FIELD_SIGN = "sign";

  public static final String FIELD_SIGN_TYPE = "sign_type";

  public static final String FIELD_APP_KEY = "app_key";

  public static final String FIELD_SECRET = "secret";

  public static final String FIELD_TOKEN = "token";

  public static final String FIELD_NONCE = "nonce_str";

  public static final String FIELD_TIMESTAMP = "timestamp";


}
