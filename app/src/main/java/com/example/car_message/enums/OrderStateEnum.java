package com.example.car_message.enums;


public enum OrderStateEnum {

  WAIT_PAY("0", "等待买家付款"),
  WAIT_SEND_GOOD("1", "等待卖家发货"),
  WAIT_SEND_GOOD_TWO("2", "等待卖家发货"),
  WAIT_RECEIVER_GOOD("3", "卖家已发货"),
  ORDER_COMPLETE("4", "交易成功"),
  CANCEL_SUCCESS("5","交易关闭"),
  ORDER_HAVE_COMPELETE("6", "交易成功");


  private String orderStateCode;
  private String orderStateName;

  OrderStateEnum(String orderStateCode, String orderStateName) {
    this.orderStateCode = orderStateCode;
    this.orderStateName = orderStateName;
  }


  public String getOrderStateCode() {
    return orderStateCode;
  }

  public void setOrderStateCode(String orderStateCode) {
    this.orderStateCode = orderStateCode;
  }

  public String getOrderStateName() {
    return orderStateName;
  }

  public void setOrderStateName(String orderStateName) {
    this.orderStateName = orderStateName;
  }
  /**
   * 根据code获取对应的状态名称
   * @param code  code
   * @return name
   */
  public static String LoadOrderStateName(String code) {
    if (WAIT_PAY.getOrderStateCode().equals(code)) {
      return WAIT_PAY.getOrderStateName();
    } else if (WAIT_SEND_GOOD.getOrderStateCode().equals(code)) {
      return WAIT_SEND_GOOD.getOrderStateName();
    } else if (WAIT_SEND_GOOD_TWO.getOrderStateCode().equals(code)) {
      return WAIT_SEND_GOOD_TWO.getOrderStateName();
    } else if (WAIT_RECEIVER_GOOD.getOrderStateCode().equals(code)) {
      return WAIT_RECEIVER_GOOD.getOrderStateName();
    } else if (ORDER_COMPLETE.getOrderStateCode().equals(code)) {
      return ORDER_COMPLETE.getOrderStateName();
    } else if (CANCEL_SUCCESS.getOrderStateCode().equals(code)) {
      return CANCEL_SUCCESS.getOrderStateName();
    } else if (ORDER_HAVE_COMPELETE.getOrderStateCode().equals(code)) {
      return ORDER_HAVE_COMPELETE.getOrderStateName();
    } else {
      return "";
    }
  }
}
