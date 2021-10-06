<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    
	<title>Resumy 관리자 페이지</title>
    <link href="/img/logo_sm.png" rel="shortcut icon" type="image/x-icon">

	<!-- Custom fonts for this template -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/css/sb-admin-2.min.css" rel="stylesheet">

    <!-- Custom styles for this page -->
    <link href="/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
    <link href="/css/dataTables.css" rel="stylesheet">
    <link href="/css/common.css" rel="stylesheet">
    
</head>




<body id="page-top">

	<!-- Page Wrapper -->
	<div id="wrapper">

		<!-- Sidebar -->
		<ul
			class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
			id="accordionSidebar">

			<!-- Sidebar - Brand -->
			<a
				class="sidebar-brand d-flex align-items-center justify-content-center my-2"
				href="/mng"> </a>

			<!-- Divider -->
			<hr class="sidebar-divider">

			<!-- Heading -->
			<div class="sidebar-heading">바로가기</div>

			<!-- Nav Item - 홈페이지 -->
			<li class="nav-item"><a class="nav-link" href="/">
					<i class="fas fa-home"></i> <span>홈페이지</span>
			</a></li>

			<!-- Nav Item - 로그아웃 -->
			<li class="nav-item" style="margin-top: -1em;">
				<div class="nav-link" data-toggle="modal" data-target="#logoutModal">
					<i class="fas fa-power-off"></i> <span>로그아웃</span>
				</div>
			</li>

			<!-- Divider -->
			<hr class="sidebar-divider">

			<!-- Heading -->
			<div class="sidebar-heading">관리하기</div>

			<!-- Nav Item - 자소서 피드백 관리 -->
			<li class="nav-item"><a class="nav-link" href="/mng/fed"> <i
					class="far fa-eye"></i> <span>자소서 피드백</span></a></li>

			<!-- Nav Item - 고객센터 답변 관리 -->
			<li class="nav-item"><a class="nav-link"
				href="/mng/qna/board.do"> <i class="fas fa-comment-alt"></i> <span>고객센터
						답변</span></a></li>

			<!-- Nav Item - 회원 관리 -->
			<li class="nav-item active"><a class="nav-link"
				href="/mng/member"> <i class="fas fa-fw fa-table"></i> <span>회원</span>
			</a></li>

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
                    <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                        <i class="fa fa-bars"></i>
                    </button>

                </nav>
				<!-- End of Topbar -->

				<!-- Begin Page Content -->
				<div class="container-fluid mt-4">


					<!-- Page Heading -->
					<div
						class="d-s-flex align-items-center justify-content-between mb-2">
						<h1 class="h3 mb-0 text-gray-800">
							<!-- 채워야 될 부분 -->
							회원 관리
						</h1>
					</div>
					<div class="mb-4">
						<!-- 채워야 될 부분 -->
						삭제 체크박스를 누른 다음 항목 삭제 버튼을 누르면 피드백 댓글이 삭제됩니다.
					</div>

					<!-- DataTales Example -->
					<form id='frmList'>
						<div class="card shadow mb-4">
							<div class="card-body">
								<div class="table-responsive">
									<table class="table table-bordered table-layout-fixed" id="dataTable" width="100%"
										cellspacing="0">
										<colgroup>
								    		<col width="10%"/>
								    		<col width="15%"/>
								    		<col/>
								    		<col width="15%"/>
								    		<col width="15%"/>
								    		<col width="15%"/>
								    		<col width="8%"/>
								    	</colgroup>
										<thead>
											<tr> 
												<th>이름</th>
												<th>아이디</th>
												<th>이메일</th>
												<th>전화번호</th>
												<th>생년월일</th>
												<th>가입일</th>
												<th>삭제</th>
											</tr>
										</thead>

									</table>							 		
									
								</div>
							</div>
						</div>
					</form>
				</div>
				<!-- /.container-fluid -->

			</div>
			<!-- End of Main Content -->

		</div>
		<!-- End of Content Wrapper -->

	</div>
	<!-- End of Page Wrapper -->

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>

	<!-- Logout Modal-->
	<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog"
		aria-labelledby="modalLabelHeader" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="modalLabelHeader">로그아웃을 하시겠습니까?</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">확인을 누르면 관리자 계정에서 로그아웃한 뒤 홈페이지로 돌아가게
					됩니다.</div>
				<div class="modal-footer">
					<button class="btn btn-secondary" type="button"
						data-dismiss="modal">취소</button>
					<a class="btn btn-primary" href="/logout">확인</a>
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
	<!-- <script src="/js/datatables.js"></script> -->
	<script src="/js/sidebarMng.js"></script>
	<script src="/js/mngMember.js"></script>
</body>
</html>