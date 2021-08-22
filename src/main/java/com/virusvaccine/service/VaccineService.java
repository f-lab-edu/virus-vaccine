package com.virusvaccine.service;

import com.virusvaccine.dto.Vaccine;
import com.virusvaccine.dto.VaccineRegistrationRequest;
import com.virusvaccine.dto.Virus;
import com.virusvaccine.exception.RequestException;
import com.virusvaccine.mapper.VaccineMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.virusvaccine.service.AccountService.USER_KEY;

@Service
public class VaccineService {
    private final HttpSession session;

    private final VaccineMapper vaccineMapper;

    public VaccineService(HttpSession session, VaccineMapper vaccineMapper) {
        this.session = session;
        this.vaccineMapper = vaccineMapper;
    }

    public List<Virus> getViruses(){
        return vaccineMapper.getViruses();
    }

    public List<Vaccine> getVaccines(){
        return vaccineMapper.getVaccines();
    }

    @Transactional
    public void register(VaccineRegistrationRequest vaccine){
        LocalDateTime reqDate = vaccine.getVaccinateAt().toLocalDateTime();
        LocalDateTime now = LocalDateTime.now();

        if (now.isAfter(reqDate)) throw new RequestException("과거 날짜로 등록 요청");

        Long agencyId = (Long) session.getAttribute(USER_KEY);
        vaccine.setAgencyId(agencyId);
        vaccineMapper.insertAcquiredVaccine(vaccine);
    }
}
