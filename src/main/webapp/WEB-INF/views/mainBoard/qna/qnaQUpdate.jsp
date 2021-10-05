<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    

<%-- 헤드 --%>
<c:import url="../../layout/head.jsp"></c:import>

	<!-- My CSS -->
	<link href="/css/buttonSize.css" rel="stylesheet">
	<link href="/css/qnaView.css" rel="stylesheet">
	<link href="/css/qnaWrite.css" rel="stylesheet">
	<style>
		@keyframes blink-effect { 50% { opacity: 0.2; } }
		.blink { 
			animation: blink-effect 1s;
			animation-delay: 2s; /* 지연시간 */
			animation-iteration-count: 2; /* 반복 횟수 */
		}
	</style>
</head>

<body class="text-gray-800">
	<%-- result == 0 의미 : 1 또는 2 만족
		1) 현재 로그인한 계정 != 문의글 작성한 계정
		2) 문의글에 답글 있음 --%> 
	<c:choose>
	
		<%-- 경고창 팝업만 띄움 --%>
		<c:when test="${result != 0 }">
			<script>
				alert("다른 사용자의 문의글이나 답변이 달린 문의글은 수정할 수 없습니다.");
				history.back();
			</script>
		</c:when>
		
		<%-- 글 수정 페이지 보여줌 --%>
		<c:otherwise>
		    <c:import url="../../layout/header.jsp">
				<c:param name="headerMenu" value="qna" />
			</c:import>
			<%-- ./navbar --%>
			
			<!-- Begin Page Content -->
			<div class="container-lg mt-4">
		
				<!-- Page Heading -->
				<div class="d-s-flex align-items-center justify-content-between">
					<h1 class="h3 text-gray-800 text-center" style="margin-bottom: 3em;">
						<b>문의글 수정하기</b>
			       </h1>
					<p class="blink" style="color: var(--mint-color)">* 작성하신 내용을 수정하신 후 수정완료 버튼을 눌러주세요.</p>
				</div>
		       
				
				<!-- 문의글 수정 -->
				<form name="frm" class="d-flex flex-column" action="updateOk.do" method="post">
					<input type="hidden" name="id" value="${qdto.id }">
					
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
					<div class="textContent p-0">
						<textarea name="content" class="border-0 p-3 blink" required>${qdto.content }</textarea>
					</div>
					
					<!-- submit button -->
					<div class="d-flex justify-content-center">
						<button type="submit" class="btn btn-mint">수정완료</button>
					</div>
				</form>
				
			</div>
			<!-- ./Begin Page Content -->
			
		</c:otherwise>
		
	</c:choose>
		
	<!-- 푸터 -->
	<c:import url="../../layout/footer.jsp"></c:import>
</body>
</html>