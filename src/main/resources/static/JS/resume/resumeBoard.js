$(document).ready(function(){
	loadPage();
});

// 자소서 관리 시작 페이지
function loadPage(){
	$("#crud_form_box").hide();
	$.ajax({
		url : "/resumeAjax/list",
		type : "GET",
		cache : false,
		success : function(data, status){
			if(status == "success"){
				$("#title").text("자기소개서 관리");
				updateList(data)
				addViewEvent();  
			} else {
				alert("잘못된 접근");
			}
		}
	}); 		
	
} // end loadPage()

// 목록 업데이트
function updateList(items){
	var result = "";  // 최종 결과
	result += "<h5><b>작성 중 자소서</b></h5><table id='notfin'></table>";
	result += "<h5><b>작성 완료된 자소서</b></h5><table id='fin'></table>";
	$("#content").html(result);
	
	var count = items.length;
	for (var i = 0; i < count; i++){
		var row = "";
		row += "<tr class='box' data-id=" + items[i].id + ">\n";
		row += "<td>" + items[i].id + "</td>\n";
		row += "<td>" + items[i].title + "</td>\n";
		row += "<td>" + items[i].regdate + "</td>\n";
		row += "</tr>\n";
		if (items[i].fin) {
			$("#fin").append(row);
		} else {
			$("#notfin").append(row);
		}
	}
	// 자소서 작성 버튼 추가
	var writeBtn = "<button id='writeBtn'>작성</button>"
	$("#notfin").append(writeBtn);
} // end updateList()


// 자소서 박스 클릭 시  자소서 읽기 이벤트 등록
// 박스 클릭하면 view 화면 뜰수 있게 하기
function addViewEvent(){
	
	// 자소서 작성 박스 버튼 누르면 자소서 작성 폼 출력 이벤트
	$("#writeBtn").click(function() {
		setPage("write");	
	});
	
	// 자소서 박스 누르면 자소서 내용 읽어오는 이벤트
	$("#content .box").click(function(){
	
		// 읽어오기
		$.ajax({
			url : "/resumeAjax/" + $(this).attr('data-id'),
			type : "GET",
			cache : false,
			success : function(data, status){
				if(status == "success"){
					setData(data);
				}
			}
		});		
	});
} // end addViewEvent()


function setData(data) {
	
	var result = "";  // 최종 결과
	
	var conList = data.conList;
	var intro = data.intro;
	
	var count = conList.length;
	
	result += "<h5>" + intro.title + "</h5>";
	result += "<table>";
	
	for (var i = 0; i < count; i++){
		result += "<tr class='box' data-id=" + conList[i].id + ">\n";
		result += "<td>" + conList[i].question + "</td>\n";
		result += "<td>" + conList[i].content + "</td>\n";

		result += "</tr>\n";
	}
	
	result += "</table>";
	
	
	$('#content').html(result);

}


