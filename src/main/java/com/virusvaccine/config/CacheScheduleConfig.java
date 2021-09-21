package com.virusvaccine.config;

import javax.management.timer.Timer;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@Configuration
public class CacheScheduleConfig {

  public static final String value1 = "QuantityOfVaccines";
  public static final String value2 = "QuantityOfBookedVaccines";
  public static final String value3 = "AgencysWithRestAmount";
  public static final String value4 = "RegionsWithRestAmount";

  private final long TWO_HOURS = Timer.ONE_HOUR * 2;

  @Scheduled(fixedRate = TWO_HOURS)
  @CacheEvict(value1)
  public void clearValue1(){}

  @Scheduled(fixedRate = TWO_HOURS)
  @CacheEvict(value2)
  public void clearValue2(){}

  @Scheduled(fixedRate = TWO_HOURS)
  @CacheEvict(value3)
  public void clearValue3(){}

  @Scheduled(fixedRate = TWO_HOURS)
  @CacheEvict(value4)
  public void clearValue4(){}


}
