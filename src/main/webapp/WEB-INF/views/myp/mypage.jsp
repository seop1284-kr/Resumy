<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
	
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>



<body>

	<div class="container" style="margin-top: 100px">
		<div>

			<!-- 헤더 -->
			<c:import url="../layout/header.jsp">
				<c:param name="menu" value="${menu }"/>
			</c:import>		
			
			<hr>
			
			<div class="row">
			
				<!-- 사이드 바 -->
				<c:import url="../layout/mypage_sidebar.jsp">
					<c:param name="menu" value="${menu }"/>
				</c:import>		
				
				<!-- 콘텐츠 -->
				<c:import url="/myp/${menu }">
					<c:param name="access" value="true"/>
				</c:import>
				
			</div>
			
			<!-- 푸터 -->
			<c:import url="../layout/mypage_footer.jsp">
			</c:import>
			
			
		</div>
	</div>






</body>
</html>