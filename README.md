# [트래픽 체크 프로젝트]
 
수정일 : 2023.09.12

- 이 프로젝트는 API 트래픽을 Thread Safe 하게 체크하는 프로젝트 입니다.
- AOP를 API를 호출할 때 마다 응답 시간을 체크합니다.
- API별로 응답 결과를 ConcurrentHashMap 에 저장합니다.
- 응답 결과 : 성공, 지연응답, 오류 
- 1분에 한 번씩 스케쥴러를 호출하여 ConcurrentHashMap 에 저장된 결과를 DB에 적재합니다
- ConcurrentHashMap 을 초기화 합니다.