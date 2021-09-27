package com.virusvaccine.common.config;

import javax.management.timer.Timer;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@Configuration
public class CacheScheduleConfig {

  public static final String VALUE_RANKING_QUANTITY = "QuantityOfVaccines";
  public static final String VALUE_RANKING_BOOKEDNUM = "QuantityOfBookedVaccines";
  public static final String VALUE_RANKING_AGENCY = "AgencysWithRestAmount";
  public static final String VALUE_RANKING_REGION = "RegionsWithRestAmount";

  private final long TWO_HOURS = Timer.ONE_HOUR * 2;

  @Scheduled(fixedRate = TWO_HOURS)
  @CacheEvict(VALUE_RANKING_QUANTITY)
  public void clearValue1(){}

  @Scheduled(fixedRate = TWO_HOURS)
  @CacheEvict(VALUE_RANKING_BOOKEDNUM)
  public void clearValue2(){}

  @Scheduled(fixedRate = TWO_HOURS)
  @CacheEvict(VALUE_RANKING_AGENCY)
  public void clearValue3(){}

  @Scheduled(fixedRate = TWO_HOURS)
  @CacheEvict(VALUE_RANKING_REGION)
  public void clearValue4(){}


}
