package org.sample.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.RequestHandledEvent;

import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
@Component
public class EventTrigger implements SmartApplicationListener {

    /**
     * Event List :
     * 1. ContextClosedEvent // 结束应用时
     * This event is published when the ApplicationContext is closed using the close() method on theConfigurableApplicationContext interface.
     * A closed context reaches its end of life; it cannot be refreshed or restarted.
     * 2. ContextRefreshedEvent // 刚开始跑起来时
     * This event is published when the ApplicationContext is either initialized or refreshed.
     * This can also be raised using the refresh() method on the ConfigurableApplicationContext interface.
     * 3. ContextStartedEvent // 以下代码目前无法捕获这个事件
     * This event is published when the ApplicationContext is started using the start() method on theConfigurableApplicationContext interface.
     * You can poll your database or you can re/start any stopped application after receiving this event.
     * 4. ContextStoppedEvent // 以下代码目前无法捕获这个事件
     * This event is published when the ApplicationContext is stopped using the stop() method on the ConfigurableApplicationContext interface.
     * You can do required housekeep work after receiving this event.
     * 5. RequestHandledEvent
     * This is a web-specific event telling all beans that an HTTP request has been serviced.
     * **/

    /** ContextRefreshedEvent Event **/
    private volatile AtomicBoolean isInit = new AtomicBoolean(false);
    private Class<? extends ApplicationEvent> eventType;
    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        this.eventType = eventType;
        return eventType == ContextRefreshedEvent.class;
    }

    @Override
    public boolean supportsSourceType(Class<?> sourceType) {
        return true;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        //防止重复触发
        if (!isInit.compareAndSet(false, true)) {
            return;
        }
        start();
    }

    private void start() {
        //开启任务
        log.info("==== Test ContextRefreshedEvent : {}", this.eventType);
    }

    //值越小，就先触发
    @Override
    public int getOrder() {
        return 2;
    }

}
