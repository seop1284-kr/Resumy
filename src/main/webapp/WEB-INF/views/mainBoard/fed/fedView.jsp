<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>



<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<body>

	<div class="container" style="margin-top: 100px">
		<div>
			<tr>
				<td>${introResult.intro.title }</td>
				<hr>
			</tr>
			<c:choose>
				<c:when
					test="${empty introResult.conList || fn:length(introResult.conList) == 0 }">
				</c:when>
				<c:otherwise>
					<tr>
						<td>수정 날짜</td>
						<td colspan="4">${introResult.intro.modydate }</td>
						<hr>
					</tr>
					<c:forEach var="con" items="${introResult.conList }"
						varStatus="status">


						<tr>
							<td>질문 ${status.count}</td>
							<br>
							<td>${con.question }</td>
							<br>

							<td>내용</td>
							<br>
							<td>${con.content }</td>
							<br>
							<br>
						</tr>
						<hr>
					</c:forEach>

				</c:otherwise>
			</c:choose>
			<!-- 문의글 -->


			<form name="frm" action="fedCommentOk" method="post">
				<input type="hidden" name="iid" value="${introResult.intro.id }">
				<input type="text" name="content"
					style="display: block; width: 90vh" />
				<button type="submit">댓글 작성</button>
			</form>
			<!-- 댓글 달기 -->


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
						</tr>
						<hr>
						<br>
					</c:forEach>

				</c:otherwise>
			</c:choose>
			<!-- 답변 -->
			<form name=form1 action="/" method=post>
				<input type=hidden name="headerMenu" value="fed">
				<input type=hidden name="content" value="fedBoard">
				<button type=submit>목록</button>
			</form>
		</div>
	</div>
</body>