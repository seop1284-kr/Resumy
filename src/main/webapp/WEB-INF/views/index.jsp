<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<c:import url="./layout/head.jsp"></c:import>
<%-- 헤드 끝 --%>
</head>

<body>
	<c:import url="./layout/header.jsp">
		<c:param name="headerMenu" value="main" />
	</c:import>
	<%-- 헤더 끝 --%>
			
	<h1>메인 페이지</h1>
			
	<c:import url="./layout/footer.jsp"></c:import>
	<%-- 푸터 끝 --%>
</body>
</html>