<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    

<%-- 헤드 --%>
<c:import url="../../layout/head.jsp"></c:import>

    <!-- My CSS -->
    <link href="/css/dataTables.css" rel="stylesheet">
    <link href="/css/buttonSize.css" rel="stylesheet">
    <link href="/css/qnaWrite.css" rel="stylesheet">
</head>

<body>
    <c:import url="../../layout/header.jsp">
		<c:param name="headerMenu" value="qna" />
	</c:import>
    <%-- ./navbar --%>
    
    <!-- Begin Page Content -->
    <div class="container-lg mt-4">

        <!-- Page Heading -->
        <div class="d-s-flex align-items-center justify-content-between">
            <h1 class="h3 text-gray-800 text-center" style="margin-bottom: 3em;">
            	<b>문의하기</b>
            </h1>
        </div>
    	
    	<!-- 문의글 작성 폼 -->
	    <form name="frm" class="container-fluid d-flex flex-column" id="frm" action="writeOk.do" method="post">
	    	<ul class="row">
	    		<li class="col-2 text-gray-600">제목 <span style="color: red;">*</span></li>
	    		<li class="col-10"><input type="text" name="subject" placeholder="제목을 입력해주세요." required></li>
	    		<li class="col-2 text-gray-600">내용 <span style="color: red;">*</span></li>
	    		<li class="col-10"><textarea name="content" placeholder="내용을 입력해주세요." required></textarea></li>
	    	</ul>
	    	<div class="d-flex justify-content-center">
			    <button type="submit" class="btn btn-mint" id="btn_submit">등록하기</button>
	    	</div>
	    </form>
	    
	</div>
    <!-- /.container-lg -->
    
    <%-- 푸터 --%>
	<c:import url="../../layout/footer.jsp"></c:import>
</body>
</html>