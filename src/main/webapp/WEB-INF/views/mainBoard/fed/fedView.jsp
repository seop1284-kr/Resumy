<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!-- 헤드 -->
<c:import url="../../layout/head.jsp">
</c:import>

<!-- My CSS -->
<link rel="stylesheet" type="text/css" href="/CSS/fedBoard.css">

</head>

<body>
	
	<!-- 헤더 -->
	<c:import url="../../layout/header.jsp">
		<c:param name="headerMenu" value="fed" />
	</c:import>

	<tr>
		<td>자소서 제목: ${introResult.intro.title }</td>
		<hr>
	</tr>
	<c:choose>
		<c:when
			test="${empty introResult.conList || fn:length(introResult.conList) == 0 }">
		</c:when>
		<c:otherwise>
			<tr>
				<td colspan="4">작성자 : ${introResult.intro.userid }</td>
				<hr>
				<td colspan="4">공개 날짜 : ${introResult.intro.modydate }</td>
				<hr>
			</tr>
			<c:forEach var="con" items="${introResult.conList }"
				varStatus="status">

				<tr>
					<td>질문 ${status.count} :</td>

					<td>${con.question }</td>
					<br>

					<td>${con.content }</td>
					<br>
					<br>
				</tr>
				<hr>
			</c:forEach>

		</c:otherwise>
	</c:choose>
	
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
