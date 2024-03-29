package com.example.demo;

import com.alibaba.fastjson.JSONArray;
import com.example.demo.mybatis.dto.LargeCount;
import com.example.demo.mybatis.service.LargeCountService;
import com.example.demo.redis.RedisCacheClient;
import com.example.demo.shiro.User;
import com.example.demo.single.SingleObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private LargeCountService largeCountService;
    @Autowired
    private RedisCacheClient redisCacheClient;

    @Test
    public void test11() {

    }

    @Test
    public void test10() {
//        SingleObject object = new SingleObject();
//        object.showMessage();

        SingleObject instance = SingleObject.getInstance();
        instance.showMessage();
    }

    @Test
    public void test9() {
        // 创建一个 HashMap
        HashMap<String, Integer> prices = new HashMap<>();

        // 往HashMap中添加映射关系
        prices.put("Bag", 300);
        prices.put("Pant", 150);

        // Shoes中的映射关系已经存在
        // Shoes并没有计算新值
        prices.computeIfAbsent("Shoes", (key) -> 280);

        // 输出更新后的 HashMap
        System.out.println("Updated HashMap: " + prices);
    }

    @Test
    public void test8(){
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("Tom", 10));
        users.add(new User("Tom2", 20));
        users.add(new User("Tom3", 30));
        users.add(new User("Tom4", 50));
        users.add(new User("Tom4", 40));
        HashMap<String, List<User>> map = new HashMap<>();
        for (User user : users) {
            map.computeIfAbsent(user.getUsername(), k -> Arrays.asList(user));
        }
        System.out.println(JSONArray.toJSONString(map));
    }

    @Test
    public void test7(){
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("Tom", 10));
        users.add(new User("Tom2", 20));
        users.add(new User("Tom3", 30));
        users.add(new User("Tom4", 40));
        users.sort(Comparator.comparing(User::getAge).reversed());
        System.out.println(JSONArray.toJSONString(users));
    }

    @Test
    public void test6() {
        redisCacheClient.boundZSetOps("三国2").add("曹操", 1);
        redisCacheClient.boundZSetOps("三国2").add("曹操2", 2);
        redisCacheClient.boundZSetOps("三国2").add("曹操3", 3);
        redisCacheClient.boundZSetOps("三国2").add("曹操4", 4);
        redisCacheClient.boundZSetOps("三国2").add("曹操5", 5);
        //按照分数正序排序
//        Set<ZSetOperations.TypedTuple<String>> set = redisCacheClient.opsForZSet().rangeByScoreWithScores("三国2", 1, 50, 0, 3);
//        System.out.println(JSONArray.toJSONString(set));
        //按照分数倒序排序
        Set<ZSetOperations.TypedTuple<String>> set1 = redisCacheClient.opsForZSet().reverseRangeByScoreWithScores("三国2", 0, 999);
        System.out.println(JSONArray.toJSONString(set1));
    }

    @Test
    public void test5() {
        if (redisCacheClient.hasKey("三国2")) {
            redisCacheClient.delete("三国2");
        }
        redisCacheClient.boundZSetOps("三国2").add("曹操", 1);
    }

    @Test
    public void test3() {

        redisCacheClient.boundZSetOps("三国2").add("曹操", 1);
        redisCacheClient.boundZSetOps("三国2").add("曹操2", 2);
        redisCacheClient.boundZSetOps("三国2").add("曹操3", 3);
        redisCacheClient.boundZSetOps("三国2").add("曹操4", 4);
        redisCacheClient.boundZSetOps("三国2").add("曹操5", 5);
        //按照分数正序排序
        redisCacheClient.opsForZSet().rangeByScoreWithScores("三国2", 1, 50, 0, 3);
        //按照分数倒序排序
        redisCacheClient.opsForZSet().reverseRangeByScoreWithScores("三国2", 1, 50, 0, 3);

        redisCacheClient.boundSetOps("三国").add("刘备");
        redisCacheClient.boundSetOps("三国").add("孙权");
        redisCacheClient.boundSetOps("三国").members();
        redisCacheClient.boundSetOps("三国").remove("孙权");
        redisCacheClient.delete("三国");

        redisCacheClient.boundListOps("桃园三结义").rightPush("刘备");
        redisCacheClient.boundListOps("桃园三结义").rightPush("关羽");
        redisCacheClient.boundListOps("桃园三结义").rightPush("张飞");
        redisCacheClient.boundListOps("桃园三结义").range(0, 10);
        redisCacheClient.boundListOps("桃园三结义").index(1);
        redisCacheClient.boundListOps("桃园三结义").remove(1, "关羽");

        redisCacheClient.boundHashOps("西游记").put("老大", "唐僧");
        redisCacheClient.boundHashOps("西游记").put("老二", "悟空");
        redisCacheClient.boundHashOps("西游记").put("老三", "八戒");
        redisCacheClient.boundHashOps("西游记").put("老四", "沙僧");
        redisCacheClient.boundHashOps("西游记").keys();
        redisCacheClient.boundHashOps("西游记").values();
        redisCacheClient.boundHashOps("西游记").get("老四");
        redisCacheClient.boundHashOps("西游记").delete("c", "a");
        redisCacheClient.boundHashOps("西游记").delete();

    }

    @Test
    public void contextLoads() {
        String str = "Java string-split#test";
        String[] split = str.split(" |-|#");
        for (String s : split) {
            System.out.println(s);
        }
    }

    @Test
    public void queryLargeCountByIdTest() {
        LargeCount largeCount = largeCountService.queryById(1);
        System.out.println(largeCount);
    }

    @Test
    public void devQuery() {
        Long largeCount = largeCountService.devQuery(4L);
        System.out.println(largeCount);
    }

    @Test
    public void devInsert() {
        Long largeCount = largeCountService.devInsert(4L);
        System.out.println(largeCount);
    }

    @Test
    public void teshuzifu() {
        String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher("花&妈妈mama");
        System.out.println(m.replaceAll("").trim());
    }

    @Test
    public void test() {
        //获取集合全部数据
        List<String> rangetest = redisCacheClient.opsForList().range("rangetest", 0, -1);
        System.out.println(JSONArray.toJSONString(rangetest));
    }
}
