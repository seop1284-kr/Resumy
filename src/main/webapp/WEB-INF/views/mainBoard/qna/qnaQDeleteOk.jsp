<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
    
<c:choose>
	<c:when test="${result < 0 }">
		<script>
			alert("다른 사용자의 문의글은 삭제할 수 없습니다.");
			location.href = "qnaView.do?id=${param.id}";
		</script>
	</c:when>
	<c:otherwise>
		<script>
			alert("삭제 완료");
			location.href = "qnaBoard.do";
			<%-- 여유될 때 Board 1페이지로 돌아가는 게 아닌, 보고있던 페이지의 Board 로 돌아가게 코드 수정 --%>
		</script>
	</c:otherwise>
</c:choose>