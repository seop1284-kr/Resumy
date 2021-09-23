<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
    
<c:choose>
	<c:when test="${result < 0 }">
		<script>
			alert("글을 삭제할 권한이 없습니다.");
			location.href = "qnaView.do?id=${param.id}";
		</script>
	</c:when>
	<c:otherwise>
		<script>
			alert("삭제 완료");
			location.href = "qnaBoard.do";
		</script>
	</c:otherwise>
</c:choose>