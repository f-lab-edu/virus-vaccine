package com.virusvaccine.lookupStats.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;
import com.virusvaccine.lookupStats.dto.RankedReturnedAgency;
import com.virusvaccine.lookupAgency.dto.ReturnedAgency;
import com.virusvaccine.lookupStats.dto.ReturnedRegion;
import com.virusvaccine.lookupStats.dto.VaccineQuantity;
import com.virusvaccine.lookupStats.service.LookupStatsService;
import com.virusvaccine.lookupStats.mapper.LookupStatsMapper;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class LookupStatsServiceTest {

  @Mock
  LookupStatsMapper lookupStatsMapper;

  @InjectMocks
  LookupStatsService lookupStatsService;

  @Test
  @DisplayName("getQuantityOfVaccines 메서드 단위 테스트")
  public void getQuantityOfVaccinesTest(){

    Long[] answer = {1L, 3L, 2L, 4L, 5L};

    when(lookupStatsMapper.getQuantityOfVaccines())
        .thenReturn(List.of(new VaccineQuantity(3, 200L),
                            new VaccineQuantity(1, 200L),
                            new VaccineQuantity(1, 200L)));

    assertThat(lookupStatsService.getQuantityOfVaccines(), equalTo(answer));

  }

  @Test
  @DisplayName("getQuantityOfBookedVaccines 메서드 단위 테스트")
  public void getQuantityOfBookedVaccinesTest(){

    Long[] answer = {1L, 3L, 2L, 4L, 5L};

    when(lookupStatsMapper.getQuantityOfBookedVaccines())
        .thenReturn(List.of(new VaccineQuantity(3, 1L),
                            new VaccineQuantity(1, 1L),
                            new VaccineQuantity(1, 1L)));

    assertThat(lookupStatsService.getQuantityOfBookedVaccines(), equalTo(answer));

  }

  @Test
  @DisplayName("getAgencysWithRestAmount 메서드 단위 테스트")
  public void getAgencysWithRestAmountTest(){

    List<RankedReturnedAgency> answer = List.of(RankedReturnedAgency.RankedReturnedAgencyBuilder.aRankedReturnedAgency().withName("기관6").build(),
        RankedReturnedAgency.RankedReturnedAgencyBuilder.aRankedReturnedAgency().withName("기관5").build(),
        RankedReturnedAgency.RankedReturnedAgencyBuilder.aRankedReturnedAgency().withName("기관4").build(),
        RankedReturnedAgency.RankedReturnedAgencyBuilder.aRankedReturnedAgency().withName("기관3").build(),
        RankedReturnedAgency.RankedReturnedAgencyBuilder.aRankedReturnedAgency().withName("기관2").build()
        );

    when(lookupStatsMapper.getAgencysWithRestAmount())
        .thenReturn(List.of(ReturnedAgency.ReturnedAgencyBuilder.aReturnedAgency().withId(1L).withName("기관1").withVaccineId(3).withRestAmount(100).build(),
            ReturnedAgency.ReturnedAgencyBuilder.aReturnedAgency().withId(2L).withName("기관2").withVaccineId(1).withRestAmount(200).build(),
            ReturnedAgency.ReturnedAgencyBuilder.aReturnedAgency().withId(3L).withName("기관3").withVaccineId(1).withRestAmount(300).build(),
            ReturnedAgency.ReturnedAgencyBuilder.aReturnedAgency().withId(4L).withName("기관4").withVaccineId(1).withRestAmount(400).build(),
            ReturnedAgency.ReturnedAgencyBuilder.aReturnedAgency().withId(5L).withName("기관5").withVaccineId(1).withRestAmount(500).build(),
            ReturnedAgency.ReturnedAgencyBuilder.aReturnedAgency().withId(6L).withName("기관6").withVaccineId(1).withRestAmount(600).build()));

    assertThat(lookupStatsService.getAgencysWithRestAmount(), equalTo(answer));

  }

  @Test
  @DisplayName("getRegionsWithRestAmount 메서드 단위 테스트")
  public void getRegionsWithRestAmountTest(){

    List<String> answer = List.of("서울6", "서울3", "서울5", "서울2", "서울4");

    when(lookupStatsMapper.getRegionsWithRestAmount())
        .thenReturn(List.of(new ReturnedRegion("서울1", 10),
                            new ReturnedRegion("서울2", 200),
                            new ReturnedRegion("서울3", 350),
                            new ReturnedRegion("서울4", 50),
                            new ReturnedRegion("서울5", 230),
                            new ReturnedRegion("서울6", 1000)));

    assertThat(lookupStatsService.getRegionsWithRestAmount(), equalTo(answer));

  }

}