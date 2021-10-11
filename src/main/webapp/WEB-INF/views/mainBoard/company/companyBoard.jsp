<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!-- 헤드 -->
<c:import url="../../layout/head.jsp"></c:import>
</head>

<body class="wrapper">

	<!-- 헤더 -->
	<c:import url="../../layout/header.jsp">
		<c:param name="headerMenu" value="company" />
	</c:import>

	<!-- content -->
	<div class="container-lg main-content">
		<iframe id="pageFrame" name="pageFrame" class="w-100" src="http://www.jobkorea.co.kr/Starter/calendar/sub/month" frameborder="0" height="1000px" scrolling="auto"></iframe>
	</div>
			
	<!-- 푸터 -->
	<c:import url="../../layout/footer.jsp"></c:import>
	
</body>
</html>