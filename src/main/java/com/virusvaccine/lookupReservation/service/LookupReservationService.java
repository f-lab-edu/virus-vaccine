package com.virusvaccine.lookupReservation.service;

import com.virusvaccine.bookVaccine.entity.AcquiredVaccineEntity;
import com.virusvaccine.bookVaccine.entity.BookingEntity;
import com.virusvaccine.bookVaccine.repository.AcquiredVaccineRepository;
import com.virusvaccine.bookVaccine.repository.BookingRepository;
import com.virusvaccine.lookupReservation.dto.AgencyReservationInfo;
import com.virusvaccine.lookupReservation.dto.AgencyReservationInfoWithTime;
import com.virusvaccine.lookupReservation.dto.CalculatedAgencyReservationInfo;
import com.virusvaccine.lookupReservation.dto.UserReservationInfo;
import com.virusvaccine.common.exception.NotFoundException;
import com.virusvaccine.lookupReservation.mapper.LookupReservationMapper;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class LookupReservationService {

  private final BookingRepository bookingRepository;
  private final AcquiredVaccineRepository acquiredVaccineRepository;

  public LookupReservationService(BookingRepository bookingRepository, AcquiredVaccineRepository acquiredVaccineRepository) {
    this.bookingRepository = bookingRepository;
    this.acquiredVaccineRepository = acquiredVaccineRepository;
  }

  public List<UserReservationInfo> lookupReservation(Long userId) {
    List<BookingEntity> userReservationBooking = bookingRepository.userReservationLookup(userId);

    if (userReservationBooking.isEmpty()){
      throw new NotFoundException();
    }

    return userReservationBooking.stream()
            .map(b -> new UserReservationInfo(
                    b.getVaccinateAt().toLocalDateTime(),
                    b.getAcquiredVaccine().getVaccine().getId(),
                    b.getAcquiredVaccine().getAgency().getName(),
                    b.getAcquiredVaccine().getAgency().getPhoneNumber(),
                    b.getAcquiredVaccine().getAgency().getZipCode(),
                    b.getAcquiredVaccine().getAgency().getSiDo(),
                    b.getAcquiredVaccine().getAgency().getSiGunGu(),
                    b.getAcquiredVaccine().getAgency().getEupMyeonDong(),
                    b.getAcquiredVaccine().getAgency().getAddress()
                    ))
            .collect(Collectors.toList());
  }

  public HashMap<LocalDate, CalculatedAgencyReservationInfo> lookupAgencyReservation(Long agencyId) {
    List<AcquiredVaccineEntity> acquiredVaccineEntities = acquiredVaccineRepository.findByAgency_Id(agencyId);
    if (acquiredVaccineEntities.isEmpty())
      throw new NotFoundException();

    List<AgencyReservationInfo> agencyReservationInfos = acquiredVaccineEntities.stream().map(a -> new AgencyReservationInfo(
            a.getVaccinateAt().toLocalDate(), a.getVaccine().getId().intValue(), (long) a.getAmount(), (long) (a.getAmount()- a.getRestAmount())
    )).collect(Collectors.toList());

    HashMap<LocalDate, CalculatedAgencyReservationInfo> toReturn = new HashMap<>();

    for (AgencyReservationInfo agencyReservationInfo: agencyReservationInfos){
      LocalDate date = agencyReservationInfo.getVaccinateAt();
      if (!toReturn.containsKey(date)){
        toReturn.put(date, new CalculatedAgencyReservationInfo());
      }
      toReturn.get(date).getAmount()[agencyReservationInfo.getVaccineId()-1] += agencyReservationInfo.getRestAmount();
      toReturn.get(date).getBookedAmount()[agencyReservationInfo.getVaccineId()-1] += agencyReservationInfo.getBookedAmount();
    }
    return toReturn;
  }

  public HashMap<LocalDate, HashMap<Integer, long[]>> lookupAgencyReservationWithTime(Long agencyId){
    List<BookingEntity> bookingEntities = bookingRepository.agencyReservationLookupWithTime(agencyId);
    if (bookingEntities.isEmpty())
      throw new NotFoundException();
    List<AgencyReservationInfoWithTime> agencyReservationInfoWithTimes = bookingEntities.stream().map(
            b -> new AgencyReservationInfoWithTime(b.getVaccinateAt().toLocalDateTime(), b.getAcquiredVaccine().getVaccine().getId().intValue())
    ).collect(Collectors.toList());

    HashMap<LocalDate, HashMap<Integer, long[]>> toReturn = new HashMap<>();

    for (AgencyReservationInfoWithTime agencyReservationInfoWithTime: agencyReservationInfoWithTimes){
      LocalDate date = agencyReservationInfoWithTime.getVaccinateAt().toLocalDate();
      if (!toReturn.containsKey(date)){
        HashMap<Integer, long[]> vaccinePerHour = new HashMap<>();
        for (int hour=0; hour<24; hour++) { // 0시부터 23시까지
          vaccinePerHour.put(hour, new long[5]);
        }
        toReturn.put(date, vaccinePerHour);
      }
      toReturn.get(date).get(agencyReservationInfoWithTime.getVaccinateAt().toLocalTime().getHour())[agencyReservationInfoWithTime.getVaccineId()-1] ++;
    }

    return toReturn;
  }
}
