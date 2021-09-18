$(document).ready(function(){
	loadPage();
});

function loadPage(){
	
	//alert(page + " 페이지 로딩");
	
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


	for (var i = 0; i < count; i++){
		result += "<tr class='box' data-id=" + items[i].id + ">\n";
	
		result += "<td>" + items[i].id + "</td>\n";
		result += "<td>" + items[i].title + "</td>\n";
		result += "<td>" + items[i].regdate + "</td>\n";

		result += "</tr>\n";
	}

	
	if (isFin) {
		$("#fin").html(result);  // 업데이트
	} else {
		$("#notfin").html(result);  // 업데이트
	}

	return true;

} // end updateList()



function addViewEvent(){
	
	$("#content .box").click(function(){
		$(location).attr("href", $(this).attr('data-id'));
	});
	
} // end addViewEvent()





