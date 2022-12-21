package org.sample.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ScheduledFuture;

@RestController
@Component
@Slf4j
public class ThreadTask {
  private static final int threadNums = 10;

  @Autowired
  private ThreadPoolTaskScheduler threadPoolTaskScheduler;


  private final List<ScheduledFuture<?>> futureList = new ArrayList<>();


  @Bean
  public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
    ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
    threadPoolTaskScheduler.setPoolSize(threadNums);
    threadPoolTaskScheduler.setThreadNamePrefix("taskExecutor-");
    //用来设置线程池关闭的时候等待所有任务都完成再继续销毁其他的Bean，这样这些异步任务的销毁就会先于Redis线程池的销毁
    threadPoolTaskScheduler.setWaitForTasksToCompleteOnShutdown(true);
    //该方法用来设置线程池中任务的等待时间，如果超过这个时候还没有销毁就强制销毁，以确保应用最后能够被关闭，而不是阻塞住
    threadPoolTaskScheduler.setAwaitTerminationSeconds(60);
    return threadPoolTaskScheduler;
  }

  @RequestMapping("/startCron")
  public String startCron() {
    /**
     scheduleAtFixedRate:
     是以上一个任务开始的时间计时，period 毫秒过去后，检测上一个任务是否执行完毕，
     如果上一个任务执行完毕，则当前任务立即执行，
     如果上一个任务没有执行完毕，则需要等上一个任务执行完毕后立即执行
     scheduleWithFixedDelay:
     是以上一个任务结束时开始计时，period 毫秒过去后，立即执行。
     **/
    for (int i = 0; i < threadNums; i++) {
      log.info("-------------------------------新建了一个任务-----------------");
      futureList.add(threadPoolTaskScheduler.scheduleAtFixedRate(new TaskRunner(), 100));
    }
    log.info("任务开启");
    return "startCron";
  }

  @RequestMapping("/stopCron")
  public String stopCron() {
    for (ScheduledFuture future : futureList) {
      if (future != null) {
        future.cancel(false);//false：如果此任务正在执行，则执行完再关闭，true：如果正在执行，也给关闭
      }
    }
    System.out.println("DynamicTask.stopCron()");
    return "stopCron";
  }

  private static class TaskRunner implements Runnable {

    @Override
    @Async
    public void run() {
      String name = Thread.currentThread().getName();
      System.out.println(name+"---执行时间：" + LocalDateTime.now());
      Random random = new Random();
      int i = random.nextInt(10);
      try {
        Thread.sleep(i * 1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(name+"---本次执行完成"+ LocalDateTime.now());
    }
  }
}
