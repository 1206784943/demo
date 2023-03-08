package com.example.demo.redis;

import com.example.demo.utils.R;
import org.springframework.data.redis.connection.BitFieldSubCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * bitField操作需要高版本的redis(7.0.0),否则报错
 * @Author liujiabin
 * @Date 2023-03-07 10:33
 */
@RestController
public class RedisTestController {

    @Resource
    private RedisTemplate redisTemplate;

    @GetMapping("sign")
    public R sign() {
        //2. 获取日期
        LocalDateTime now = LocalDateTime.now();
        //3. 拼接key
        String keySuffix = now.format(DateTimeFormatter.ofPattern(":yyyyMM"));
        String key = "sign" + keySuffix;
        //4. 获取今天是本月的第几天
        int dayOfMonth = now.getDayOfMonth();
        //5. 写入redis setbit key offset 1
        redisTemplate.opsForValue().setBit(key, 8, true);
        return R.ok();
    }

    @GetMapping("/signCount")
    public R signCount() {
        //2. 获取日期
        LocalDateTime now = LocalDateTime.now();
        //3. 拼接key
        String keySuffix = now.format(DateTimeFormatter.ofPattern(":yyyyMM"));
        String key = "sign" + keySuffix;
        //4. 获取今天是本月的第几天
        int dayOfMonth = now.getDayOfMonth();
        //5. 获取本月截至今天为止的所有的签到记录，返回的是一个十进制的数字 BITFIELD sign:5:202301 GET u3 0
        List<Long> result = redisTemplate.opsForValue().bitField(key,BitFieldSubCommands.create().get(BitFieldSubCommands.BitFieldType.unsigned(dayOfMonth)).valueAt(0));
        //没有任务签到结果
        if (result == null || result.isEmpty()) {
            return R.ok("0");
        }
        Long num = result.get(0);
        if (num == null || num == 0) {
            return R.ok("0");
        }
        //6. 循环遍历
        int count = 0;
        while (true) {
            //6.1 让这个数字与1 做与运算，得到数字的最后一个bit位 判断这个数字是否为0
            if ((num & 1) == 0) {
                //如果为0，签到结束
                break;
            } else {
                count++;
            }
            num >>>= 1;
        }
        return R.ok(String.valueOf(count));
    }

}
