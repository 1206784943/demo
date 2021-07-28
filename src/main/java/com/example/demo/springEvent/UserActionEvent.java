package com.example.demo.springEvent;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.io.Serializable;

/**
 * 事件
 */
@Getter
@Setter
public class UserActionEvent extends ApplicationEvent implements Serializable {

    private UserAction user;
    private EnumUserOperate enumUserOperate;

    public UserActionEvent(Object source) {
        super(source);
    }
}
