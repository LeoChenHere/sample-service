package org.sample.utility;

import lombok.Getter;

@Getter
public enum Status implements IBasic{
    ACTIVE(0, "Active"),
    DISABLE(1, "Disable"),
    DELETE(999, "Delete");

    private int code;
    private String msg;

    Status(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static void main(String[] args){
        for( Status status : Status.values() ){
            System.out.println(status.getMsg());
        }
    }

}
