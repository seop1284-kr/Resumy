// qnaMng(고객센터 관리 페이지) 노수빈

// 모달에 데이터 삽입
function setModalData() {
	// 답변하기 위해 아이콘 누를 때 (모달 활성화)
	$(".btn_replyWriteModal").click(function() {
		// qnaMng.jsp 에 Ajax 방식으로 데이터 삽입
		id = $(this).attr('data-id'); // 해당 글의 일련번호(q_id) 가져옴
		getQnaDTO(); // QnaDTO 정보 가져옴 (그 후에 view 에 가져온 정보 삽입)
		
		// 모달에서 답변삭제 버튼 (delete query 수행)
		$("#btn_delete").click(function() {
			$(location).attr("href", "qnaADeleteOk.do?id=" + id);
		});
	});
}

// QnaDTO 정보 가져옴
function getQnaDTO() {
	$.ajax({
		url : "/AjaxQnaBoard/list/" + id,
		type : "GET",
		cache : false,
		success : function(data, status) { // data 가 QnaDTO dto가 담김
			if(status == "success"){
				// view 에 QnaDTO 정보 삽입
				insertQnaDTOModal(data);
			} else {
				alert("잘못된 접근");
				console.log("qnaMng 페이지 로딩 실패");
			}
		}
	});
}

// view 에 QnaDTO 정보 삽입
function insertQnaDTOModal(data) {
	// 문의글 일련번호
	$("span.q_id").text(data.qdto.id);
	$("input[type='hidden'].q_id").val(data.qdto.id);
	
	// 문의글 작성자
	$("#q_name").text(data.name);
	
	// 문의글 업로드 날짜
	$("#q_regdate").text(data.qdto.regdate);
	
	// 문의글 제목
	$("#q_subject").text(data.qdto.subject);
	
	// 문의글 내용
	$("#q_content").text(data.qdto.content);
	
	// 문의글 답변
	$("#r_reply").val(data.adto.reply);
};
