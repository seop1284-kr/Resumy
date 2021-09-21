$(document).ready(function(){
	
	loadPage();
	
	// 자소서 내용(질문+답변) 폼 추가
	$("#plus").click(function(){
		var form = '<div id="content_text"><input type="text" placeholder="자소서 제목(필수)" name="title" required>';
		form += '<input type="text" placeholder="질문" name="question">';
		form += '<textarea placeholder="내용" name="content"></textarea>';
		form += '<Button type="button" onclick="$(this).parent().remove()" id="minus">삭제</Button></div>';
		$("#minus").click(function(){
			
		})
		$("#content_text_box").append(form);			
	});
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
					setPage("view");
					setData(data);
				}
			}
		});		
	});
} // end addViewEvent()

// 글 쓰기 / 읽기 / 수정
function setPage(mode){
	
	// 자소서 목록 숨기기
	$("#content").hide();

	// 폼 보이기
	$("#crud_form_box").show();
	
	// 글 작성
	if(mode == "write"){
		$("#title").text("자기소개서 관리");
		
		$('#frmWrite')[0].reset();  // form 내의 기존 내용 reset		
		$("#frmWrite .btn_group_write").show();
		$("#frmWrite .btn_group_view").hide();
		$("#frmWrite .btn_group_update").hide();
		
		
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

		$("#frmWrite .btn_group_write").hide();
		$("#frmWrite .btn_group_view").show();
		$("#frmWrite .btn_group_update").hide();
		
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
		
		$("#frmWrite .btn_group_header").show();
		$("#frmWrite .btn_group_write").hide();
		$("#frmWrite .btn_group_view").hide();
		$("#frmWrite .btn_group_update").show();
		
		
		$("#frmWrite input[name='title']").attr("readonly", false);
		$("#frmWrite input[name='title']").css("border", "1px solid #ccc");
		
		$("#frmWrite input[name='question']").attr("readonly", false);
		$("#frmWrite input[name='question']").css("border", "1px solid #ccc");
		
		$("#frmWrite textarea[name='content']").attr("readonly", false);
		$("#frmWrite textarea[name='content']").css("border", "1px solid #ccc");
	}
	
	
} // end setPopup()

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

	return true;
}


