package com.example.demo.springEvent;

import lombok.Getter;

import java.io.Serializable;

@Getter
public enum EnumUserOperate implements Serializable {

    ADD("add", 0, "新增"),
    UPDATE("update", 1, "更新"),
    DELETE("delete", 2, "删除");

    EnumUserOperate(String name, Integer value, String desc) {
        this.name = name;
        this.value = value;
        this.desc = desc;
    }

    private String name;
    private Integer value;
    private String desc;

//    public static EnumUserOperate getByValue(String value) {
//        for (EnumUserOperate e : values()) {
//            if (e.getValue().equals(value)) {
//                return e;
//            }
//        }
//        return null;
//    }

}
