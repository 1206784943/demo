package com.example.demo.springEvent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 事件监听
 */
@Slf4j
@Component
public class UserMonitor {

    @Async()
    @EventListener(value = UserActionEvent.class, condition = "#userActionEvent.enumUserOperate.name()=='ADD'")
    public void addUserActionEvent(UserActionEvent userActionEvent) {
        log.info("事件监听操作:" + userActionEvent.getEnumUserOperate() + "，接收数据" + userActionEvent.getUser());
    }
}
