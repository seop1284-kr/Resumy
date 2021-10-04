var page = 1;   // 현재 페이지
var pageRows = 1000;   // 페이지당 글의 개수

$(document).ready(function() {
	// 페이지 최초 로딩되면 1페이지 내용을 로딩
	//loadPage(page);
	loadPageAll();

	// 글 삭제 버튼 누르면
	$("#btnDel").click(function() {
		chkDelete();
	});

});


// Korean
var lang_kor = {
	"decimal": "",
	"emptyTable": "데이터가 없습니다.",
	"info": "_START_ ~ _END_ 페이지 (총 _TOTAL_ 건)",
	"infoEmpty": "0 건",
	"infoFiltered": "(전체 _MAX_ 건의 검색결과)",
	"infoPostFix": "",
	"thousands": ",",
	"lengthMenu": "_MENU_ 개씩 보기",
	"loadingRecords": "로딩중...",
	"processing": "처리중...",
	"search": "검색 : ",
	"zeroRecords": "검색된 데이터가 없습니다.",
	"paginate": {
		"first": "<<",
		"last": ">>",
		"next": ">",
		"previous": "<"
	},
	"aria": {
		"sortAscending": " :  오름차순 정렬",
		"sortDescending": " :  내림차순 정렬"
	}
};


function loadPageAll() {
	$("#dataTable").DataTable({
		/* 기본 옵션 위치 설정
		   l : length changing input control
		   f : filtering input
		   t : the table
		   i : Table information summary
		   p : pagination control
		   r : processing display element
		*/
		dom: "fltip",
		language: lang_kor,
		order: [[0, 'desc']], // 기본 정렬칼럼 (0이 첫번째 칼럼)
		ordering: true, // 칼럼별 정렬기능
		serverSide: false,
		ajax: {
			url: "/AjaxMngFed/1/1000",
			type: "GET",
			dataSrc: 'data'
		},
		columns: [
			{ data: "introDto.id" },
			{
				data: "introDto",
				render: function(data, type, row) {
					return "<a class='left' onclick='addViewEvent(" + data.id + ")'>" + data.title +"</a>";
				}
			},
			{ 
				data: "fedDto.content",
				render: function(data, row) {
					return "<span class='left'>" + data + "</span>";
				}
			},
			{ data: "fedDto.userid" },
			{ data: "fedDto.regdate" },
			{
				data: "fedDto",
				render: function(data, type, row) {

					return "<input type='checkbox' name='uid' value='" + data.id + "'>";
				}
			}
		]

	});
}

// check 된 uid 의 게시글들만 삭제 하기
function chkDelete() {

	var uids = [];  // check 된 uid 들을 담을 배열
	$("#dataTable tbody input[name=uid]").each(function() {
		if ($(this).is(":checked")) {   // jQuery 에서 check 여부 확인 함수
			uids.push(parseInt($(this).val()));  // uids 배열에 check 된 uid 값 추가
		}
	});

	//alert(uids);

	if (uids.length == 0) {
		alert("삭제할 글을 체크해주세요");
	} else {
		if (!confirm(uids.length + "개의 글을 삭제하시겠습니까?")) return false;

		var data = $("#frmList").serialize();
		// DELETE 방식
		$.ajax({
			url: "/AjaxMngFed/",
			type: "DELETE",
			data: data,
			cache: false,
			success: function(data, status) {
				if (status == "success") {  // 200
					if (data.status == "OK") {
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


// 현재 글 목록 list 에 대해 이벤트 등록
// - 제목(title) 클릭하면 view 팝업 화면 뜰수 있게 하기
function addViewEvent(id) {
	var form = document.createElement('form');
	var contentObj;
	var content = "fedView";
	contentObj = document.createElement('input');
	contentObj.setAttribute('type', 'hidden');
	contentObj.setAttribute('name', 'content');
	contentObj.setAttribute('value', content);
	form.appendChild(contentObj);

	var viewIdObj;
	viewIdObj = document.createElement('input');
	viewIdObj.setAttribute('type', 'hidden');
	viewIdObj.setAttribute('name', 'id');
	viewIdObj.setAttribute('value', id);
	form.appendChild(viewIdObj);

	var headerMenuObj;
	headerMenuObj = document.createElement('input');
	headerMenuObj.setAttribute('type', 'hidden');
	headerMenuObj.setAttribute('name', 'headerMenu');
	headerMenuObj.setAttribute('value', "fed");
	form.appendChild(headerMenuObj);

	form.setAttribute('method', 'post');
	form.setAttribute('action', "/");

	document.body.appendChild(form);

	form.submit();


} // end addViewEvent()


// 특정 uid 의 글 삭제하기
function deleteUid(uid) {

	if (!confirm(uid + "글을 삭제하시겠습니까?")) return false;

	// DELETE 방식
	$.ajax({
		url: "/AjaxMngFed/",  //
		type: "DELETE",
		data: "uid=" + uid,
		cache: false,
		success: function(data, status) {
			if (status == "success") {
				if (data.status == "OK") {
					alert("DELETE성공: " + data.count + "개");
					loadPage(window.page);  // 현재 페이지 리로딩
				} else {
					alert("DELETE실패: " + data.message);
					return false;
				}
			}
		}
	});

	return true;
} // end deleteUid()

















