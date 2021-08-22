package com.virusvaccine.service;

import com.virusvaccine.dto.VaccineRegistrationRequest;
import com.virusvaccine.exception.RequestException;
import com.virusvaccine.mapper.VaccineMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;

import static com.virusvaccine.service.AccountService.USER_KEY;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VaccineServiceTest {
    @Mock
    VaccineMapper vaccineMapper;

    @Mock
    HttpSession session;

    @InjectMocks
    VaccineService vaccineService;

    @Test
    @DisplayName("백신 등록 테스트 : 성공")
    void registerTestWhenCorrectRequestThenSuccess() {
        VaccineRegistrationRequest request = new VaccineRegistrationRequest(1L, 10, new Timestamp(System.currentTimeMillis()));
        doNothing().when(vaccineMapper).insertAcquiredVaccine(request);
        when(session.getAttribute(USER_KEY)).thenReturn(1L);

        vaccineService.register(request);

        assertThat(request.getAgencyId(), equalTo(1L));
        verify(vaccineMapper).insertAcquiredVaccine(any(VaccineRegistrationRequest.class));
    }

    @Test
    @DisplayName("백신 등록 테스트 : 실패 - 과거 시간으로 등록")
    void registerTestWhenPastDatetimeRequestThenFail() {
        VaccineRegistrationRequest request = new VaccineRegistrationRequest(1L, 10, new Timestamp(System.currentTimeMillis()-1000_0000));

        assertThrows( RequestException.class, ()->vaccineService.register(request)) ;

        assertThat(request.getAgencyId(), equalTo(null));
    }
}