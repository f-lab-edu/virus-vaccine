package com.virusvaccine.lookupAgency.service;

import com.virusvaccine.lookupAgency.dto.CalculatedReturnedAgency;
import com.virusvaccine.lookupAgency.dto.LookupRequest;
import com.virusvaccine.lookupAgency.dto.ReturnedAgency;
import com.virusvaccine.common.exception.NotFoundException;
import com.virusvaccine.lookupAgency.mapper.LookupAgencyMapper;
import org.junit.jupiter.api.BeforeEach;
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

@ExtendWith(SpringExtension.class)
class LookupAgencyServiceTest {

    @Mock
    private LookupAgencyMapper lookupAgencyMapper;

    @InjectMocks
    private LookupAgencyService lookupAgencyService;

    private LookupRequest lookupRequest;

    @BeforeEach
    private void setUp(){
        lookupRequest = new LookupRequest((float) 60.3, (float) 102.3, null, true, null);
    }

    @Test
    @DisplayName("lookup 메서드 단위 테스트 : 조건에 맞는 기관을 찾았을때")
    public void lookupFoundTest(){

        List<CalculatedReturnedAgency> answer = List.of(
            CalculatedReturnedAgency.CalculatedReturnedAgencyBuilder.aCalculatedReturnedAgency().withId(1L).build(),
            CalculatedReturnedAgency.CalculatedReturnedAgencyBuilder.aCalculatedReturnedAgency().withId(2L).build());
        answer.get(0).getRestAmount()[2] += 100;
        answer.get(0).addTotal(100);
        answer.get(0).getRestAmount()[3] += 100;
        answer.get(0).addTotal(100);
        answer.get(1).getRestAmount()[0] += 100;
        answer.get(1).addTotal(100);


        List<ReturnedAgency> returnedAgencys = List.of(
            ReturnedAgency.ReturnedAgencyBuilder.aReturnedAgency().withId(1L).withVaccineId(3).withRestAmount(100).build(),
            ReturnedAgency.ReturnedAgencyBuilder.aReturnedAgency().withId(1L).withVaccineId(4).withRestAmount(100).build(),
            ReturnedAgency.ReturnedAgencyBuilder.aReturnedAgency().withId(2L).withVaccineId(1).withRestAmount(100).build());

        when(lookupAgencyMapper.lookup(lookupRequest, -1, null))
                .thenReturn(returnedAgencys);

        assertThat(lookupAgencyService.lookup(lookupRequest), equalTo(answer));

    }

    @Test
    @DisplayName("lookup 메서드 단위 테스트 : 조건에 맞는 기관을 못찾았을때")
    public void lookupNotFoundTest(){

        when(lookupAgencyMapper.lookup(lookupRequest, -1, null))
                .thenReturn(List.of());

        assertThrows(NotFoundException.class, () -> lookupAgencyService.lookup(lookupRequest));

    }

}