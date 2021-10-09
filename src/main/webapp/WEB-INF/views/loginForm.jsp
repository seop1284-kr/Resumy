<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>Resumy - 로그인</title>
	<!-- 파비콘 -->
	<link href="/img/logo_sm.png" rel="shortcut icon" type="image/x-icon">
	<!-- FontAwesome -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
	<!-- Bootstrap CSS -->
	<link href="/assets/dist/css/bootstrap.min.css" rel="stylesheet">
	<!-- 카카오 로그인 -->
	<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
	<script src="/JS/login.js"></script>
	<style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
    </style>
    <script>
    window.Kakao.init("8a72cbf0b5226747f67595121318891d");

    function kakaoLogin() {
        window.Kakao.Auth.login({
            scope: 'profile_nickname, account_email, gender, birthday',
            success: function (autObj) {
                console.log(autObj);
                window.Kakao.API.request({
                    url: '/v2/user/me',
                    success: res => {
                        const kakao_account = res.kakao_account;
                        console.log(kakao_account)
                    }
                });

            }
        });

    }
    </script>
	<!-- My CSS -->
	<link href="/css/login.css" rel="stylesheet">
</head>
<%-- loginProcessingUrl() 로 세팅한 url, 반드시 POST! --%>
<%-- 일단 아이디/패스워드 의 name 은 'username' 과 'password' 로 하자 (시큐리티의 디폴트) --%>
<%-- 만약 다른 name 을 사용하려면 SecurityConfig 에서 .usernameParameter("userid") 해주면 된다 --%>
<body class="text-center">
	<!-- 카카오 로그인 -->
	
    <form class="form-login" action="/loginOk" method="POST">
    
      <!-- logo -->
	  <a href="/"><img class="mb-4" src="/img/logo_shadow.png" alt="Resumy 로고" width="300"></a>
	  
	  <!-- id/pw -->
	  <label for="inputID" class="sr-only">Id</label>
	  <input type="text" name="userid" id="inputID" class="form-control" placeholder="아이디"  autofocus>
	  <label for="inputPassword" class="sr-only">Password</label>
	  <input type="password" name="password" id="inputPassword" class="form-control" placeholder="비밀번호" >
	  <div class="checkbox mb-2 d-flex justify-content-between">
	    <label>
	      <input type="checkbox" value="remember-me"> 로그인 상태 유지
	    </label>
	    <label>
	    	<span><a href="#">ID/PW 찾기</a></span>
	    </label>
	  </div>
	  
	  <!-- login button -->
	  <button class="btn btn-lg btn-primary btn-block border-0 mb-4" type="submit">로그인</button>
	  
	  <button class="btn btn-lg btn-primary btn-block border-0 btn-kk d-flex justify-content-center align-item-center" type="button" href="javascript:kakaoLogin();">
	  	<i class="fas fa-comments" ></i>
	  	<span>카카오톡 로그인</span>
	  </button>
	  
	  <button class="btn btn-lg btn-primary btn-block border-0 btn-fb d-flex justify-content-center align-item-center" type="button" href="javascript:kakaoLogin();">
	  	<i class="fab fa-facebook-square"></i>
	  	<span>Facebook 로그인</span>
	  </button>
	  
	  <button class="btn btn-lg btn-primary btn-block border-0 btn-nv d-flex justify-content-center align-item-center" type="button">
	  	<i class="fas">N</i>
	  	<span>네이버 로그인</span>
	  </button>
	  
	  <!-- signin -->
	  <div class="mt-3" style="font-size: 14px">
	  	  아직 회원이 아니시면 눌러주세요
		  <a href="/join">회원가입</a>
	  </div>
	  
	</form>
	
</body>
</html>