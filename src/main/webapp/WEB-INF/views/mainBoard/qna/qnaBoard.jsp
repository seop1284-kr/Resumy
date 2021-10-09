<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>	

<%-- 헤드 --%>
<c:import url="../../layout/head.jsp"></c:import>

    <!-- My CSS -->
    <link href="/css/dataTables.css" rel="stylesheet">
    <link href="/css/buttonSize.css" rel="stylesheet">
</head>

<body class="wrapper">
	<c:import url="../../layout/header.jsp">
		<c:param name="headerMenu" value="qna" />
	</c:import>
	<%-- ./navbar --%>
    
    <!-- Begin Page Content -->
    <div class="container-lg mt-4 main-content">

        <!-- Page Heading -->
        <div class="d-s-flex align-items-center justify-content-between">
            <h1 class="h3 text-gray-800 text-center" style="margin-bottom: 3em;">
            	<b>고객센터</b>
            </h1>
        </div>
        
        <div class="mt-5 mb-2">현재 <span style="color:orange;">${fn:length(list) }건</span>의 게시물이 있습니다</div>
        
        <!-- DataTales Example -->
        <div class="table-responsive">
            <table class="table table-bordered table-layout-fixed" id="dataTable" width="100%" cellspacing="0">
            	<colgroup>
			        <col width="8%"/>
			        <col/>
			        <col width="8%"/>
			        <col width="10%"/>
			        <col width="15%"/>
			    </colgroup>
            	<thead>
            		<tr>
            			<th>번호</th>
            			<th>제목</th>
            			<th>답변</th>
            			<th>작성자</th>
            			<th>등록일</th>
            		</tr>
            	</thead>
            </table>
        </div>

    </div>
    <!-- /.container-lg -->
    
	<%-- 푸터 --%>
	<c:import url="../../layout/footer.jsp"></c:import>
	
    <!-- Core plugin JavaScript-->
    <script src="/assets/startbootstrap-sb-admin-2-gh-pages/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="/assets/startbootstrap-sb-admin-2-gh-pages/js/sb-admin-2.min.js"></script>

    <!-- Page level plugins -->
    <script src="/assets/startbootstrap-sb-admin-2-gh-pages/vendor/datatables/jquery.dataTables.min.js"></script>
    <script src="/assets/startbootstrap-sb-admin-2-gh-pages/vendor/datatables/dataTables.bootstrap4.min.js"></script>

    <!-- My Script -->
    <script src="/JS/qnaBoard.js"></script>
    <script>
    	$(function(){
	     	var insertStd = '#dataTable_paginate'; <%-- 어떤 요소를 기준으로 element를 삽입할 건지 --%>
    		var insertElement = '<button type="button" class="btn btn-mint btn_write float-right">문의하기</button>'; <%-- 삽입하려는 element --%>
			var locationElement = '.btn_write'; <%-- 클릭 시 경로변경 이벤트를 넣고싶은 elemtent --%>
	     	var locationUrl = 'write.do'; <%-- 이동할 경로의 url --%>
			
			<%-- 특정 요소의 바깥쪽 앞에 내용 삽입 (cf. after) --%>
	     	$(insertStd).before(insertElement);
			<%-- 클릭 시 url 로 이동 --%>
	    	$(locationElement).on('click', function(){location.href=locationUrl});
    	});
    </script>
</body>
</html>