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

	result += "<table class='fileList'>";
		result += "<th><input type='checkbox' value='선택'></th>\n";
		result += "<th>첨부파일명</th>\n";
		result += "<th>용량</th>\n";
		result += "<th>등록일</th>\n";
		result += "<th>MEMO</th>\n";
	
	for (var i = 0; i < count; i++) {	
		result += "<tr class='box' data-id=" + items[i].id + ">\n";
		result += "<td><input type='checkbox' value='선택'>" + "</td>\n";
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




// 업로드 버튼 누르면 모달 팝업
	$("#uploadBtn").click(function(){
		setPopup("upload");
		$("#dlg_file").show();
	});
	
// 모달 대화상자 취소 버튼 누르면
	$(".modal .close").click(function(){
		$(this).parents(".modal").hide();
	});
	
// 모달 대화상자 업로드 버튼 누르면	
	$("#frmWrite").submit(function(){
		$(this).parents(".modal").hide();
		
		return chkWrite();  // 새글 등록 submit
	});

// 다운로드 버튼

// 삭제 버튼



// 파일 업로드 대화상자 세팅
	function setPopup(mode){
		
		if(mode == "upload"){
		$('#frmFile')[0].reset();  // form 내의 기존 내용 reset
		$("#dlg_write .btn_group_header").hide();
		$("#dlg_file .btn_group_file").show();
		$("#dlg_write .btn_group_view").hide();
		$("#dlg_write .btn_group_update").hide();
		
		$("#dlg_file input[name='file']").attr("readonly", false);
		$("#dlg_file input[name='file']").css("border", "1px solid #ccc");
		$("#dlg_file input[name='memo']").attr("readonly", false);
		$("#dlg_file input[name='memo']").css("border", "1px solid #ccc");
	}
	} // end setPopup()
		


