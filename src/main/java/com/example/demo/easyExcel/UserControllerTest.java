package com.example.demo.easyExcel;

import com.example.demo.easyExcel.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName UserController
 * @Author soulmate
 * @Version V1.0.0
 * @CreateTime 2021-02-07 18:25:19
 * @Description TODO
 */

@Slf4j
@RestController
public class UserControllerTest {

    @Resource
    IUserService iUserService;

    /**
     * 用户数据导出接口
     * 浏览器或者swagger访问 localhost:8080/user/export就可以把Excel文件下载下来
     *
     * @param response
     * @return
     */
    @GetMapping("/user/export")
    @ResponseBody
    public Object exportDataToExcel(HttpServletResponse response) {
        iUserService.ExportDataToExcel(response);
        return "导出成功!";
    }
}
