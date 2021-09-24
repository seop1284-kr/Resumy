var curResumeId = -1;	// -1이면 현재 어떤 자소서도 선택하지 않음

$(document).ready(function(){
	
	loadPage();
	
	// 자소서 질문 추가 버튼
	$(".plusBtn").click(function(){
		makeContentForm();
	});
	
	// 목록 버튼(자소서 메인 페이지(목록)로 이동)
	$(".listBtn").click(function(){
		loadPage();	
	});
	
	// 수정 취소 버튼(뷰 페이지로 이동)
	$(".updateCancelBtn").click(function() {
		makePage(curResumeId, "view");
	});
	
	// 삭제 버튼(삭제 시 자소서 메인 페이지(목록)로 이동)
	$(".deleteBtn").click(function() {
		if(!confirm(curResumeId + "글을 삭제하시겠습니까?")) return false;	
		deleteResume();
		loadPage();  // 현재 페이지 리로딩
		

	});
	
	// 자소서 수정 버튼(수정 페이지로 이동)
	$(".updateBtn").click(function(){
		makePage(curResumeId, "update");
	});
		
	// 자소서 저장 버튼(성공 시 뷰 페이지로 이동)
	// 글 작성 폼 submit 되면
	$("#frmWrite").submit(function(){
		if (curResumeId == -1) {
			writeResume();  // 새글 등록 submit
		} else {
			deleteResume();	// 글 지우고
			writeResume();  // 새글 등록 submit
		}
		makePage(curResumeId, "view");
	});
	
	
});

// 새글 등록 처리
function writeResume(){
	
	// 특정 form 의 name 달린 form element 들의 value 들을 string 으로 묶기
	// ex) name=aaa&subject=bbb&content=ccc   <-- string 타입이다
	var serialData = $("#frmWrite").serialize();
	$.ajax({
		url : "/resumeAjax",  // url : /board
		type : "POST",
		cache : false,
		data : serialData,  // POST 로 ajax request 할 경우 data 에 parameter 넘기기
		async: false,
		success : function(data, status){
			if(status == "success"){
				//alert("INSERT 성공 " + data);
				curResumeId = data;
			}
		}
	});
	
} // end chkWrite()


function deleteResume() {
	
	// DELETE 방식
	$.ajax({
		url : "/resumeAjax",  // URL : /board
		type : "DELETE",
		data : "id=" + curResumeId,
		cache : false,
        async: false,


		success : function(data, status){
			if(status == "success"){
				//alert("DELETE성공"); 
				
			}
		}
	});
}


function makeContentForm() {
	var content_text = '<div id="content_text_plus"><hr><input type="text" placeholder="질문" name="question">';
	content_text += '<textarea placeholder="내용" name="content"></textarea>';
	content_text += '<button class="minus" onclick="$(this).parent().remove()">삭제</button></div>'
	$('#content_text_plus').append(content_text);
}

// 자소서 관리 시작 페이지
function loadPage(){
	
	// 자소서 목록 보이기, crud 폼 숨기기, 추가 질문 비우기, curResumeId = -1 로 설정
	$("#crud_form_box").hide();
	$("#content").show();
	$("#content_text_plus").empty();

	curResumeId = -1;
	
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
		curResumeId = $(this).attr('data-id');
		makePage(curResumeId, "view");
		

	});
} // end addViewEvent()


function makePage(id, set) {
	// 읽어오기
	$.ajax({
		url : "/resumeAjax/" + id,
		type : "GET",
		cache : false,
		success : function(data, status){
			if(status == "success"){				
				setData(data);
				setPage(set);
			}
		}
	});	
}

// 글 쓰기 / 읽기 / 수정
function setPage(mode){
	// 자소서 목록 숨기기, crud 폼 보이기
	$("#content").hide();
	$("#crud_form_box").show();
	
	// 글 작성
	if(mode == "write"){
		$("#title").text("자기소개서 관리");
		
		$('#frmWrite')[0].reset();  // form 내의 기존 내용 reset		
		$("#frmWrite .btn_group_write").show();
		$("#frmWrite .btn_group_view").hide();
		$("#frmWrite .btn_group_update").hide();
		$("#frmWrite .minus").show();
		
		$("#frmWrite input[name='pub']").attr("checked", false);		
		$("#frmWrite input[name='pub']").attr("disabled", false);
		$("#frmWrite input[name='pub']").css("border", "1px solid #ccc");
		$("#frmWrite input[name='fin']").attr("checked", false);				
		$("#frmWrite input[name='fin']").attr("disabled", false);
		$("#frmWrite input[name='fin']").css("border", "1px solid #ccc");
		
		
		$("#frmWrite input[name='title']").attr("readonly", false);
		$("#frmWrite input[name='title']").css("border", "1px solid #ccc");
		
		$("#frmWrite input[name='question']").attr("readonly", false);
		$("#frmWrite input[name='question']").css("border", "1px solid #ccc");
		
		$("#frmWrite textarea[name='content']").attr("readonly", false);
		$("#frmWrite textarea[name='content']").css("border", "1px solid #ccc");
	}
	
	// 글 읽기
	if(mode == "view"){
		$("#title").text("자기소개서");
		
		$("#frmWrite input[name='pub']").attr("disabled", true);
		$("#frmWrite input[name='pub']").css("border", "none");
		$("#frmWrite input[name='fin']").attr("disabled", true);
		$("#frmWrite input[name='fin']").css("border", "none");
		
		

		$("#frmWrite .btn_group_write").hide();
		$("#frmWrite .btn_group_view").show();
		$("#frmWrite .btn_group_update").hide();
		$("#frmWrite .minus").hide();

		//$("#frmWrite input[name='uid']").val(viewItem.uid);   // 나중에 삭제나 수정을 위해 필요
		
		
		//$("#frmWrite input[name='title']").val(viewItem.subject)
		$("#frmWrite input[name='title']").attr("readonly", true);
		$("#frmWrite input[name='title']").css("border", "none");
		
		//$("#frmWrite input[name='question']").val(viewItem.subject)
		$("#frmWrite input[name='question']").attr("readonly", true);
		$("#frmWrite input[name='question']").css("border", "none");
		
		//$("#frmWrite textarea[name='content']").val(viewItem.content);
		$("#frmWrite textarea[name='content']").attr("readonly", true);
		$("#frmWrite textarea[name='content']").css("border", "none");
	}
	
	// 글 수정
	if(mode == "update"){
		
		$("#title").text("자기소개서 수정");
		
		$("#frmWrite .btn_group_write").hide();
		$("#frmWrite .btn_group_view").hide();
		$("#frmWrite .btn_group_update").show();
		$("#frmWrite .minus").show();

		$("#frmWrite input[name='pub']").attr("disabled", false);
		$("#frmWrite input[name='pub']").css("border", "1px solid #ccc");
		$("#frmWrite input[name='fin']").attr("disabled", false);
		$("#frmWrite input[name='fin']").css("border", "1px solid #ccc");
		
		$("#frmWrite input[name='title']").attr("readonly", false);
		$("#frmWrite input[name='title']").css("border", "1px solid #ccc");
		
		$("#frmWrite input[name='question']").attr("readonly", false);
		$("#frmWrite input[name='question']").css("border", "1px solid #ccc");
		
		$("#frmWrite textarea[name='content']").attr("readonly", false);
		$("#frmWrite textarea[name='content']").css("border", "1px solid #ccc");
	}
	
	
} // end setPopup()

function setData(data) {
	$("#content_text_plus").empty();
	var conList = data.conList;
	var intro = data.intro;
	var count = conList.length;
	

	$("#frmWrite input[name='title']").val(intro.title)
		
	$("#frmWrite input[name='question']").eq(0).val(conList[0].question)
	$("#frmWrite textarea[name='content']").eq(0).val(conList[0].content);

	
	$("#frmWrite input[name='pub']").prop("checked", intro.pub);		
	$("#frmWrite input[name='fin']").prop("checked", intro.fin);		
		

	for (var i = 1; i < count; i++){
		makeContentForm();
		$("#frmWrite input[name='question']").eq(i).val(conList[i].question)
		$("#frmWrite textarea[name='content']").eq(i).val(conList[i].content);

	}

}


