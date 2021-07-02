# financial-investment

### 부동산, 펀드 등 금융상품 투자 서비스
- 사용자는 전체 투자 상품을 조회할 수 있으며, 모집 기간 내 상품에 투자할 수 있습니다.
- 투자상품이 오픈될 때, 다수의 고객이 동시에 투자를 합니다.
- 투자 후 투자 상품의 누적 투자모집 금액, 투자자 수가 증가됩니다.
- 총 투자모집금액 달성 시 투자는 마감되고 상품은 Soldout 됩니다.
- 다수의 서버/인스턴스로 동작하더라도 기능에 문제가 없도록 작동합니다.

---
## 기능정의

## 사용자
- [ ] 회원 가입, 로그인
- [ ] 투자 상품 조회
    - [ ] sort, filter 적용 조회
- [ ] 상품 투자
- [ ] 투자 현황 조회

## 관리자
- [ ] 투자 상품 등록
- [ ] 투자 상품 별 현황 조회
