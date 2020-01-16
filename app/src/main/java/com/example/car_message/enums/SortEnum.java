package com.example.car_message.enums;

public enum SortEnum {
  DEFAULT("默认","0"),
  PRICE_ASC("价格正序","1"),
  PRICE_DESC("价格倒序","2"),
  SALE_ASC("销量正序","3"),
  SALE_DESC("销量倒序","4");

  private String name;
  private String code;

  SortEnum(String name, String code) {
    this.name = name;
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }
}
