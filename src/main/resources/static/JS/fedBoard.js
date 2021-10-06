// Korean
var lang_kor = {
	"decimal": "",
	"emptyTable": "데이터가 없습니다.",
	"info": "_START_ ~ _END_ 페이지 (총 _TOTAL_ 건)",
	"infoEmpty": "0 건",
	"infoFiltered": "(전체 _MAX_ 건의 검색결과)",
	"infoPostFix": "",
	"thousands": ",",
	"lengthMenu": "_MENU_ 개씩 보기",
	"loadingRecords": "로딩중...",
	"processing": "처리중...",
	
	"search": "",
	"searchPlaceholder": "자소서 항목, 내용, 제목, 아이디 등 키워드",
	
	"zeroRecords": "관련 자소서가 없습니다.",
	"paginate": {
		"first": "<<",
		"last": ">>",
		"next": ">",
		"previous": "<"
	},
	"aria": {
		"sortAscending": " :  오름차순 정렬",
		"sortDescending": " :  내림차순 정렬"
	}
};
		
$(document).ready(function() {
	
	// DataTable 설정
	var dataTable = $('#dataTable').DataTable({
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
		  	url: "/AjaxFedBoard/1/100000000",
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
			// 자소서 제목
            { "data": "intro.title",
				render: function(data, row) {					
					return '<div class="ellipsis">'
							+ data
							+ '</div>';
				}
			},	
			// 자소서 피드백 내용 일부
            { "data": "conList",
				render: function(data, row) {
					var id = data[0].iid;
					var view_url = "/fedView?id=" + id;
					
					return "<a class='left ellipsis-parent' href=" + view_url + ">"
							+'<div class="ellipsis">'
							+ data[0].question + '/'
							+ data[0].content
							+ '</div>';
				}
			},
			// 피드백 답변 갯 수
            { "data": "fedList.length",
				render: function(data, row) {
					if (data == 0) {
						return '';
					} else {
						return '<i class="fas fa-comment-alt"> '
							+ data
							+ '</i>';
					}
					
				} 
			},
			// 작성자 id
            { "data": "intro.userid",
				render: function(data, row) {					
					return '<div class="ellipsis">'
							+ data
							+ '</div>';
				} 
			},
			// 수정날짜
			{ "data": "intro.modydate"}
			
        ]
	});
	
	// custom 검색 기능
	$('#customSearchTextField').keyup(function(){
    	dataTable.search($(this).val()).draw();
	})
	
});
