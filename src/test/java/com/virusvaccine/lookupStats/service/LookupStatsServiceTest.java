package com.virusvaccine.lookupStats.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;
import com.virusvaccine.lookupStats.dto.ReturnedSortedAgency;
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

    List<Integer> answer = List.of(5,3,1,3,2);

    when(lookupStatsMapper.getQuantityOfVaccines())
        .thenReturn(List.of(5,3,1,3,2));

    assertThat(lookupStatsService.getQuantityOfVaccines(), equalTo(answer));

  }

  @Test
  @DisplayName("getQuantityOfBookedVaccines 메서드 단위 테스트")
  public void getQuantityOfBookedVaccinesTest(){

    List<Integer> answer = List.of(5,4,3,2,1);

    when(lookupStatsMapper.getQuantityOfBookedVaccines())
        .thenReturn(List.of(5,4,3,2,1));

    assertThat(lookupStatsService.getQuantityOfBookedVaccines(), equalTo(answer));


  }

  @Test
  @DisplayName("getAgencysWithRestAmount 메서드 단위 테스트")
  public void getAgencysWithRestAmountTest(){

    List<ReturnedSortedAgency> answer = List.of(ReturnedSortedAgency.ReturnedSortedAgencyBuilder.aReturnedSortedAgency().withName("기관4").build(),
        ReturnedSortedAgency.ReturnedSortedAgencyBuilder.aReturnedSortedAgency().withName("기관1").build(),
        ReturnedSortedAgency.ReturnedSortedAgencyBuilder.aReturnedSortedAgency().withName("기관3").build(),
        ReturnedSortedAgency.ReturnedSortedAgencyBuilder.aReturnedSortedAgency().withName("기관2").build(),
        ReturnedSortedAgency.ReturnedSortedAgencyBuilder.aReturnedSortedAgency().withName("기관5").build()
        );

    when(lookupStatsMapper.getAgencysWithRestAmount())
        .thenReturn(List.of(ReturnedSortedAgency.ReturnedSortedAgencyBuilder.aReturnedSortedAgency().withName("기관4").build(),
            ReturnedSortedAgency.ReturnedSortedAgencyBuilder.aReturnedSortedAgency().withName("기관1").build(),
            ReturnedSortedAgency.ReturnedSortedAgencyBuilder.aReturnedSortedAgency().withName("기관3").build(),
            ReturnedSortedAgency.ReturnedSortedAgencyBuilder.aReturnedSortedAgency().withName("기관2").build(),
            ReturnedSortedAgency.ReturnedSortedAgencyBuilder.aReturnedSortedAgency().withName("기관5").build()));

    assertThat(lookupStatsService.getAgencysWithRestAmount(), equalTo(answer));

  }

  @Test
  @DisplayName("getRegionsWithRestAmount 메서드 단위 테스트")
  public void getRegionsWithRestAmountTest(){

    List<String> answer = List.of("서울6", "서울3", "서울5", "서울2", "서울4");

    when(lookupStatsMapper.getRegionsWithRestAmount())
        .thenReturn(List.of("서울6", "서울3", "서울5", "서울2", "서울4"));

    assertThat(lookupStatsService.getRegionsWithRestAmount(), equalTo(answer));

  }

}