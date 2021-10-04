<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    
	<title>Resumy 관리자 페이지</title>

	 <!-- Custom fonts for this template -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/css/sb-admin-2.min.css" rel="stylesheet">

    <!-- Custom styles for this page -->
    <link href="/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
    <link href="/css/dataTables.css" rel="stylesheet">
    <style>
        /* 게시물 목록 */
        	/* 답변상태 */
        	.replyStateYes {
        		color: #4CAF50;
        	}
	        /* 답변 */
	        tbody td>button {
	        	border:none; 
	        	background:none;
	        }
        
         /* [페이징] */
		.center {
			text-align: center;
		}
		
		ul.pagination{
			list-style-type: none;
		}
		
		ul.pagination li{
			display: inline-block;
		}
		
		ul.pagination a {
			color: black;
			float: left;
			padding: 4px 8px;
			text-decoration: none;
			transition: background-color .3s;
			margin: 0px;
		}
		
		ul.pagination a.active {
			background-color: #4CAF50;
			color: white;
			border: 1px solid #4CAF50;
		}
		
		ul.pagination a:hover:not(.active) {background-color: #ddd;}
		
		/* 모달 */
		input[name="reply"] {
			width: 100%;
		}
    </style>
</head>

<body id="page-top">

	<!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
        <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

            <!-- Sidebar - Brand -->
            <a class="sidebar-brand d-flex align-items-center justify-content-center my-2" href="indexMng.html">
            </a>

            <!-- Divider -->
            <hr class="sidebar-divider">

            <!-- Heading -->
            <div class="sidebar-heading">
                바로가기
            </div>

            <!-- Nav Item - 홈페이지 -->
            <li class="nav-item">
                <a class="nav-link" href="index.html">
                    <i class="fas fa-home"></i>
                    <span>홈페이지</span></a>
            </li>

            <!-- Nav Item - 로그아웃 -->
            <li class="nav-item" style="margin-top: -1em;">
                <div class="nav-link" data-toggle="modal" data-target="#logoutModal">
                    <i class="fas fa-power-off"></i>
                    <span>로그아웃</span></div>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider">

            <!-- Heading -->
            <div class="sidebar-heading">
                관리하기
            </div>

            <!-- Nav Item - 자소서 피드백 관리 -->
            <li class="nav-item">
                <a class="nav-link" href="/mng/fed">
                    <i class="far fa-eye"></i>
                    <span>자소서 피드백</span></a>
            </li>

            <!-- Nav Item - 고객센터 답변 관리 -->
            <li class="nav-item active">
                <a class="nav-link" href="/mng/qna/board.do">
                    <i class="fas fa-comment-alt"></i>
                    <span>고객센터 답변</span></a>
            </li>

            <!-- Nav Item - 회원 관리 -->
            <li class="nav-item">
                <a class="nav-link" href="/mng/member">
                    <i class="fas fa-fw fa-table"></i>
                    <span>회원</span></a>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider d-none d-md-block">

            <!-- Sidebar Toggler (Sidebar) -->
            <div class="text-center d-none d-md-inline">
                <button class="rounded-circle border-0" id="sidebarToggle"></button>
            </div>

        </ul>
        <!-- End of Sidebar -->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- Topbar -->
                <nav class="navbar navbar-expand navbar-light bg-white d-xs-block d-md-none topbar static-top shadow">

                    <!-- Sidebar Toggle (Topbar) -->
                    <form class="form-inline">
                        <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                            <i class="fa fa-bars"></i>
                        </button>
                    </form>

                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid mt-4">

                    <!-- Page Heading -->
                    <div class="d-s-flex align-items-center justify-content-between mb-2">
                        <h1 class="h3 mb-0 text-gray-800">
                        	고객센터 답변 관리
                        </h1>
                    </div>
                    <div class="mb-4">
                        답변 아이콘을 누르면 모달이 나옵니다. 이를 통해 문의글에 답변등록/답변삭제를 할 수 있습니다. 삭제 체크박스를 누른 다음 항목 삭제 버튼을 누르면 문의글이 삭제됩니다.
                    </div>
    
    				<!-- DataTales Example -->
    				 <div class="card shadow mb-4">
                        <div class="card-body">
                            <div class="table-responsive">
								<form name="frmChk" action="qnaQDeleteOk.do" method="get" onsubmit="return frmChkSubmit();">
                            	    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
								    	<thead>
								    		<tr>
									    		<th>번호</th>
									    		<th>제목</th>
									    		<th>내용</th>
									    		<th>등록일</th>
									    		<th>답변상태</th>
									    		<th>답변</th>
									    		<th>삭제</th>
									    	</tr>
								    	</thead>
								    	<tbody>
								    	</tbody>
							 		</table>
							 	</div>
							 </div>
						</div>
						
					</div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>
	
	<!-- ================================================================================================== -->
	<!-- 답변하기 Modal -->
	<div class="modal fade" id="replyWriteModal" tabindex="-1" aria-labelledby="replyWriteModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	     	<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
			  <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
			  <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
			</svg>
	        <span class="modal-title" id="exampleModalLabel">
	        	<span class="q_id">00</span>
	        	번 문의글 답변 작성하기</span>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
		  	<form name="frm" action="updateOk.do" method="post">
		       	<input type="hidden" name="id" class="q_id">
		    	<ul class="row">
		        	<li class="col-2">작성자</li>
		        	<li class="col-3" name="name" id="q_name"></li>
		        	<li class="col-2">날짜</li>
		        	<li class="col" name="regdate" id="q_regdate"></li>
		        </ul>
		        <ul class="row">
		        	<li class="col-2">제목</li>
		        	<li class="col" name="subject" id="q_subject"></li>
		        </ul>
		        <ul class="row">
		        	<li class="col-12">내용</li>
		        	<li class="col-12" name="content" id="q_content"></li>
		        </ul>
		        <input type="text" name="reply" id="r_reply" placeholder="답변을 적어주세요.">
		        <button type="button" class="btn btn-secondary" id="btn_delete">답변 삭제</button>
		        <button type="submit" class="btn btn-primary">답변 완료</button>
			</form>
	      </div>
	    </div>
	  </div>
	</div>
	<!-- ./답변 모달 -->
	
	<!-- Logout Modal-->
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="modalLabelHeader"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="modalLabelHeader">로그아웃을 하시겠습니까?</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">확인을 누르면 관리자 계정에서 로그아웃한 뒤 홈페이지로 돌아가게 됩니다.</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">취소</button>
                    <a class="btn btn-primary" href="index.html">확인</a>
                </div>
            </div>
        </div>
    </div>
	
	<!-- Bootstrap core JavaScript-->
    <script src="/vendor/jquery/jquery.min.js"></script>
    <script src="/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="/js/sb-admin-2.min.js"></script>

    <!-- Page level plugins -->
    <script src="/vendor/datatables/jquery.dataTables.min.js"></script>
    <script src="/vendor/datatables/dataTables.bootstrap4.min.js"></script>

    <!-- Page level custom scripts -->
    <script src="/js/sidebarMng.js"></script>
    <script src="/js/datatables.js"></script>
    <script src="/JS/qnaMngBoard.js"></script>
    <script src="/JS/qnaMngModal.js"></script>
</body>
</html>