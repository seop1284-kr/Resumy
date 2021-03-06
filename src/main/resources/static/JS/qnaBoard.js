// Call the dataTables jQuery plugin
// qnaBoard(고객센터 페이지, 고객센터 관리 페이지) 노수빈

// Korean
var lang_kor = {
	"emptyTable": "데이터가 없습니다.",
	"loadingRecords": "로딩중...",
	"processing": "처리중...",
	"paginate" : {
		"next" : ">",
    	"previous" : "<"
  	}
};
		
$(document).ready(function() {
	
	// DataTable 설정
	$('#dataTable').DataTable({
		destroy: true, // 테이블 재생성
		/* 기본 옵션 위치 설정
	       l : length changing input control
	       f : filtering input
	       t : the table
	       i : Table information summary
	       p : pagination control
	       r : processing display element
	    */
	    dom: "tp",
		language: lang_kor,
		ordering: false, // 칼럼별 정렬기능
		/*order: [[0, 'desc']], // 기본 정렬칼럼 (0이 첫번째 칼럼)
		serverSide: false,*/
		/*paging: true, // 페이징처리
        lengthChange: true, // 데이터건수 변경
        lengthMenu: [10, 20, 50, 100], // 데이터건수옵션 
        pageLength: 10, // 기본 데이터건수
		pagingType: full_numbers, // 페이징 종류 ('First', 'Previous', 'Next', 'Last' buttons)
		autoWidth: false, // 가로자동
        searching: false, // 검색
        scrollX: true, // 가로 스크롤*/
		ajax: {
		  	url: "/AjaxQnaBoard/1/100000000",
			type: "GET",
			cache: false,
			/* dataSrc 속성 값을 이용해서 Controller에서 반환하는 키값을 변경 
			   : 키값이 없다면 '' 반환
			   : map 반환 -> map.put("data", allUser);로 키 값을 바꿔주면 됨
				 (dataSrc 사용X, 키값과 columns:[]의 키값은 이름이 동일해야 됨)
			*/
			dataSrc: 'data'
		},
		columns: [
			/* columns: Java Controller 에서 Object 형식으로 넘어올 때의 key 값들
						columns [] 안에 쓴 순서와, <tbody>의 칼럼 순서/개수 맞춰야 함
			 */
			// 문의글 일련번호
            { "data": "qdto.id" },
			// 문의글 제목
            { "data": "qdto",
				render: function(data, row) {
					var id = data.id;
					var view_url = "/main/qna/view.do?id=" + id;
					var subject = data.subject;
					
					return "<a class='left ellipsis-parent' href=" + view_url + ">" 
							+ '<div class="ellipsis">'
							+ subject
							+ '</div>'
							+ "</a>";
				}
			},
			// 답변상태
            { "data": "qdto.replyState",
				render: function(data, row) {
					if (data) {
						return '<span class="replyStateYes"><i class="fas fa-comment-alt"> 1</i></span>';
					} else {
						return '';
					}
				}
		    },
			// 작성자
			{ "data": "name",
				render: function(data, row) {
					return data + "";
				}
			},
			// 문의글 등록일
            { "data": "qdto.regdate" }
        ]
	});
	
});
