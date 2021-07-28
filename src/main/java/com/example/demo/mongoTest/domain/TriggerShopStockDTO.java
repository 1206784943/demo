package com.example.demo.mongoTest.domain;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "trigger_shop_stock")
public class TriggerShopStockDTO {

    public static final String FIELD_STATUS = "status";

    @Id
    private ObjectId id;
    private String status;


}
