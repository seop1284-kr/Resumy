// Call the dataTables jQuery plugin

 // Korean
 var lang_kor = {
  "decimal" : "",
  "emptyTable" : "데이터가 없습니다.",
  "info" : "_START_ ~ _END_ 페이지 (총 _TOTAL_ 건)",
  "infoEmpty" : "0 건",
  "infoFiltered" : "(전체 _MAX_ 건의 검색결과)",
  "infoPostFix" : "",
  "thousands" : ",",
  "lengthMenu" : "_MENU_ 개씩 보기",
  "loadingRecords" : "로딩중...",
  "processing" : "처리중...",
  "search" : "검색 : ",
  "zeroRecords" : "검색된 데이터가 없습니다.",
  "paginate" : {
      "first" : "<<",
      "last" : ">>",
      "next" : ">",
      "previous" : "<"
  },
  "aria" : {
      "sortAscending" : " :  오름차순 정렬",
      "sortDescending" : " :  내림차순 정렬"
  }
};

$(document).ready(function() {

  // jquery dataTables 설정
  $('#dataTable').DataTable({
    /* 기본 옵션 위치 설정
       l : length changing input control
       f : filtering input
       t : the table
       i : Table information summary
       p : pagination control
       r : processing display element
    */
    dom: "fltip",
    language : lang_kor
  });

  // dataTables_info 뒤에 삭제 버튼 삽입
  var content = '<button type="button" class="btn btn-primary dataTables_btn_del">항목 삭제</button>';
  $(".dataTables_info").append(content);
  
});