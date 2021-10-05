<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<body></body>
<c:choose>
	<c:when test="${result == 0 }">
		<script>
			alert("댓글 등록 실패");
			hisotry.back();
		</script>
	</c:when>
	<c:otherwise>
		<script>
			var form = document.createElement('form');
			var contentObj;
			var content = ${param.iid};
			contentObj = document.createElement('input');
			contentObj.setAttribute('type', 'hidden');
			contentObj.setAttribute('name', 'id');
			contentObj.setAttribute('value', content);
			form.appendChild(contentObj);

			var headerMenuObj;
			headerMenuObj = document.createElement('input');
			headerMenuObj.setAttribute('type', 'hidden');
			headerMenuObj.setAttribute('name', 'headerMenu');
			headerMenuObj.setAttribute('value', "fed");
			form.appendChild(headerMenuObj);

			form.setAttribute('method', 'put');
			form.setAttribute('action', "/fedView");

			document.body.appendChild(form);

			form.submit();
			//location.href = "fedView?id=${param.iid}";
		<%-- 수정 성공하면 view 로 이동하여 제대로 수정되었는지 보여주는게 좋다--%>
			
		</script>
	</c:otherwise>
</c:choose>