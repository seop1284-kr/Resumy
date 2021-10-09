<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!-- 헤드 -->
<c:import url="../../layout/head.jsp">
</c:import>
</head>

<body class="wrapper">

	<!-- 헤더 -->
	<c:import url="../../layout/header.jsp">
		<c:param name="headerMenu" value="company" />
	</c:import>
			
			
	<div class="container-lg main-content">
		<h1>기업정보 페이지</h1>
		<ul id="infoList" class="list"></ul>
	</div>
			
	<!-- 푸터 -->
	<c:import url="../../layout/footer.jsp">
	</c:import>
	
</body>
</html>