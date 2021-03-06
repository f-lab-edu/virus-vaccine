package com.virusvaccine.vaccine.service;

import com.virusvaccine.vaccine.dto.VaccineRegistrationRequest;
import com.virusvaccine.vaccine.mapper.VaccineMapper;
import com.virusvaccine.vaccine.service.VaccineService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VaccineServiceTest {
    @Mock
    VaccineMapper vaccineMapper;

    @InjectMocks
    VaccineService vaccineService;

    @Test
    @DisplayName("백신 등록 테스트 : 성공")
    void registerTestWhenCorrectRequestThenSuccess() {
        VaccineRegistrationRequest request = new VaccineRegistrationRequest(1L, 10, LocalDateTime.now().plusDays(1));
        doNothing().when(vaccineMapper).insertAcquiredVaccine(request);

        vaccineService.register(1L, request);

        assertThat(request.getAgencyId(), equalTo(1L));
        verify(vaccineMapper).insertAcquiredVaccine(any(VaccineRegistrationRequest.class));
    }
}