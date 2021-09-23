
$(document).ready(function(){
	
	loadPage();
	
});


// 자소서 관리 시작 페이지
function loadPage(){
	
	// 자소서 목록 보이기, crud 폼 숨기기, 추가 질문 비우기, curResumeId = -1 로 설정
//	$("#crud_form_box").hide();
//	$("#content").show();
//	$("#content_text_plus").empty();

	//curResumeId = -1;
	$('#content1').html('<h4><b>기본정보</b></h4>');
	$('#content2').html('<h4><b>학력사항</b></h4>');
	$('#content3').html('<h4><b>경력사항</b></h4>');
	alert("333");
	$.ajax({
		url : "/careerAjax/list/10" ,
		type : "GET",
		cache : false,
		success : function(data, status){
				alert("111");
			if(status == "success"){
				alert("123");
				alert(data[0].company);
				//$("#title").text("자기소개서 관리");
				//updateList(data)
				//addViewEvent();  
			} else {
				alert("잘못된 접근");
				alert("124");
				console.log("222");
			}
		}
	}); 		
	
} // end loadPage()


