package com.example.demo.enumClass;

public enum StrategyType {
    STRATEGY_A(1, "策略A"),
    STRATEGY_B(2, "策略B");

    private int code;
    private String desc;

    private StrategyType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    public int getCode() {
        return code;
    }
    public String getDesc() {
        return desc;
    }

    public static void main(String[] args) {
        System.out.println(StrategyType.STRATEGY_A.code);
    }
}
