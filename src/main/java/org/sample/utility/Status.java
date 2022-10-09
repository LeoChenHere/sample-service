package org.sample.utility;

import lombok.Getter;

@Getter
public enum Status implements IBasic{
    Active(0, "Active"),
    Disable(1, "Disable"),
    Delete(999, "Delete");

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
