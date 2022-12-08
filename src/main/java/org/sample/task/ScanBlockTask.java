package org.sample.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component // enable task : step 2 : add this
public class ScanBlockTask {
  private int counter = 0;

  @Scheduled(fixedDelay=1000) // enable task : step 3 : add this, fixedDelay / fixedRate is very different or you can use this : cron="*/5 * * * * MON-FRI"
  public void task() {
    log.info("Hello, {}", counter);
    counter++;
  }
}
