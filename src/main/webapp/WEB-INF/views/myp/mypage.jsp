<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="ko">
<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>RESUMY - 채용 도우미 사이트</title>

<!-- 파비콘 -->
<link href="/img/logo_sm.png" rel="shortcut icon" type="image/x-icon">

<!-- Bootstrap CSS -->
<link href="/assets/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- FontAwesome -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

<!-- Bootstrap JS -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<!-- CSS -->
<link href="/css/common.css" rel="stylesheet">
<link href="/css/header.css" rel="stylesheet">
<link href="/css/footer.css" rel="stylesheet">
<link href="/css/mypageSidebar.css" rel="stylesheet">

</head>

<body class="wrapper">

	<!-- 헤더 -->
	<c:import url="../layout/header.jsp">
		<c:param name="headerMenu" value="${headerMenu }" />
	</c:import>

	<div class="container main-content">

		<div class="row">

			<!-- 사이드 바 -->
			<c:import url="../layout/mypage_sidebar.jsp">
				<c:param name="menu" value="${menu }" />
			</c:import>

			<!-- 콘텐츠 -->
			<c:import url="/myp/${menu }">
				<c:param name="access" value="true" />
			</c:import>

		</div>

	</div>

	<!-- 푸터 -->
	<c:import url="../layout/footer.jsp">
	</c:import>

</body>
</html>