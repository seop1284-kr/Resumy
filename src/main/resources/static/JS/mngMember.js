var page = 1;   // 현재 페이지
var pageRows = 10;   // 페이지당 글의 개수
var viewItem = undefined;   // 가장 최근에 view 한 글의 데이터

$(document).ready(function() {
	// 페이지 최초 로딩되면 1페이지 내용을 로딩
	loadPage(page);


	// 글 삭제 버튼 누르면
	$("#btnDel").click(function() {
		chkDelete();
	});

});

// page번째 페이지 목록 읽어오기
function loadPage(page) {

	//alert(page + " 페이지 로딩");

	$.ajax({
		url: "/AjaxMngMember/" + page + "/" + pageRows,
		type: "GET",
		cache: false,
		success: function(data, status) {
			if (status == "success") {
				//alert("목록 가져오기 성공:  뿌~~");

				// response 가 application/json 이면 이미 parse된 결과가 data 에 담겨 있다.

				if (updateList(data)) {
				}

			}
		}

	});

} // end loadPage()


// 목록 업데이트
// 성공하면 true, 실패하면 false 리턴
function updateList(jsonObj) {
	var result = "";  // 최종 결과

	if (jsonObj.status == "OK") {
		var count = jsonObj.count;

		window.page = jsonObj.page;
		window.pageRows = jsonObj.pagerows;

		var items = jsonObj.data;  // 배열
		for (var i = 0; i < count; i++) {
			result += "<tr>\n";
			result += "<td>" + items[i].name + "</td>\n";
			result += "<td>" + items[i].userid + "</td>\n";
			result += "<td>" + items[i].email + "</td>\n";
			result += "<td>" + items[i].phone + "</td>\n";
			result += "<td>" + items[i].birthday + "</td>\n";
			result += "<td>" + items[i].regdtm + "</td>\n";
			result += "<td><input type='checkbox' name='uid' value='" + items[i].id + "'></td>\n";
			result += "</tr>\n";
		}
		$("#list tbody").html(result);  // 업데이트


		// 페이지 정보 업데이트
		$("#pageinfo").text(jsonObj.page + "/" + jsonObj.totalpage + "페이지, " + jsonObj.totalcnt + "명의 회원");

		// pageRows
		var txt = "<select id='rows' onchange='changePageRows()'>\n";
		txt += "<option " + ((window.pageRows == 10) ? "selected" : "") + " value='10'>10개씩</option>\n";
		txt += "<option " + ((window.pageRows == 20) ? "selected" : "") + " value='20'>20개씩</option>\n";
		txt += "<option " + ((window.pageRows == 50) ? "selected" : "") + " value='50'>50개씩</option>\n";
		txt += "<option " + ((window.pageRows == 100) ? "selected" : "") + " value='100'>100개씩</option>\n";
		txt += "</select>\n";
		$("#pageRows").html(txt);

		// [페이징] 정보 업데이트
		var pagination = buildPagination(jsonObj.writepages, jsonObj.totalpage, jsonObj.page, jsonObj.pagerows);
		$("#pagination").html(pagination);

	} else {
		alert("내용이 없습니다");
		return false;
	}

	return true;
} // end updateList()

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

// <select> 에서 pageRows 값 변경될때마다
function changePageRows() {
	window.pageRows = $("#rows").val();
	loadPage(window.page);
}



// check 된 uid 의 게시글들만 삭제 하기
function chkDelete() {

	var uids = [];  // check 된 uid 들을 담을 배열
	$("#list tbody input[name=uid]").each(function() {
		if ($(this).is(":checked")) {   // jQuery 에서 check 여부 확인 함수
			uids.push(parseInt($(this).val()));  // uids 배열에 check 된 uid 값 추가
		}
	});

	//alert(uids);

	if (uids.length == 0) {
		alert("삭제할 회원을 체크해주세요");
	} else {
		if (!confirm(uids.length + "명의 회원을 삭제하시겠습니까?")) return false;

		var data = $("#frmList").serialize();

		// DELETE 방식
		$.ajax({
			url: "/AjaxMngMember/",
			type: "DELETE",
			data: data,
			cache: false,
			success: function(data, status) {
				if (status == "success") {  // 200
					if (data.status == "OK") {
						alert("DELETE 성공 : " + data.count + "개")
						loadPage(window.page);   // 현재 페이지 목록 리로딩
					} else {
						alert("DELETE 실패 " + data.message);
						return false;
					}
				}
			}
		});
	}

} // end chkDelete()

// 특정 uid 의 글 삭제하기
function deleteUid(uid) {

	if (!confirm(uid + "글을 삭제하시겠습니까?")) return false;

	// DELETE 방식
	$.ajax({
		url: "/AjaxMngFed/",  //
		type: "DELETE",
		data: "uid=" + uid,
		cache: false,
		success: function(data, status) {
			if (status == "success") {
				if (data.status == "OK") {
					alert("DELETE성공: " + data.count + "개");
					loadPage(window.page);  // 현재 페이지 리로딩
				} else {
					alert("DELETE실패: " + data.message);
					return false;
				}
			}
		}
	});

	return true;
} // end deleteUid()

















