$(document).ready(function(){
	loadPage();
});

// 자소서 관리 시작 페이지
function loadPage(){
	
	$('#content').html('<h4><b>자기소개서 관리</b></h4><h5><b>작성 중 자소서</b></h5><div id="notfin"></div><h5><b>완성 자소서</b></h5><div id="fin"></div>');

	$.ajax({
		url : "/resumeAjax/finlist",
		type : "GET",
		cache : false,
		success : function(data, status){
			if(status == "success"){
				//alert("목록 가져오기 성공:  뿌~~");
				// response 가 application/json 이면 이미 parse된 결과가 data 에 담겨 있다.
				if(updateList(data, true)) {
					addViewEvent();  
				}
			}
		}
		
	});
	
	$.ajax({
		url : "/resumeAjax/notfinlist",
		type : "GET",
		cache : false,
		success : function(data, status){
			if(status == "success"){
				//alert("목록 가져오기 성공:  뿌~~");
				// response 가 application/json 이면 이미 parse된 결과가 data 에 담겨 있다.
				if(updateList(data, false)) {
					addViewEvent();  
				}
			}
		}
		
	}); 		
	
} // end loadPage()


// 목록 업데이트
// 성공하면 true, 실패하면 false 리턴
// isFin : 완성, 미완성
function updateList(items, isFin){
	var result = "";  // 최종 결과
	var count = items.length;
	
	if (count <= 0) {
		return false;
	}

	result += "<table>";
	for (var i = 0; i < count; i++){
		result += "<tr class='box' data-id=" + items[i].id + ">\n";
	
		result += "<td>" + items[i].id + "</td>\n";
		result += "<td>" + items[i].title + "</td>\n";
		result += "<td>" + items[i].regdate + "</td>\n";

		result += "</tr>\n";
	}
		result += "</table>";

	
	if (isFin) {
		$("#fin").html(result);  // 업데이트
	} else {
		$("#notfin").html(result);  // 업데이트
	}

	return true;

} // end updateList()


// 자소서 박스 클릭 시  자소서 읽기 이벤트 등록
// 박스 클릭하면 view 화면 뜰수 있게 하기
function addViewEvent(){
	$("#content .box").click(function(){
		// 읽어오기
		$.ajax({
			url : "/resumeAjax/" + $(this).attr('data-id'),
			type : "GET",
			cache : false,
			success : function(data, status){
				if(status == "success"){
					$('#content').empty();
					viewResume(data);
				}
			}
		});
		
	});
	
} // end addViewEvent()

function viewResume(data) {
	var result = "";  // 최종 결과
	
	var conList = data.conList;
	var intro = data.intro;
	
	result += "<h4><b>자기소개서</b></h4> <h5>" + intro.title + "</h5>";
	
	var count = conList.length;
	
	result += "<table>";
	
	for (var i = 0; i < count; i++){
		result += "<tr class='box' data-id=" + conList[i].id + ">\n";
		result += "<td>" + conList[i].question + "</td>\n";
		result += "<td>" + conList[i].content + "</td>\n";

		result += "</tr>\n";
	}
	
	result += "</table>";
	
	
	$('#content').html(result);

	return true;
}


