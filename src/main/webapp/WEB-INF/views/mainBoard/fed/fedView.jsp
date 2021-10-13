<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!-- 헤드 -->
<c:import url="../../layout/head.jsp">
</c:import>

	<!-- My CSS -->
    <link href="/css/buttonSize.css" rel="stylesheet">
    <link href="/css/fedView.css" rel="stylesheet">

</head>

<body class="wrapper text-gray-800">
	
	<!-- 헤더 -->
	<c:import url="../../layout/header.jsp">
		<c:param name="headerMenu" value="fed" />
	</c:import>
	
	
	<!-- Begin Page Content -->
	<div class="container-lg mt-4 main-content">
		<h1 class="h3 text-gray-800 text-center" style="margin-bottom: 3em;">
            	<b>자소서 피드백 글</b>
        </h1>        
	
	
		<!-- 자소서 글 -->
		<div class="shadow">
			<c:choose>
				<c:when test="${empty introResult.conList || fn:length(introResult.conList) == 0 }">
				</c:when>
				<c:otherwise>
					
					<!-- 제목 -->
					<table class="tableInfo" width="100%" cellspacing="0">
						<tr>
							<td colspan="4"><b>${introResult.intro.title }</b></td>
						</tr>
						<tr>
							<td>작성자</td>
							<td class="text-gray-500"> ${introResult.intro.userid }</td>
							<td>등록일</td>
							<td class="text-gray-500">${introResult.intro.regdate }</td>
							<td>수정일</td>
							<td class="text-gray-500">${introResult.intro.modydate }</td>
						</tr>
					</table>
					
					
					<!-- 내용 -->
					<div class="textContent">
						<c:forEach var="con" items="${introResult.conList }" varStatus="status">
							<div>
								<div class="engBreakWord font-weight-bold mb-3">${status.count}.&nbsp;${con.question }</div>
								<div class="engBreakWord mb-5">${con.content }</div>
								
								<c:if test="${status.last eq false}">
									<hr>
								</c:if>
							</div>
							
						</c:forEach>
					</div>
					
		
				</c:otherwise>
			</c:choose>
		
		</div>
		<!-- 자소서 글 끝-->
	
		<!-- 비로그인 -->
		<sec:authorize access="isAnonymous()">
			<h4><b>댓글</b></h4>
			<table class="tableRply shadow" width="100%" cellspacing="0">
				<tr>
					<td class="text-gray-500">
						회원만 볼 수 있습니다.
					</td>
				</tr>
			</table>
			
		</sec:authorize>
	
		<!-- 로그인 했을 때 피드백 보기 및 댓글 달기 -->
		<sec:authorize access="isAuthenticated()">
	
			<!-- 댓글 달기 -->
			<h4><b>댓글 작성</b></h4>
			<form name="frm" action="fedCommentOk" method="post">
				<input type="hidden" name="iid" value="${introResult.intro.id }">
				<textarea class="w-100 mb-4 p-2" name="content" style="display: block; height: 7em" maxlength="500" required></textarea>
				<div class="d-flex flex-row-reverse">
					<button type="submit" class="btn btn-mint">댓글 작성</button>
				</div>
			</form>
	
	
			<h4><b>댓글</b></h4>
			<!-- 피드백 답변 -->
			<table class="tableRply shadow" width="100%" cellspacing="0">
				<c:choose>
					<c:when test="${empty introResult.fedList || fn:length(introResult.fedList) == 0 }">
						<tr class="fedContent">
							<td class="text-gray-500">
								<i class="fas fa-spinner fa-spin mr-2"></i>
								아직 댓글이 없습니다.
							</td>
						</tr>
					</c:when>
					
					<c:otherwise>
						<c:forEach var="fed" items="${introResult.fedList }" varStatus="status">
							
								<!-- 등록 댓글 사용자 기본정보 -->
								<tr class="notEmpty basicInfo">
									<td>
										<i class="fas fa-user-circle text-gray-400" style="font-size: 1.5em;"></i>
									</td>
									<td class="d-flex align-items-center">
										<div class="mr-2">${fed.userid }</div>
										<div class="text-gray-500">${fed.regdate }</div>
									</td>
									<td class="pr-3">
										<form name="frm" action="fedDeleteOk" method="put">
											<input type="hidden" name="iid" value="${introResult.intro.id }">
											<input type="hidden" name="id" value="${fed.id}">
											<button type=submit class="btn float-right btn-del-reply bg-danger">
												<i class="fas fa-trash-alt text-white"></i>
											</button>
										</form>
									</td>
								</tr>
								
								<!-- 댓글 내용 -->
								<tr class="notEmpty fedContent">
									<td colspan="3">
										<div class="engBreakWord">${fed.content }</div>
									</td>
								</tr>
								
								<!-- 구분선 -->
								<tr class="notEmpty lastLine">
									<td colspan="3">
										<c:if test="${status.last eq false}">
											<hr class="m-0">
										</c:if>
									</td>
								</tr>
								
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</table>
			<!-- ./답변 -->
			
			
		</sec:authorize>
	
		<form name=form1 action="fedBoard" method=get>
			<input type=hidden name="iid" value="${introResult.intro.id }">
			<button type=submit class="btn btn-mint float-right">목록</button>
			
		</form>
		
	</div>	
	<!-- End Page Content -->
	
	<!-- 푸터 -->
	<c:import url="../../layout/footer.jsp">
	</c:import>
</body>
</html>
