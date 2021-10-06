<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!-- 헤드 -->
<c:import url="../../layout/head.jsp">
</c:import>

	<!-- My CSS -->
    <link href="/css/buttonSize.css" rel="stylesheet">
	<!-- <link rel="stylesheet" type="text/css" href="/CSS/fedBoard.css"> -->
    <link href="/css/qnaView.css" rel="stylesheet">

</head>

<body>
	
	<!-- 헤더 -->
	<c:import url="../../layout/header.jsp">
		<c:param name="headerMenu" value="fed" />
	</c:import>
	
	
	<!-- Begin Page Content -->
	<div class="container-lg mt-4">
		<h1 class="h3 text-gray-800 text-center" style="margin-bottom: 3em;">
            	<b>자소서 피드백 글</b>
        </h1>        
	
	
		<!-- 자소서 글 -->
		<div class="shadow">
			<c:choose>
				<c:when test="${empty introResult.conList || fn:length(introResult.conList) == 0 }">
				</c:when>
				<c:otherwise>
					
					<!-- 제목 -->
					<table class="tableInfo" width="100%" cellspacing="0">
						<tr>
							<td colspan="4"><b>${introResult.intro.title }</b></td>
						</tr>
						<tr>
							<td>작성자</td>
							<td class="text-gray-500"> ${introResult.intro.userid }</td>
							<td>등록일</td>
							<td class="text-gray-500">${introResult.intro.regdate }</td>
							<td>수정일</td>
							<td class="text-gray-500">${introResult.intro.modydate }</td>
						</tr>
					</table>
					
					
					<!-- 내용 -->
					<div class="textContent">
						<c:forEach var="con" items="${introResult.conList }"
							varStatus="status">
							<div>
								<div class="engBreakWord font-weight-bold mb-3">${status.count}.&nbsp;${con.question }</div>
								<div class="engBreakWord mb-5">${con.content }</div>
								
								<c:if test="${status.last eq false}">
									<hr>
								</c:if>
							</div>
							
						</c:forEach>
					</div>
					
		
				</c:otherwise>
			</c:choose>
		
		</div>
		<!-- 자소서 글 끝-->
	
	</div>	
	<!-- End Page Content -->
	
	
	
	
	
	
	<!-- 비로그인 -->
	<sec:authorize access="isAnonymous()">
		<p>회원만 볼 수 있습니다.</p>
	</sec:authorize>

	<!-- 로그인 했을 때 피드백 보기 및 댓글 달기 -->
	<sec:authorize access="isAuthenticated()">

		<!-- 댓글 달기 -->
		<form name="frm" action="fedCommentOk" method="post">
			<input type="hidden" name="iid" value="${introResult.intro.id }">
			<input type="text" name="content"
				style="display: block; width: 90vh" required />
			<button type="submit">댓글 작성</button>
		</form>


		<!-- 피드백 답변 -->
		<h5>댓글</h5>
		<c:choose>
			<c:when
				test="${empty introResult.fedList || fn:length(introResult.fedList) == 0 }">
			</c:when>
			<c:otherwise>

				<c:forEach var="fed" items="${introResult.fedList }"
					varStatus="status">

					<tr>
						<td>이름</td>
						<td>${fed.userid }</td>
						<td>/ 답변날짜</td>
						<td>${fed.regdate }</td>
						<br>
					</tr>
					<tr>
						<td>내용</td>
						<td>${fed.content }</td>
						<form name="frm" action="fedDeleteOk" method="put">
							<input type="hidden" name="iid" value="${introResult.intro.id }">
							<input type="hidden" name="id" value="${fed.id}">
							<button type="submit">댓글 삭제</button>
						</form>
					</tr>

					<hr>
					<br>
				</c:forEach>

			</c:otherwise>
		</c:choose>
	</sec:authorize>

	<form name=form1 action="fedBoard" method=get>
		<input type=hidden name="iid" value="${introResult.intro.id }">

		<button type=submit>목록</button>
	</form>

	
	<!-- 푸터 -->
	<c:import url="../../layout/footer.jsp">
	</c:import>
</body>
</html>
