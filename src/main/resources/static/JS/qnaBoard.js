// qnaBoard(고객센터 페이지, 고객센터 관리 페이지) 노수빈
var page = 1;   // 현재 페이지
var pageRows = 10;   // 페이지당 글의 개수

$(document).ready(function() {
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
			} else {
				alert("잘못된 접근");
				console.log("고객센터 게시판(리스트) 로딩 실패");
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
		// 답변상태에 답변이 있을 때 들어갈 아이콘
		var icon = '<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-chat-left-fill" viewBox="0 0 16 16">'
					+'<path d="M2 0a2 2 0 0 0-2 2v12.793a.5.5 0 0 0 .854.353l2.853-2.853A1 1 0 0 1 4.414 12H14a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z"/>'
					+'</svg>1';
		
		// 출력 정보 삽입
		for (var i = 0; i < count; i++) {
			result += "<tr>\n";
			result += "<td>" + data[i].qdto.id + "</td>\n";
			result += "<td>" + data[i].qdto.subject + "</td>\n";
			if (data[i].qdto.replyState == true) {
				result += "<td>" + icon + "</td>\n";
			} else {
				result += "<td> </td>\n";
			}
			result += "<td>" + data[i].name + "</td>\n";
			result += "<td>" + data[i].qdto.regdate + "</td>\n";
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
