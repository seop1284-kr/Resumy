<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
    
<c:choose>
	<c:when test="${result > 0 }">
		<script>
			alert("회원가입이 완료되었습니다.");
			location.href = "/login";
		</script>
	</c:when>
	<c:otherwise>
		<script>
			alert("회원가입에 실패하였습니다.");
			history.back();
		</script>
	</c:otherwise>
</c:choose>