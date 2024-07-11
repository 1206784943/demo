package com.example.demo.snowFlake;


import com.github.yitter.contract.IdGeneratorOptions;
import com.github.yitter.idgen.YitIdHelper;

//雪花算法生成唯一id
public class SnowFlakeDemo {
    public static void main(String[] args) {
        IdGeneratorOptions options = new IdGeneratorOptions((short) 1);
        YitIdHelper.setIdGenerator(options);
        for (int i = 0; i < 5; i++) {
            long l = YitIdHelper.nextId();
            System.out.println(l);
        }

    }
}
