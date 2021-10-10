# virus-vaccine

### 바이러스 백신 예약 서비스

- 사용자는 일반 사용자와 기관 사용자가 있습니다.
- 백신의 종류는 Pf, Md, Az, Js, Nv 가 있습니다.
- 일반 사용자는 선택한 위치의 반경에 있는 접종 기관 목록을 조회할 수 있으며, 백신 물량이 있는 접종 기관에 예약할 수 있습니다.
  - 접종기관 조회 시, 사용자 입력 기준 반경 5Km 내 접종기관을 조회합니다.
  - 접종기관 조회 시, 사용자는 원하는 백신의 종류를 선택하여 조회할 수 있습니다.
  - 접종기관 조회 시, 사용자는 잔여 물량이 있는 접종기관만 요청할 수 있습니다.
  - 백신 예약 시, 사용자는 접종시간을 선택할 수 없습니다.
  - 백신 예약 시, 잔여 물량이 1회 감소합니다.
  - 백신 예약 시, 기관의 백신 물량이 없으면 예약 할 수 없습니다.
  - 2회 예약 완료 시, 추가적인 백신 예약이 불가능합니다.
- 기관 사용자는 자신의 기관에 물량을 등록 할 수 있고, 예약한 사용자를 확인할 수 있습니다.
  - 백신 물량 등록 시, 종류와 수량, 접종시간을 입력하여 등록합니다.


---
### 시스템 구조
![Image of Yaktocat](https://github.com/f-lab-edu/virus-vaccine/tree/main/pictures/Architecture.png)
---
### 사용자 공통
- [ ] 회원 가입, 로그인
   - [ ] 기관 회원 시 위치(위경도,  EPSG:4326), 전화번호, 주소 등록 

### 일반 사용자
- [ ] 사용자로부터 5km 내에 위치한 잔여 물량이 있는 접종기관 목록 조회
   - [ ] 백신 브랜드 선택하여 조회 가능
   - [ ] 기관 상세정보 조회 가능 (전화번호, 주소)
- [ ] 잔여 물량이 있을 경우 예약 
   - [ ] 예약 시점에 물량이 없을 경우 실패
   - [ ] 백신 브랜드에 따라 예약 횟수 제한, 1회 혹은 2회

### 기관 사용자
- [ ] 확보한 물량의 백신을 등록
   - [ ] 백신의 종류와 수량, 접종시간을 등록
- [ ] 백신 예약자 리스트 확인

### 데이터 분석
- [ ] 등록 횟수가 가장 많은 백신
- [ ] 예약 횟수가 가장 많은 백신
- [ ] 잔여 물량이 가장 많은 기관 및 지역


