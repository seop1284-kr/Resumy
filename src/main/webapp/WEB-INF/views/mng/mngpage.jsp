<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="ko">
<head>

<title>RESUMY - 채용 도우미 사이트</title>

<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

</head>


<body>

	<div class="container" style="margin-top: 100px">
		<div>

			<!-- 헤더 -->
			<c:import url="../layout/header.jsp">
				<c:param name="headerMenu" value="${headerMenu }" />
			</c:import>

			<div class="row">

				<!-- 사이드 바 -->
				<c:import url="../layout/mngpage_sidebar.jsp">
					<c:param name="menu" value="${menu }" />
				</c:import>

				<!-- 콘텐츠 -->
				<c:import url="/mng/${menu }">
					<c:param name="access" value="true" />
				</c:import>

			</div>

			<!-- 푸터 -->
			<c:import url="../layout/footer.jsp">
			</c:import>


		</div>
	</div>

</body>
</html>