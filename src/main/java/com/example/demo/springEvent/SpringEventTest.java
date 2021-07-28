package com.example.demo.springEvent;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringEventTest {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    /**
     * 事件发布
     */
    @Test
    public void test() {
        UserAction user = new UserAction();
        user.setAge(18);
        user.setName("Dick");
        UserActionEvent userActionEvent = new UserActionEvent(this);
        userActionEvent.setUser(user);
        userActionEvent.setEnumUserOperate(EnumUserOperate.ADD);
        applicationEventPublisher.publishEvent(userActionEvent);
        log.info("事件发布操作:" + EnumUserOperate.ADD + "，发送数据UserAction" + userActionEvent);
    }

}
