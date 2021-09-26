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
					//addViewEvent();
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
	$(".modal .btn_group_file .btn_cancel").click(function(){
		$(this).parents(".modal").hide();
	});
	
// 모달 대화상자 업로드 버튼 누르면	
	$("#frmFile").submit(function(){
		$(this).parents(".modal").hide();
		
		return chkWrite();  // 새글 등록 submit
	});

// 다운로드 버튼

// 삭제 버튼 누르면
	$("#deleteBtn").click(function(){
		chkDelete();
	});



// 파일 업로드 대화상자 세팅
	function setPopup(mode){
		
		if(mode == "upload"){
		$('#frmFile')[0].reset();  // form 내의 기존 내용 reset
		//$("#dlg_write .btn_group_header").hide();
		$("#dlg_file .btn_group_file").show();
		//$("#dlg_write .btn_group_view").hide();
		//$("#dlg_write .btn_group_update").hide();
		
		$("#dlg_file input[name='file']").attr("readonly", false);
		$("#dlg_file input[name='file']").css("border", "1px solid #ccc");
		$("#dlg_file input[name='memo']").attr("readonly", false);
		$("#dlg_file input[name='memo']").css("border", "1px solid #ccc");
	}
} // end setPopup()
		

// 새글 등록 처리
function chkWrite(){
	
	// 특정 form 의 name 달린 form element 들의 value 들을 string 으로 묶기
	// ex) name=aaa&subject=bbb&content=ccc   <-- string 타입이다
	var data = $("#frmFile").serialize();
	//alert(data);
	
	$.ajax({
		url : "/fileAjax",  // url : /myp/file
		type : "POST",
		cache : false,
		data : data,  // POST 로 ajax request 할 경우 data 에 parameter 넘기기
		
		success : function(data, status){
			if(status == "success"){
				loadPage();
			}
		}
	});
	
	return false;
	
} // end chkWrite()		
		

// check 된 uid 의 게시글들만 삭제 하기
function chkDelete(){
	
	var uids = [];  // check 된 uid 들을 담을 배열
	$("#list tbody input[name=uid]").each(function(){
		if($(this).is(":checked")){   // jQuery 에서 check 여부 확인 함수
			uids.push(parseInt($(this).val()));  // uids 배열에 check 된 uid 값 추가
		}
	});
	
	//alert(uids);
	
	if(uids.length == 0){
		alert("삭제할 글을 체크해주세요");
	} else {
		if(!confirm(uids.length + "개의 글을 삭제하시겠습니까?")) return false;
		
		var data = $("#frmList").serialize();
		
		// DELETE 방식
		$.ajax({
			url: ".", // URL : /board
			type: "DELETE",
			data : data,
			cache : false,
			success : function(data, status){
				if(status == "success"){  // 200
					if(data.status == "OK"){
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

