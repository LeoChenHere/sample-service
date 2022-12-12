package org.sample.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Slf4j
@Component
public class PostConstructExampleBean {

    @Autowired
    private Environment environment;

    @PostConstruct
    public void init() {
        log.info("==== Test PostConstruct {} ", Arrays.asList(environment.getDefaultProfiles()));
    }
}