package org.sample.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
@Component
public class EventTrigger implements SmartApplicationListener {

    /**
     * Event List :
     * 1. ContextClosedEvent
     * 2. ContextRefreshedEvent
     * 3. ContextStartedEvent
     * 4. ContextStoppedEvent
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
        log.info("Test ContextRefreshedEvent : {}", this.eventType);
    }

    //值越小，就先触发
    @Override
    public int getOrder() {
        return 2;
    }

}
