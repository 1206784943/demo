package com.example.demo.easyExcel.pojo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ClassName UserEntity
 * @Author soulmate
 * @Version V1.0.0
 * @CreateTime 2021-02-07 18:23:36
 * @Description TODO
 */

@Data
@NoArgsConstructor
public class UserEntity {

    /**
     * @ColumnWidth(value = 10) 列宽度
     * @ExcelProperty(value = "id列标题", index = 0) value是列标题，index是列下标，默认从0开始
     */
    @ColumnWidth(value = 10)
    @ExcelProperty(value = "id", index = 0)
    private Integer id;

    @ColumnWidth(value = 15)
    @ExcelProperty(value = "姓名", index = 1)
    private String name;

    @ColumnWidth(value = 10)
    @ExcelProperty(value = "年龄", index = 2)
    private Integer age;

    @ColumnWidth(value = 30)
    @ExcelProperty(value = "电子邮件", index = 3)
    private String email;

    @ColumnWidth(value = 26)
    @ExcelProperty(value = "个性签名", index = 4)
    private String sign;

    @ColumnWidth(value = 20)
    @ExcelProperty(value = "修改时间", index = 5)
    private Date updateTime;

    @ColumnWidth(value = 20)
    @ExcelProperty(value = "新增时间", index = 6)
    private Date createTime;

    /**
     * @ExcelIgnore 表示忽略该字段不导出，默认是会被导出的
     */
    @ExcelIgnore
    private String other;
}
