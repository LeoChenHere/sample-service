package org.sample.utility;

import lombok.Getter;

@Getter
public enum ReturnCode implements IReturnCode{
  OK(0, "Success"),
  ERR_DBFAIL(1000, "Database error"),
  ERR_NETWORK(2000, "Network error");

  private int code;
  private String msg;

  ReturnCode(int code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public static void main(String[] args){
    for( ReturnCode rc : ReturnCode.values() ){
      System.out.println(rc);
    }
  }
}
