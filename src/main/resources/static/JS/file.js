$(document).ready(function() {
	loadPage();

	// 업로드 버튼 누르면 모달 팝업
	$("#uploadBtn").click(function() {
		setPopup("upload");
		$("#dlg_file").show();
	});

	// 모달 대화상자 취소 버튼 누르면
	$(".modal .btn_group_file .btn_cancel").click(function() {
		$(this).parents(".modal").hide();
	});

	// 모달 대화상자 업로드 버튼 누르면	
	$(".btn_upload").click(function() {
		if(chkFileAndMemo()){
			$(this).parents(".modal").hide();
			chkUpload();
		}
	});

	// 다운로드 버튼 누르면
	$("#downloadBtn").click(function() {
		chkDownload();

	});

	// 삭제 버튼 누르면
	$("#deleteBtn").click(function() {
		chkDelete();
	});

});



// 파일 관리 리스트
function loadPage() {

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
	$("#fileCnt").text(count + "개");
	$("#leftCnt").text(15 - count + "개");
	$("#fileCnt2").text(count + "개");

	if (count == 0) {
		result += "<div class='thereIsNoFile'>\n";
		result += "앗? 파일이 하나도 없습니다  <i class='far fa-smile-wink'></i>\n";
		result += "</div>\n";

		$("#downloadBtn").hide();
		$("#deleteBtn").hide();

		$("#content").html(result);
		return false;
	} else {

		result += "<table class='fileList table-layout-fixed'>";
		result += "<colgroup>\n"
		result += "<col width='5%'/>\n"
		result += "<col width='45%'/>\n"
		result += "<col width='10%'/>\n"
		result += "<col width='25%'/>\n"
		result += "<col width='15%'/>\n"
		result += "</colgroup>\n"

		result += "<th><input type='checkbox' name='selectall' value='selectall' onclick='selectAll(this)'/></th>\n";
		result += "<th>첨부파일명</th>\n";
		result += "<th>용량</th>\n";
		result += "<th>등록일</th>\n";
		result += "<th>MEMO</th>\n";

		for (var i = 0; i < count; i++) {
			result += "<tr>\n";
			result += "<td style='text-align: center;'><input type='checkbox' name='id' value='" + items[i].id + "' onclick='checkSelectAll()'></td>\n";
			result += "<td class='ellipsis'>" + items[i].name + "</td>\n";
			result += "<td style='text-align: center;'>" + (items[i].volume / (1024 * 1024)).toFixed(2) + " mb" + "</td>\n";
			result += "<td style='text-align: center;'>" + items[i].regdate + "</td>\n";
			result += "<td>" + items[i].memo + "</td>\n";
			result += "</tr>\n";
		}
		result += "</table>";
		$("#downloadBtn").show();
		$("#deleteBtn").show();

		$("#content").html(result);

		return true;
	}
} // end updateList()


function selectAll(selectAll) {
	const checkboxes
		= document.querySelectorAll('input[type="checkbox"]'); //모든 input element 중 type이 체크박스인 목록들
	//querySelectorAll() 함수는 파라미터로 지정한 특정 element 목록 전체를 가져온다
	checkboxes.forEach((checkbox) => {
		checkbox.checked = selectAll.checked;
		// 반복문 사용 각 체크박스의 checked 값을 'SelectAll' element의 check 값(selectAll.checked)과 동일하게 변경
		// 따라서 'SelectAll' 체크박스가 선택되면 나머지 체크박스도 모두 선택되고, 해제되면 나머지 체크박스도 모두 해제됨


	})
} // end selectAll()


function checkSelectAll() {
	// 전체 체크박스
	const checkboxes
		= document.querySelectorAll('input[name="id"]');
	// 선택된 체크박스
	const checked
		= document.querySelectorAll('input[name="id"]:checked');
	// select all 체크박스
	const selectAll
		= document.querySelector('input[name="selectall"]');

	if (checkboxes.length === checked.length) {
		selectAll.checked = true;
	} else {
		selectAll.checked = false;
	}

	// animal 체크박스에 onclick 이벤트가 발생할 때마다
	// 전체 체크박스(checkboxes)와 선택된 체크박스(checked)의 갯수를 비교하여
	// 갯수가 같으면 selectall 체크박스가 선택되도록 하고,
	// 갯수가 같지 않으면 selectall 체크박스(selectAll)가 선택해제 되도록 하고 있다.

} // end checkSelectAll()





// 파일 업로드 대화상자 세팅
function setPopup(mode) {

	if (mode == "upload") {
		$('#frmFile')[0].reset();  // form 내의 기존 내용 reset
		$("#dlg_file .btn_group_file").show();

		$("#dlg_file input[name='file']").attr("readonly", false);
		$("#dlg_file input[name='file']").css("border", "1px solid #ccc");
		$("#dlg_file input[name='memo']").attr("readonly", false);
		$("#dlg_file input[name='memo']").css("border", "1px solid #ccc");
	}
} // end setPopup()


// 파일 업로드 처리
function chkUpload() {


	// 특정 form 의 name 달린 form element 들의 value 들을 string 으로 묶기
	// ex) name=aaa&subject=bbb&content=ccc   <-- string 타입이다
	var data = new FormData($("#frmFile")[0]);
	//alert(data);

	$.ajax({
		url: "/fileAjax",  // url : /myp/file
		type: "POST",
		cache: false,
		data: data,  // POST 로 ajax request 할 경우 data 에 parameter 넘기기
		// multipart를 위한 옵션
		processData: false,
		contentType: false,
		success: function(data, status) {
			if (status == "success") { // 200
				if (data == 0) {
					alert("파일의 총 개수는 15개를 초과할 수 없습니다.")
				}
				loadPage();
			}
		},
		error: function(error) {
			alert("파일은 5MB 이하 첨부 가능합니다" + error);
		}

	});


} // end chkUpload()		

// 파일 다운로드 처리
function chkDownload() {


	var ids = [];  // check 된 파일의 id 들을 담을 배열
	$("#content td input[name=id]").each(function() {
		if ($(this).is(":checked")) {   // jQuery 에서 check 여부 확인 함수
			ids.push(parseInt($(this).val()));  // ids 배열에 check 된 id 값 추가
		}
	});

	if (ids.length == 0) {
		alert("다운로드 할 파일을 체크해주세요");
	} else {
		var data = $("#fileList").serialize();
		$.ajax({
			url: "/fileAjax/download/" + ids,  // url : /myp/file
			type: "GET",
			cache: false,
			// multipart를 위한 옵션
			processData: false,
			contentType: false,
			success: function(data, status) {
				if (status == "success") {
					window.location = '/fileAjax/download/' + ids;
					loadPage();
				}
			}
		});
	}
}

// check 된 uid 의 게시글들만 삭제 하기
function chkDelete() {

	var ids = [];  // check 된 파일의 id 들을 담을 배열
	$("#content td input[name=id]").each(function() {
		if ($(this).is(":checked")) {   // jQuery 에서 check 여부 확인 함수
			ids.push(parseInt($(this).val()));  // ids 배열에 check 된 id 값 추가
		}
	});

	//alert(ids);

	if (ids.length == 0) {
		alert("삭제 할 파일을 체크해주세요");
	} else {
		if (!confirm(ids.length + "개의 체크한 파일을 삭제하시겠습니까?")) return false;

		var data = $("#fileList").serialize();
		//alert(data);
		// DELETE 방식
		$.ajax({
			url: "/fileAjax",
			type: "DELETE",
			data: data,
			cache: false,
			success: function(data, status) {
				if (status == "success") {  // 200
					loadPage();

				}
			}
		});
	}

} // end chkDelete()


function chkFileAndMemo() {

	var form = document.forms['frmFile'];
	var memoContent = form.memo.value.trim();
	var fileContent = form.file.value.trim();

	var memoPat = /^.{0,8}$/;
	if (!memoPat.test(memoContent)) {
		alert("메모는 8글자 이하로 입력해야합니다.")
		form.memo.focus();
		return false;
	}
	if (fileContent == ""){
		alert("첨부할 파일을 선택해 주세요")
		form.file.focus();
		return false;
	}
	return true;

}

