// Call the dataTables jQuery plugin
// qnaMng(고객센터 관리 페이지) 노수빈

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
	    dom: "fltip",
	    language : lang_kor,
		order: [[0, 'desc']], // 기본 정렬칼럼 (0이 첫번째 칼럼)
		ordering: true, // 칼럼별 정렬기능
		serverSide: false,
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
							
							return "<a class='left ellipsis-parent' href=" + view_url + "><div class='ellipsis'>" + subject + "</div></a>";
						}
					},
					// 문의글 내용
                    { "data": "qdto.content",
						render: function(data, row) {
							return "<span class='left ellipsis-parent'><div class='ellipsis'>" + data + "</div></span>";
						}
					},
					// 문의자 아이디
					{ "data": "qdto.userid",
						render: function(data, row) {
							return data + "";
						}
					},
					// 문의글 등록일
                    { "data": "qdto.regdate" },
					// 답변상태
                    { "data": "qdto.replyState",
						render: function(data, row) {
							if (data) {
								return '<span class="replyStateYes">완료</span>';
							} else {
								return '<span>-</span>';
							}
						}
				    },
					// 답변하기 modal
				    { "data": "qdto.id",
						render: function(data, row) {
							return '<button type="button" class="text-center btn_replyWriteModal" data-id="' + data + '" data-toggle="modal" data-target="#replyWriteModal" onclick="setModalData(' + data + ')"><i class="far fa-edit"></i></button>';
						}
				    },
					// 문의글 삭제
				    { "data": "qdto.id",
						render: function(data, row) {
							return '<input type="checkbox" name="id" class="dataTables_btn_del" value="' + data + '">';
						}
				    }
        ]
	});
	
	// 데이터 없으면 페이징 없애기
	if ($.fn.dataTable.isDataTable('#dataTable')) {
	    $('#dataTable').DataTable();
		insertButton(); // dataTables 사이에 button 삽입
	}
	else {
	    table = $('#dataTable').DataTable({
	        paging: false
	    });
		// 페이징이 없을 때 버튼도 없애고 싶은데 방법을 모르겠음
	}

});

// 관리 페이지에서 삭제버튼 (문의글 삭제버튼)
function insertButton() {
	var insertStd = '#dataTable_info'; /* 어떤 요소를 기준으로 element를 삽입할 건지 */
	var insertElement = '<button type="button" class="btn btn-primary btn_del float-right mt-1" onclick="frmChkSubmit()">항목 삭제</button>'; /* 삽입하려는 element */

	/*  특정 요소(바깥)의 앞에 내용 삽입 (cf. after) */
	$(insertStd).before(insertElement);
}

function frmChkSubmit() {
	var cnt = $(".dataTables_btn_del:checked").length;
	var warning = cnt + "개의 문의글을 삭제하시겠습니까?";
	
	if (cnt > 0) {
		if (confirm(warning) == true){
			document.frmChk.submit(); // form[name="frmchk"]
		} else {
			return false;
		}
	} else {
		alert("삭제를 원하시는 항목을 체크해주세요.");
		return false;
	}
}

// 모달에 데이터 삽입 (qnaMng.jsp 에 Ajax 방식으로 데이터 삽입)
function setModalData(id) {
	getQnaDTO(id); // QnaDTO 정보 가져옴 (그 후에 view 에 가져온 정보 삽입)
	
	// 모달에서 답변삭제 버튼 (delete query 수행)
	$("#btn_delete").click(function() {
		$(location).attr("href", "qnaADeleteOk.do?id=" + id);
	});
}

// QnaDTO 정보 가져옴
function getQnaDTO(id) {
	$.ajax({
		url : "/AjaxQnaBoard/list/" + id,
		type : "GET",
		cache : false,
		success : function(data, status) { // data 가 QnaDTO dto가 담김
			if(status == "success"){
				// view 에 QnaDTO 정보 삽입
				insertQnaDTOModal(data);
			} else {
				alert("잘못된 접근");
				console.log("qnaMng 페이지 로딩 실패");
			}
		}
	});
}

// view 에 QnaDTO 정보 삽입
function insertQnaDTOModal(data) {
	// form 내의 기존 내용 reset
	$('#modalfrm')[0].reset();
	
	// 문의글 일련번호
	$("span.q_id").text(data.qdto.id);
	$("input[type='hidden'].q_id").val(data.qdto.id);
	
	// 문의글 작성자
	$("#q_name").text(data.name);
	
	// 문의글 업로드 날짜
	$("#q_regdate").text(data.qdto.regdate);
	
	// 문의글 제목
	$("#q_subject").text(data.qdto.subject);
	
	// 문의글 내용
	$("#q_content").text(data.qdto.content);

	// 문의글 답변
	var adto = data.adto;
	if(typeof adto == "undefined" || adto == "" || adto == null){
	}else{
		$("#r_reply").val(adto.reply);
	}
};
