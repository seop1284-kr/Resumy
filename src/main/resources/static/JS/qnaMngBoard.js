// qnaMng(고객센터 관리 페이지) 노수빈

var page = 1;   // 현재 페이지
var pageRows = 10;   // 페이지당 글의 개수
	
$(document).ready(function(){
	// 페이지 최초 로딩되면 1페이지 내용을 로딩
	loadPage(page);
});

// page번째 페이지 목록 읽어오기
function loadPage(page) {
	$.ajax({
		url: "/AjaxQnaBoard/" + page + "/" + pageRows,
		type: "GET",
		cache: false,
		success: function(data, status) {
			if(status == "success"){
				// view 에 QnaDTO 정보 삽입
				insertQnaDTO(data);
				// 모달에 데이터 삽입
				setModalData();
			} else {
				alert("잘못된 접근");
				console.log("QnaDTO 데이터 접근 실패");
			}
		}
	});
}

// view 에 QnaDTO 정보 삽입
function insertQnaDTO(jsonObj) { // data 인자를 jsonObj 매개변수로 받음
	var result = "";  // view 에 출력될 게시물 목록
	
	if (jsonObj.status == "OK") {
		var count = jsonObj.count;
		var data = jsonObj.data;  // .data : QnaDTO 의 QnaQDTO 객체
		window.page = jsonObj.page;
		window.pageRows = jsonObj.pagerows;
		// 답변하기 아이콘
		var btn_reply = '<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">'
					+ '<path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>'
					+ '<path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/?>'
					+ '</svg>';
		
		// 출력 정보 삽입
		for (var i = 0; i < count; i++) {
			var id = data[i].qdto.id;
			var view_url = "/main/qna/view.do?id=" + id;
			
			result += "<tr>\n";
			
			// 번호
			result += "<td>" + data[i].qdto.id + "</td>\n";
			// 제목
			result += "<td><a href=" + view_url + ">" + data[i].qdto.subject + "</td>\n";
			// 내용
			result += "<td>" + data[i].qdto.content + "</td>\n";
			// 등록일
			result += "<td>" + data[i].qdto.regdate + "</td>\n";
			// 답변상태
			if (data[i].qdto.replyState == true) {
				result += "<td class='replyStateYes'>완료</td>\n";
			} else {
				result += "<td>-</td>\n";
			}
			// 답변 (아이콘)
			result += '<td>'
						+ '<button type="button" class="btn_replyWriteModal" data-id="' + id + '" data-toggle="modal" data-target="#replyWriteModal" >'
						+ btn_reply
						+ '</td>';
			// 삭제 (체크박스)
			result += '<td><input type="checkbox" name="id" class="chk_delete" value="' + id + '"></td>\n';
			
			result += "</tr>\n";
		}
		$("table tbody").html(result);  // view 에 출력

		// [페이징] 정보 업데이트
		var pagination = buildPagination(jsonObj.writepages, jsonObj.totalpage, jsonObj.page, jsonObj.pagerows);
		$("#pagination").html(pagination);
		
		return true;
	} else {
		alert("내용이 없습니다");
		return false;
	}
}

// [페이징] 생성
// 한 [페이징]에 표시될 페이지수 --> writePages
// 총 페이지수 --> totalPage
// 현재 페이지 --> curPage
function buildPagination(writePages, totalPage, curPage, pageRows) {
	var str = "";   // 최종적으로 페이징에 나타날 HTML 문자열 <li> 태그로 구성

	// 페이징에 보여질 숫자들 (시작숫자 start_page ~ 끝숫자 end_page)
	var start_page = ((parseInt((curPage - 1) / writePages)) * writePages) + 1;
	var end_page = start_page + writePages - 1;

	if (end_page >= totalPage) {
		end_page = totalPage;
	}

	//■ << 표시 여부
	if (curPage > 1) {
		str += "<li><a onclick='loadPage(" + 1 + ")' class='tooltip-top' title='처음'><i class='fas fa-angle-double-left'></i></a></li>\n";
	}

	//■  < 표시 여부
	if (start_page > 1)
		str += "<li><a onclick='loadPage(" + (start_page - 1) + ")' class='tooltip-top' title='이전'><i class='fas fa-angle-left'></i></a></li>\n";

	//■  페이징 안의 '숫자' 표시	
	if (totalPage > 1) {
		for (var k = start_page; k <= end_page; k++) {
			if (curPage != k)
				str += "<li><a onclick='loadPage(" + k + ")'>" + k + "</a></li>\n";
			else
				str += "<li><a class='active tooltip-top' title='현재페이지'>" + k + "</a></li>\n";
		}
	}

	//■ > 표시
	if (totalPage > end_page) {
		str += "<li><a onclick='loadPage(" + (end_page + 1) + ")' class='tooltip-top' title='다음'><i class='fas fa-angle-right'></i></a></li>\n";
	}

	//■ >> 표시
	if (curPage < totalPage) {
		str += "<li><a onclick='loadPage(" + totalPage + ")' class='tooltip-top' title='맨끝'><i class='fas fa-angle-double-right'></i></a></li>\n";
	}
	
	return str;
} // end buildPagination()

// 관리 페이지에서 삭제버튼 (문의글 삭제버튼)
function frmChkSubmit() {
	var cnt = $(".chk_delete:checked").length;
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
