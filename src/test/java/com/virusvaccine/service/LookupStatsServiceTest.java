package com.virusvaccine.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;
import com.virusvaccine.dto.RankedReturnedAgency;
import com.virusvaccine.dto.ReturnedAgency;
import com.virusvaccine.dto.ReturnedRegion;
import com.virusvaccine.dto.VaccineQuantity;
import com.virusvaccine.mapper.LookupStatsMapper;
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

    List<RankedReturnedAgency> answer = List.of(new RankedReturnedAgency("기관6", null, null,null,null,null,null),
                                                new RankedReturnedAgency("기관5", null, null,null,null,null,null),
                                                new RankedReturnedAgency("기관4", null, null,null,null,null,null),
                                                new RankedReturnedAgency("기관3", null, null,null,null,null,null),
                                                new RankedReturnedAgency("기관2", null, null,null,null,null,null));

    when(lookupStatsMapper.getAgencysWithRestAmount())
        .thenReturn(List.of(new ReturnedAgency(1L,"기관1",null,null,null,null,null,null,3,100),
                            new ReturnedAgency(2L,"기관2",null,null,null,null,null,null,1,200),
                            new ReturnedAgency(3L,"기관3",null,null,null,null,null,null,1,300),
                            new ReturnedAgency(4L,"기관4",null,null,null,null,null,null,1,400),
                            new ReturnedAgency(5L,"기관5",null,null,null,null,null,null,1,500),
                            new ReturnedAgency(6L,"기관6",null,null,null,null,null,null,1,600)));

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