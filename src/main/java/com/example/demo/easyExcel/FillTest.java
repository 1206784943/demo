package com.example.demo.easyExcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.enums.WriteDirectionEnum;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.alibaba.excel.write.metadata.fill.FillWrapper;
import com.example.demo.easyExcel.pojo.FillData;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Ignore
public class FillTest {
    private List<FillData> data() {
        List<FillData> list = ListUtils.newArrayList();
        for (int i = 0; i < 10; i++) {
            FillData fillData = new FillData();
            list.add(fillData);
            fillData.setName("张三" + i);
            fillData.setNumber(i);
            fillData.setDate(new Date());
        }
        return list;
    }
    
    @Test
    public void simpleWrite() {
        // 存储位置
        String target = "C:\\Users\\work\\Desktop\\write.xlsx";
        // 模板位置
        String templete = ClassLoader.getSystemResource("excelTemplate/optionTemplate.xlsx").getPath();
        // 写法2
        // 指定用哪个class去写
        ExcelWriter excelWriter = EasyExcel.write(target, FillData.class).withTemplate(templete).build();
        WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
        excelWriter.write(data(), writeSheet);
        // 关闭流
        excelWriter.finish();
    }
    
    @Test
    public void fuzaWrite() {
        // 存储位置
        String target = "C:\\Users\\work\\Desktop\\write.xlsx";
        // 模板位置
        String templete = ClassLoader.getSystemResource("excelTemplate/optionTemplate.xlsx").getPath();
        // 创建工作簿
        ExcelWriter excelWriter = EasyExcel.write(target, FillData.class).withTemplate(templete).build();
        // 创建sheet
        WriteSheet writeSheet = EasyExcel.writerSheet().build();
        // list后创建一行
        FillConfig fillConfig = FillConfig.builder().forceNewRow(Boolean.TRUE).build();
        // 横着排
        FillConfig horizontalConfig = FillConfig.builder().direction(WriteDirectionEnum.HORIZONTAL).build();
        // 工作簿填充数据
        // 第二题
        excelWriter.fill(new FillWrapper("data1", data()), fillConfig, writeSheet);
        // 第三题
        excelWriter.fill(new FillWrapper("data2", data()), fillConfig, writeSheet);
        // 第四题
        excelWriter.fill(new FillWrapper("data3", data()), fillConfig, writeSheet);
        // 第五题
        excelWriter.fill(new FillWrapper("data4", data()), fillConfig, writeSheet);
        // 补充数据
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", new Date());
        map.put("total", 1000);
        map.put("total2", 2000);
        map.put("total3", 3000);
        map.put("total4", 4000);
        excelWriter.fill(map, writeSheet);
        // 关闭流
        excelWriter.finish();
    }
}
