20200109 || calender에서 count, 기간 UI 적용 완료.

​						(real_count 전환작업 필요) by hi-ing2

20200107 || todo_total~calender 이동 보수작업 완료.
						start/endDate db등록 및 호출 완료. by hi-ing2

# 해야하는거

- 친구추가하면 todo set으로 넘어가는 문제
- todo set 봤을 때 다른 아이디의 todo까지 보여지는 문제
- todo 삭제하면 db에서 todo테이블 삭제
- calender - real_count 넣기
- c



# TodoController

- RequestParam 해야됨
- 대충 기능 구현 완료 / 디자인 및 StartDate값은 캘린더로 받아와야함



# HomeController & HomeHTML

- 데이터베이스 데이터가져와서 HomeHTML에 뿌려주기까지 완료
- todo_total~calender 이동 보수작업 완료



# SettingHTML & SetController

- Todo관리탭 추가
  - Todo Total(할일 전체 수량) 추가
  - Todo Delete 버튼 추가 : 버튼클릭시 로컬 H2 콘솔에서 데이터 삭제
  - 친구관리탭 추가
  - start/endDate 항목 추가
- 앱 관리탭 추가
  - 로그아웃 버튼 추가 



# calenderHTML & CalenderController 
###### *by hi-ing2*

- 외부 calender 스크립트 적용(js,html,css)

  > 출처표시

- home에서 todo 선택 시 해당 todo에 관한 calender 호출

  - startDate, EndDate 호출 : 기간 UI
  - color 호출 : 기간 UI 색상
  - real_count 호출 :

  > 매일 초기화되는 real_count를 startDate~EndDate까지
  >
  > 보여주고, 투명도 설정하여 수행도 측정.

