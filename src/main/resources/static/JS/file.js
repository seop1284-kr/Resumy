$(document).ready(function() {
	loadPage();
});

// 자소서 관리 시작 페이지
function loadPage() {

	$('#content').html('<h4><b>나의 파일 관리</b></h4>');

	$.ajax({
		url: "/fileAjax/filelist",
		type: "GET",
		cache: false,
		success: function(data, status) {
			if (status == "success") {
				//alert("목록 가져오기 성공");
				// response 가 application/json 이면 이미 parse된 결과가 data 에 담겨 있다.
				if (updateList(data)) {
					addViewEvent();
				} else {
					alert("목록 가져오기 0개")
				}
			}
		}

	});



} // end loadPage()


// 목록 업데이트
// 성공하면 true, 실패하면 false 리턴
// isFin : 완성, 미완성
function updateList(items) {
	var result = "";  // 최종 결과
	var count = items.length;

	if (count <= 0) {
		return false;
	}

	result += "<table>";
	for (var i = 0; i < count; i++) {
		result += "<tr class='box' data-id=" + items[i].id + ">\n";

		result += "<td>" + items[i].name + "</td>\n";
		result += "<td>" + items[i].volume + "</td>\n";
		result += "<td>" + items[i].regdate + "</td>\n";
		result += "<td>" + items[i].memo + "</td>\n";

		result += "</tr>\n";
	}
	result += "</table>";

	$("#content").html(result);

	return true;

} // end updateList()

