var chkId = false;

$(function(){
	
	// id alert
	$('#chkIdErrorExist').hide(); // 존재 아이디 오류
	$('#chkIdErrorNull').hide();  // 아이디 입력없이 중복확인 누른 오류
	$('#chkIdSuccess').hide();    // 아이디 중복확인 성공
	
	// pw alert
	$('#chkPwErrorRE').hide();	  // 비밀번호 양식 오류
	$('#chkPwErrorNull').hide();  // 비밀번호 입력없이 재확인 입력한 오류
	$('#chkPwSuccess').hide();    // 비밀번호 확인 성공
	
	// email alert
	$('#chkEmailSuccessForm').hide();    // 이메일 인증번호 입력칸
	$('#chkEmailErrorNull').hide();	     // 이메일 주소 채우지 않고 확인버튼 누른 오류
	$('#chkEmailErrorInputNull').hide(); // 이메일 인증 미완료(입력X)
	$('#chkEmailFail').hide();           // 이메일 인증 실패
	$('#chkEmailSuccess').hide();        // 이메일 인증 성공
	
	checkPassword(); // 비밀번호 확인
	
	// select option 년도 채우기
	insertYear();
	
	// select option 월 채우기
	insertMonth();
	
	// select option 일 채우기
	insertDate();
	
	//
	$("#userid").keyup(function() {
		chkId = false;
		$('#chkIdErrorExist').hide();
		$('#chkIdErrorNull').hide();
		$('#chkIdSuccess').hide();
	});
	
});

// 아이디 중복확인
function checkId() {
	
	var userid = $('#userid').val();

	if (userid == "") {
		// 아이디를 입력하지 않고 중복확인 버튼 누름
		$('#chkIdErrorExist').hide();
		$('#chkIdErrorNull').show();
		$('#chkIdSuccess').hide();
		return;
	} 
	
	$.ajax({
		url: "/checkUserAjax/checkId/" + userid,  // url : /board
		type: "GET",
		cache: false,
		async: false,
		success: function(data, status) {
			if (status == "success") {
				if (!data) {
					// 동일한 아이디 존재X
					$('#chkIdErrorExist').show();
					$('#chkIdErrorNull').hide();
					$('#chkIdSuccess').hide();
					chkId = true;
				} else {
					// 동일한 아이디 존재
					$('#chkIdErrorExist').hide();
					$('#chkIdErrorNull').hide();
					$('#chkIdSuccess').show();
				}
				
			}
		}
	});
}


// 비밀번호 확인
function checkPassword() {
	$('#chkPassword').keyup(function() {
		$('#password').blur();
		
		var password = $('#password').val();
		var chkPassword = $('#chkPassword').val();
		
		if(password == "" && chkPassword != "") { // 비밀번호 입력 안 하고 비밀번호 재입력
			$('#chkPwErrorRE').hide();
			$('#chkPwErrorNull').show();
			$('#chkPwSuccess').hide();
			$('#password').focus();
		} else if(password != chkPassword) { // 비밀번호와 비밀번호 재입력이 안 맞음
			$('#chkPwErrorRE').show();
			$('#chkPwErrorNull').hide();
			$('#chkPwSuccess').hide();
		} else if(password == chkPassword && password != "") { // 비밀번호와 비밀번호 재입력이 맞음
			$('#chkPwErrorRE').hide();
			$('#chkPwErrorNull').hide();
			$('#chkPwSuccess').show();
		}
	});
	
	$('#password').keyup(function() {
		var password = $('#password').val();
		var chkPassword = $('#chkPassword').val();
		
		if(password != "" && chkPassword == "") {
			$('#chkPwErrorRE').hide();
			$('#chkPwErrorNull').hide();
			$('#chkPwSuccess').hide();
		}
	});
}

// select option 년도 채우기
function insertYear() {
	var today = new Date();   
	var year = today.getFullYear(); // 년도
	
	year = year - 12; // 만 12세 이상부터
	for(i = 0; year > 1900; i++) {
		year = year - 1;
		$('#year').append('<option value="' + year + '">' + year + '</option>');
	}
}

// select option 월 채우기
function insertMonth() {
	for(i = 1; i <= 12; i++) {
		$('#month').append('<option value="' + i + '">' + i + '</option>');
	}
}

// select option 일 채우기
function insertDate() {
	$('#date').click(function() {
		var month = $('#month').val();
		if(month == -1) {
			alert('태어난 월을 먼저 선택해주세요.');
		} else {
			for(i = 1; i <= 31; i++) { // <<<<<<<<<<< 뭘 해도 31만 나옴...
				switch(month) {
					case 2:
						if (i == 30) break;
						$('#date').append('<option value="' + i + '">' + i + '</option>');
						break;
					case 4:
					case 6:
					case 9:
					case 11:
						if (i == 31) break;
						$('#date').append('<option value="' + i + '">' + i + '</option>');
						break;
					default:
						$('#date').append('<option value="' + i + '">' + i + '</option>');
						break;
				}
			}
		}
	});
}

// 이메일 인증
function checkEmail() {
	var prefixEmail = $('#prefixEmail').val();
	var suffixEmail = $('#suffixEmail option:selected').val();
	
	$('#chkEmailErrorInputNull').hide();
	$('#chkEmailFail').hide();
	$('#chkEmailSuccess').hide();
		
	if(prefixEmail == "" || suffixEmail == "") { // 이메일을 입력하지 않고 인증하기 버튼 누름
		$('#chkEmailErrorNull').show();
	}
	if(prefixEmail != "" && suffixEmail != -1) { // 정상수행 후 인증하기 버튼 누름
		$('#chkEmailErrorNull').hide();
		$('#chkEmailSuccessForm').show(); // 인증번호 입력폼 등장
	}
}

// 이메일 인증확인
function checkEmailSuccess() {
	var chkEmailSuccessInput = $('#chkEmailSuccessInput').val();
	var sendEmailText = 'same'; // <<<<<<<<<<<<<<<<<<<<<<<<< 이메일 인증번호 가져오는 로직 추가해야 함
	
	$('#chkEmailErrorNull').hide();
	
	if(chkEmailSuccessInput == "") { // 인증번호 기입하지 않고 확인 버튼 누름
		$('#chkEmailErrorInputNull').show();
		$('#chkEmailFail').hide();
		$('#chkEmailSuccess').hide();
	} else if(chkEmailSuccessInput != sendEmailText) { // 인증번호가 다름
		$('#chkEmailErrorInputNull').hide();
		$('#chkEmailFail').show();
		$('#chkEmailSuccess').hide();
	} else if(chkEmailSuccessInput == sendEmailText) { // 인증성공
		$('#chkEmailErrorInputNull').hide();
		$('#chkEmailFail').hide();
		$('#chkEmailSuccess').show();
	}
}