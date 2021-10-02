var page = 1;   // 현재 페이지
var pageRows = 10;   // 페이지당 글의 개수
var viewItem = undefined;   // 가장 최근에 view 한 글의 데이터
var keyword = "";

$(document).ready(function() {
	// 페이지 최초 로딩되면 1페이지 내용을 로딩
	loadPage(page);

	// 검색 버튼 누르면
	$("#searchBtn").click(function() {
		keyword = $("#keyword").val();
		page = 1;
		loadPage(page);
	});
});

// page번째 페이지 목록 읽어오기
function loadPage(page) {

	if (keyword == "") {
		// 키워드 있을 때
		$.ajax({
			url: "/AjaxFedBoard/" + page + "/" + pageRows,
			type: "GET",
			cache: false,
			success: function(data, status) {
				if (status == "success") {
					updateList(data);
					addViewEvent();

				}
			}
		});
	} else {
		// 키워드 없을 때
		$.ajax({
			url: "/AjaxFedBoard/" + page + "/" + pageRows + "/" + keyword,
			type: "GET",
			cache: false,
			success: function(data, status) {
				if (status == "success") {
					updateList(data)
					addViewEvent();
				}
			}
		});
	}

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
			result += "<tr class='box' style='cursor:pointer;color:#blue;' data-id=" + items[i].intro.id + ">\n";

			result += "<td>" + items[i].intro.title + "</td>\n";
			result += "<td>" + items[i].conList[0].question + " / " + items[i].conList[0].content + "</td>\n";
			result += "<td>" + items[i].fedList.length + "</td>\n";
			result += "<td>" + items[i].intro.modydate + "</td>\n";
			result += "<td>" + items[i].intro.userid + "</td>\n";

			result += "</tr>\n";
		}
		$("#list tbody").html(result);  // 업데이트


		// 페이지 정보 업데이트
		$("#pageinfo").text(jsonObj.page + "/" + jsonObj.totalpage + "페이지, " + jsonObj.totalcnt + "개의 글");


		// [페이징] 정보 업데이트
		var pagination = buildPagination(jsonObj.writepages, jsonObj.totalpage, jsonObj.page, jsonObj.pagerows);
		$("#pagination").html(pagination);

	} else {
		alert("내용이 없습니다");
		return false;
	}

	return true;
} // end updateList()

function addViewEvent() {
	
	// 자소서 박스 누르면 자소서 내용 읽어오는 이벤트
	$(".box").click(function() {
		var form = document.createElement('form');
		var contentObj;
		var content = "fedView";
		contentObj = document.createElement('input');
		contentObj.setAttribute('type', 'hidden');
		contentObj.setAttribute('name', 'content');
		contentObj.setAttribute('value', content);
		form.appendChild(contentObj);
		
		var viewIdObj;
		viewIdObj = document.createElement('input');
		viewIdObj.setAttribute('type', 'hidden');
		viewIdObj.setAttribute('name', 'id');
		viewIdObj.setAttribute('value', $(this).attr('data-id'));
		form.appendChild(viewIdObj);
		
		var headerMenuObj;
		headerMenuObj = document.createElement('input');
		headerMenuObj.setAttribute('type', 'hidden');
		headerMenuObj.setAttribute('name', 'headerMenu');
		headerMenuObj.setAttribute('value', "fed");
		form.appendChild(headerMenuObj);
		
		form.setAttribute('method', 'put');
		form.setAttribute('action', "/");

		document.body.appendChild(form);

		form.submit();
		

	});
} // end addViewEvent()


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

















