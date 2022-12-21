package org.sample.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/*
 Deprecated, Spring 支持的 task annotation 过于简单，停止时对数据的风险比较高
 */

@Deprecated
@Slf4j
@Component // enable task : step 2 : add this
public class SpringTask {
  private int counter = 0;

//  @Value("${chain}") // 这个可以取得 java -jar xxx.jar --chain=ooo 的参数 ooo，直接注入给变量，没有输入的话会报错
  protected String chainSelector;

//  @Scheduled(fixedDelay=1000) // enable task : step 3 : add this, fixedDelay / fixedRate is very different or you can use this : cron="*/5 * * * * MON-FRI"
  public void task() {
    log.info("Hello, {}", counter);
    counter++;
  }
}
