package org.sample.utility;

import lombok.Getter;

@Getter
public enum DataType implements IBasic{
    AboutUs(0, "AboutUs"),
    UserRules(1, "UserRules"),
    PrivacyPolicy(999, "PrivacyPolicy");

    private int code;
    private String msg;

    DataType(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static void main(String[] args){
        for( DataType type : DataType.values() ){
            System.out.println(type.getMsg());
        }
    }

}
