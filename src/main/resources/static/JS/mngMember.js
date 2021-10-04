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
		"pageLength": 10,
		ajax: {
			url: "/AjaxMngMember/1/100",
			type: "GET",
			dataSrc: 'data'
		},
		columns: [
			{ data: "name" },
			{ data: "userid" },
			{ data: "email" },
			{ data: "phone" },
			{ data: "birthday" },
			{ data: "regdtm" },
			{
				data: "",
				render: function(data, type, row) {

					return "<input type='checkbox' name='uid' value='" + row.id + "'>";
				}
			}
		]

	});
}

// page번째 페이지 목록 읽어오기
function loadPage(page) {

	$.ajax({
		url: "/AjaxMngMember/" + page + "/" + pageRows,
		type: "GET",
		cache: false,
		success: function(data, status) {
			if (status == "success") {
				//alert("목록 가져오기 성공:  뿌~~");

				// response 가 application/json 이면 이미 parse된 결과가 data 에 담겨 있다.

				if (updateList(data)) {
				}

			}
		}

	});

} // end loadPage()


// 목록 업데이트
// 성공하면 true, 실패하면 false 리턴
function updateList(jsonObj) {
	var result = "";  // 최종 결과

	if (jsonObj.status == "OK") {
		var count = jsonObj.count;

		window.page = jsonObj.page;
		window.pageRows = jsonObj.pagerows;

		var items = jsonObj.data;  // 배열
		for (var i = 0; i < count; i++) {
			result += "<tr>\n";
			result += "<td>" + items[i].name + "</td>\n";
			result += "<td>" + items[i].userid + "</td>\n";
			result += "<td>" + items[i].email + "</td>\n";
			result += "<td>" + items[i].phone + "</td>\n";
			result += "<td>" + items[i].birthday + "</td>\n";
			result += "<td>" + items[i].regdtm + "</td>\n";
			result += "<td><input type='checkbox' name='uid' value='" + items[i].id + "'></td>\n";
			result += "</tr>\n";
		}
		$("#dataTable tbody").append(result);  // 업데이트

	} else {
		alert("내용이 없습니다");
		return false;
	}

	return true;
} // end updateList()




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
		alert("삭제할 회원을 체크해주세요");
	} else {
		if (!confirm(uids.length + "명의 회원을 삭제하시겠습니까?")) return false;

		var data = $("#frmList").serialize();
		alert(data);
		// DELETE 방식
		$.ajax({
			url: "/AjaxMngMember/",
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

















