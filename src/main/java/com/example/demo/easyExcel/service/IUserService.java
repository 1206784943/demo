package com.example.demo.easyExcel.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.example.demo.easyExcel.pojo.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.apache.poi.ss.usermodel.BorderStyle.DASHED;

/**
 * @ClassName UserServiceImpl
 * @Author soulmate
 * @Version V1.0.0
 * @CreateTime 2021-02-07 18:24:35
 * @Description TODO
 */

@Slf4j
@Service
public class IUserService {

    /**
     * 构造出user数据
     *
     * @return list
     */
    public List<UserEntity> userEntities() {
        List<UserEntity> list = new ArrayList<>();

        for (int i = 1; i <= 100; i++) {
            UserEntity userEntity = new UserEntity();
            userEntity.setId(i);
            userEntity.setName("soulmate" + i * 100);
            userEntity.setAge(i + 10);
            userEntity.setEmail("soulmateqx@163.com" + i * 100);
            userEntity.setSign("" + i * 100);
            userEntity.setUpdateTime(new Date());
            userEntity.setCreateTime(new Date());
            list.add(userEntity);
        }
        return list;
    }

    /**
     * 导出数据到Excel
     *
     * @param response
     * @return object
     */
    public Object ExportDataToExcel(HttpServletResponse response) {
        //设置标题样式
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        //设置字体居中显示
        headWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        headWriteCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        //构造字体对象
        WriteFont writeFont = new WriteFont();
        writeFont.setFontHeightInPoints((short) 15);
        headWriteCellStyle.setWriteFont(writeFont);
        headWriteCellStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());

        //设置content样式
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        //设置字体垂直居中和水平居中
        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        contentWriteCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        writeFont.setFontHeightInPoints((short) 12);  //设置字体大小
        contentWriteCellStyle.setWriteFont(writeFont);
        contentWriteCellStyle.setWrapped(true);  //自动换行

        //设置边框样式  DASHED虚线
        contentWriteCellStyle.setBorderLeft(DASHED);
        contentWriteCellStyle.setBorderTop(DASHED);
        contentWriteCellStyle.setBorderRight(DASHED);
        contentWriteCellStyle.setBorderBottom(DASHED);

        //水平单元格样式策略, 头是头的样式 内容是内容的样式 其他的策略可以自己实现
        HorizontalCellStyleStrategy cellStyleStrategy = new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);

        String filename = "用户信息";

        ExcelWriter excelWriter = null;
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
//          这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode(filename + "-" + System.currentTimeMillis(), "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

            // 这里 需要指定写用哪个class去写
            excelWriter = EasyExcel.write(response.getOutputStream(), UserEntity.class).build();
            // 这里注意 如果同一个sheet只要创建一次
//            WriteSheet writeSheet = EasyExcel.writerSheet("").registerWriteHandler(horizontalCellStyleStrategy).build();
            WriteSheet writeSheet = EasyExcel.writerSheet("").registerWriteHandler(cellStyleStrategy).build();
            // 去调用写入,实际使用时根据数据库分页的总的页数来
            writeSheet.setSheetName("sheet" + 1);
            // 这里直接使用构造的数据, 也可以去数据库查询每一页的数据
            excelWriter.write(userEntities(), writeSheet);

            log.info("数据导出完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //千万别忘了finish,会帮我们自动关闭流
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
        return "";
    }
}
