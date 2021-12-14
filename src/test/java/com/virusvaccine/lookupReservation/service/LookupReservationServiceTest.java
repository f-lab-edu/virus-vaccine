package com.virusvaccine.lookupReservation.service;

import com.virusvaccine.lookupReservation.dto.AgencyReservationInfo;
import com.virusvaccine.lookupReservation.dto.AgencyReservationInfoWithTime;
import com.virusvaccine.lookupReservation.dto.CalculatedAgencyReservationInfo;
import com.virusvaccine.lookupReservation.dto.UserReservationInfo;
import com.virusvaccine.common.exception.NotFoundException;
import com.virusvaccine.lookupReservation.mapper.LookupReservationMapper;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

//todo: jpa repository 적용 필요
@ExtendWith(SpringExtension.class)
class LookupReservationServiceTest {

  @Mock
  private LookupReservationMapper lookupReservationMapper;

  @InjectMocks
  private LookupReservationService lookupReservationService;

  @Test
  @DisplayName("lookupReservation 메서드 단위 테스트 : 예약한 내역이 있을경우")
  public void lookupReservationFoundTest(){

        List<UserReservationInfo> answer = List.of(UserReservationInfo.UserReservationInfoBuilder.anUserReservationInfo().withVaccineId(1L).build(),
            UserReservationInfo.UserReservationInfoBuilder.anUserReservationInfo().withVaccineId(2L).build());

        when(lookupReservationMapper.userReservationLookup(1L))
            .thenReturn(List.of(UserReservationInfo.UserReservationInfoBuilder.anUserReservationInfo().withVaccineId(1L).build(),
                UserReservationInfo.UserReservationInfoBuilder.anUserReservationInfo().withVaccineId(2L).build()));

        assertThat(lookupReservationService.lookupReservation(1L), equalTo(answer));

    }

    @Test
    @DisplayName("lookupReservation 메서드 단위 테스트 : 예약한 내역이 없는경우")
    public void lookupReservationNotFoundTest(){

        when(lookupReservationMapper.userReservationLookup(1L))
            .thenReturn(List.of());

        assertThrows(NotFoundException.class, ()-> lookupReservationService.lookupReservation(1L));

    }

    @Test
    @DisplayName("lookupAgencyReservation 메서드 단위 테스트 : 해당 기관이 확보한 백신 물량이 있는경우")
    public void lookupAgencyReservationTest(){

        HashMap<LocalDate, CalculatedAgencyReservationInfo> answer = new HashMap<>();

        CalculatedAgencyReservationInfo case1 = new CalculatedAgencyReservationInfo();
        case1.getAmount()[0] += 200;
        case1.getBookedAmount()[0] += 1;
        answer.put(LocalDate.of(2021,9,10), case1);

        CalculatedAgencyReservationInfo case2 = new CalculatedAgencyReservationInfo();
        case2.getAmount()[0] += 200;
        case2.getAmount()[2] += 200;
        case2.getBookedAmount()[0] += 1;
        case2.getBookedAmount()[2] += 1;
        answer.put(LocalDate.of(2021,9,2), case2);

        when(lookupReservationMapper.agencyReservationLookup(1L))
            .thenReturn(List.of(new AgencyReservationInfo(LocalDate.of(2021,9,2), 3, 200L, 1L),
                                new AgencyReservationInfo(LocalDate.of(2021,9,2), 1, 200L, 1L),
                                new AgencyReservationInfo(LocalDate.of(2021,9,10), 1, 200L, 1L)
                ));

        assertThat(lookupReservationService.lookupAgencyReservation(1L), equalTo(answer));

    }

    @Test
    @DisplayName("lookupAgencyReservation 메서드 단위 테스트 : 해당 기관이 확보한 백신 물량이 없는경우")
    public void lookupAgencyReservationNotFoundTest(){

        when(lookupReservationMapper.agencyReservationLookup(1L))
            .thenReturn(List.of());

        assertThrows(NotFoundException.class, ()-> lookupReservationService.lookupAgencyReservation(1L));

    }

    @Test
    @DisplayName("lookupAgencyReservationWithTime 메서드 단위 테스트 : 해당 기관이 확보한 백신 물량이 있는경우")
    public void lookupAgencyReservationWithTimeTest(){

        HashMap<LocalDate, HashMap<Integer, long[]>> answer = new HashMap<>();

        HashMap<Integer, long[]> case1 = new HashMap<>();
        for (int hour=0; hour<24; hour++) {
            case1.put(hour, new long[5]);
        }
        case1.get(13)[0] += 1;
        answer.put(LocalDate.of(2021,9,10), case1);

        HashMap<Integer, long[]> case2 = new HashMap<>();
        for (int hour=0; hour<24; hour++) {
            case2.put(hour, new long[5]);
        }
        case2.get(5)[2] += 1;
        case2.get(7)[0] += 1;
        answer.put(LocalDate.of(2021,9,2), case2);

        when(lookupReservationMapper.agencyReservationLookupWithTime(1L))
            .thenReturn(List.of(new AgencyReservationInfoWithTime(LocalDateTime.of(2021,9,2,5,10,24), 3),
                                new AgencyReservationInfoWithTime(LocalDateTime.of(2021,9,2,7,29,45), 1),
                                new AgencyReservationInfoWithTime(LocalDateTime.of(2021,9,10,13,32,11), 1)));

        HashMap<LocalDate, HashMap<Integer, long[]>> output = lookupReservationService.lookupAgencyReservationWithTime(1L);

        for (Map.Entry<LocalDate, HashMap<Integer, long[]>> entry : output.entrySet()){ // 각 날짜당 시간별 예약수(배열) 비교 검증
            LocalDate key = entry.getKey();
            HashMap<Integer, long[]> partialAnswer = answer.get(key);
            HashMap<Integer, long[]> partialOutput = output.get(key);
            for (int hour=0; hour<24; hour++) {
                assertTrue(Arrays.equals(partialAnswer.get(hour), partialOutput.get(hour)));
            }

        }

    }

    @Test
    @DisplayName("lookupAgencyReservationWithTime 메서드 단위 테스트 : 해당 기관이 확보한 백신 물량이 없는경우")
    public void lookupAgencyReservationWithTimeNotFoundTest(){

        when(lookupReservationMapper.agencyReservationLookupWithTime(1L))
            .thenReturn(List.of());

        assertThrows(NotFoundException.class, ()-> lookupReservationService.lookupAgencyReservationWithTime(1L));

    }


}