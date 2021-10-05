<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!-- 헤드 -->
<c:import url="./layout/head.jsp">
</c:import>
</head>

<body>

	<!-- 헤더 -->
	<c:import url="./layout/header.jsp">
		<c:param name="headerMenu" value="main" />
	</c:import>
			
			
	<h1>메인 페이지</h1>
			
	<!-- 푸터 -->
	<c:import url="./layout/footer.jsp">
	</c:import>
	
</body>
</html>