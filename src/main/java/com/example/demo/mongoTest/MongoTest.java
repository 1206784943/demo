package com.example.demo.mongoTest;

import com.alibaba.fastjson.JSONArray;
import com.example.demo.mongoTest.domain.TriggerShopStockDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void test(){
        TriggerShopStockDTO triggerShopStockDTO = new TriggerShopStockDTO();
        triggerShopStockDTO.setStatus("PENDING");
        mongoTemplate.insert(triggerShopStockDTO);
        Query query = new Query();
        query.addCriteria(Criteria.where(TriggerShopStockDTO.FIELD_STATUS).is("PENDING"));
        List<TriggerShopStockDTO> triggerShopStockDTOS = mongoTemplate.find(query, TriggerShopStockDTO.class);
        System.out.println("mongo查询结果："+JSONArray.toJSONString(triggerShopStockDTOS));


    }
}
