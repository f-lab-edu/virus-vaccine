package com.virusvaccine.service;

import com.virusvaccine.dto.CalculatedReturnedAgency;
import com.virusvaccine.dto.LookupRequest;
import com.virusvaccine.dto.ReturnedAgency;
import com.virusvaccine.dto.VaccineType;
import com.virusvaccine.mapper.LookupMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class AgencyServiceTest {

    @Mock
    LookupMapper lookupMapper;

    @InjectMocks
    AgencyService agencyService;

    @Test
    @DisplayName("lookup 메서드 단위 테스트")
    public void lookupTest(){

        LookupRequest lookupRequest = new LookupRequest((float) 60.3, (float) 102.3, VaccineType.AZ, true, LocalDate.of(2021,8,16));
        List<CalculatedReturnedAgency> answer = List.of(new CalculatedReturnedAgency(1L, "01022223333", "000-111", "seoul", "seoul", "seoul",
                "seoul"));
        answer.get(0).getRestAmount()[2] += 100;
        int code = lookupRequest.getCode() == null ? -1: lookupRequest.getCode().getType();
        LocalDate nextDay = lookupRequest.getDate() == null ? null: lookupRequest.getDate().plusDays(1);

        List<ReturnedAgency> returnedAgencys = List.of(new ReturnedAgency(1L,"01022223333", "000-111", "seoul","seoul","seoul", "seoul",3, 100));
        when(lookupMapper.lookup(lookupRequest, code, nextDay))
                .thenReturn(returnedAgencys); // ? 여기 왜 안되는 걸까요..?? List 를 자꾸 LinkedList 로 타입변환 하라고 하는데 도무지 이해가 안됩니다..
        

    }

}