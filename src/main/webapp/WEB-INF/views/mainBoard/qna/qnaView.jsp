<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    

<%-- 헤드 --%>
<c:import url="../../layout/head.jsp"></c:import>
	
	<!-- My CSS -->
    <link href="/css/buttonSize.css" rel="stylesheet">
    <link href="/css/qnaView.css" rel="stylesheet">
</head>

<body class="text-gray-800">
	<c:import url="../../layout/header.jsp">
		<c:param name="headerMenu" value="qna" />
	</c:import>
	<%-- ./navbar --%>
    
    <!-- Begin Page Content -->
    <div class="container-lg mt-4">

        <!-- Page Heading -->
        <div class="d-s-flex align-items-center justify-content-between">
            <h1 class="h3 text-gray-800 text-center" style="margin-bottom: 3em;">
            	<b>문의글 읽기</b>
            </h1>
        </div>
        
       	<!-- 문의글 -->
       	<div class="shadow">
	        <c:choose>
				<c:when test="${empty qdto }">
				</c:when>
				<c:otherwise>
				
					<!-- tableInfo -->
					<table class="tableInfo" width="100%" cellspacing="0">
						<tr>
							<td colspan="4"><b>${qdto.subject }</b></td>
						</tr>
						<tr>
							<td>작성일</td>
							<td class="text-gray-500">${qdto.regdate }</td>
							<td>작성자</td>
							<td class="text-gray-500">${userName }</td>
						</tr>
					</table>
					
					<!-- tableContent -->
					<div class="textContent engBreakWord">${qdto.content }</div>
					
				</c:otherwise>
			</c:choose>
		</div>
		<!-- ./문의글 -->
		
		<h4><b>답변</b></h4>
				
		<!-- tableRply -->
		<table class="tableRply shadow" width="100%" cellspacing="0">
			<c:choose>
				<c:when test="${empty adto }">
					<tr>
						<td class="text-gray-500">
							<i class="fas fa-spinner fa-spin mr-2"></i>
							질문하신 내용에 답변을 준비하고 있습니다.
						</td>
					</tr>
				</c:when>
				<c:otherwise>
			
					<tr class="ReplyBasicInfo">
						<td class="img"><i class="fas fa-user-circle text-gray-400" style="font-size: 1.5em;"></i></td>
						<td class="name">admin</td>
						<td class="regdate text-gray-500">${adto.regdate }</td>
					</tr>
					<tr class="ReplyContent">
						<td colspan="3" class="engBreakWord">${adto.reply }</td>
					</tr>
				
				</c:otherwise>
			</c:choose>
		</table>
		<!-- ./답변 -->
		
		<div class="d-flex flex-row-reverse">
			<button type="button" class="btn btn-mint btn_del order-3" onClick="chkDelete(${qdto.id })">삭제</button>
			<button type="button" class="btn btn-mint btn_update order-2" onClick="location.href='update.do?id=${qdto.id }'">수정</button>
			<button type="button" class="btn btn-mint btn_list order-1" onClick="location.href='board.do'">목록</button>
		</div>
			
    </div>
    <!-- /.container-lg -->
    
	<%-- 푸터 --%>
	<c:import url="../../layout/footer.jsp"></c:import>
	
	<!-- My JS -->
	<script>
		function chkDelete(id) {
			var check = confirm("삭제하시겠습니까?");
			if (check) {
				location.href = "deleteOk.do?id=" + id;
			}
		}
	</script>
	
</body>
</html>