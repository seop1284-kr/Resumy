var chkId = false; // 아이디 중복확인 상태 (true: 중복아님, false: 중복)
var chkPw = false; // 비밀번호 입력 확인 상태 (true: 확인O, false: 확인X)
var chkName = false; // 이름 입력 확인 상태 (true: 입력O, false: 입력X)
var chkEmail = false; // 이메일 인증 확인 상태 (true: 인증O, false: 인증X)
// 정규 표현식
var regexId = /^[a-zA-Z][a-zA-Z\d]{7,15}$/;
var regexPw = /^[a-zA-Z][a-zA-Z\d]{7,15}$/;

$(function() {
	
	// 아이디 확인
	checkIdInput();

	// 비밀번호 확인
	checkPassword();
	
	// 이름 입력 확인
	checkName();

	// datepicker로 생년월일 표시하기
	$("#datepicker").datepicker({
		dateFormat: 'yy-mm-dd' //달력 날짜 형태
		,showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
		,showMonthAfterYear: true // 월- 년 순서가아닌 년도 - 월 순서
		,changeYear: true //option값 년 선택 가능
		,changeMonth: true //option값  월 선택 가능                
		,showOn: "focus" //focus:버튼 표시 없고 포커스가 오면 달력 표시 ^ button:버튼을 표시하고,버튼을 눌러야만 달력 표시 ^ both:버튼을 표시하고,버튼을 누르거나 input을 클릭하면 달력 표시  
		,yearSuffix: "년" //달력의 년도 부분 뒤 텍스트
		,monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'] //달력의 월 부분 텍스트
		,monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'] //달력의 월 부분 Tooltip
		,dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'] //달력의 요일 텍스트
		,dayNames: ['일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일'] //달력의 요일 Tooltip
		,yearRange: "-100:+0" //년도 범위를 스크롤바로 나오게 함
	});

	// 초기값 설정
	$('#datepicker').datepicker('setDate', '-20Y'); // (-1D:하루전, -1M:한달전, -1Y:일년전), (오늘, +1D:하루후, -1M:한달후, -1Y:일년후)

	// submit button 상태 변환
	$('#formJoin').keyup(function() {
		submitVisiable();
	});
	$('#formJoin').click(function() {
		submitVisiable();
	});
	
	// submit 될 떄 이메일 pre와 suf를 합쳐서 보냄
	$('#formJoin').submit(function() {
		var prefixEmail = $('#prefixEmail').val();
		var suffixEmail = $('#suffixEmail').val();
		
		$('#prefixEmail').val(prefixEmail + '@' + suffixEmail);
	});
	
}); // document.ready() 끝

// ===============================================================================
// 아이디 확인
function checkIdInput() {
	$("#userid").keyup(function() {
		var userid = $('#userid').val();
    	var result = regexId.exec(userid);
		
		$('#chkIdErrorRE').hide();
		$('#chkIdErrorExist').hide();
		$('#chkIdErrorCheck').hide();
		$('#chkIdSuccess').hide();
		
		// 아이디 양식 검사
		if (result == null) { // 양식 안 맞음
			$('#chkIdErrorRE').show();
			$('#btn_chkId').attr('disabled', true); // 중복확인 버튼 비활성화
		} else { // 양식 맞음
			$('#chkIdErrorCheck').show();
			$('#btn_chkId').attr('disabled', false); // 중복확인 버튼 활성화
	    }
	
		chkId = false;
	});
}

// 아이디 중복확인
function checkId() {
	var userid = $('#userid').val();
	var result = regexId.exec(userid);
	
	$('#chkIdErrorRE').hide();
	$('#chkIdErrorExist').hide();
	$('#chkIdErrorCheck').hide();
	$('#chkIdSuccess').hide();
	
	$.ajax({
		url: "/checkUserAjax/checkId/" + userid,
		type: "GET",
		cache: false,
		async: false,
		success: function(data, status) {
			if (status == "success") {
				if (data) {
					// 동일한 아이디 존재X (true)
					$('#chkIdSuccess').show();
					chkId = true;
				} else {
					// 동일한 아이디 존재 (false)
					$('#chkIdErrorExist').show();
					chkId = false;
				}
			}
		}
	});
}

// 비밀번호 확인
function checkPassword() {
	$("#pwform").keyup(function() {
		var password = $('#password').val();
		var chkPassword = $('#chkPassword').val();
		var result = regexPw.exec(password);
		
		$('#chkPwErrorRE').hide();
		$('#chkPwErrorNull').hide();
		$('#chkPwSuccess').hide();
		$('#chkPwErrorFail').hide();
		
		// 비밀번호 입력 안 하고 비밀번호 재입력
		if (password.length == 0 && chkPassword.legnth != 0) {
			$('#chkPwErrorNull').show();
			$('#chkPassword').val(''); // 비밀번호 재입력 입력 삭제
			$('#password').focus();    // 비밀번호 입력에 포커스
			chkPw = false;
			return;
		}
		
		// 비밀번호 양식 틀림
		if (result == null) {
			$('#chkPwErrorRE').show();
			chkPw = false;
			return;
		}

		// 비밀번호와 비밀번호 재입력이 안 맞음
		if (password != chkPassword) {
			$('#chkPwErrorFail').show();
			chkPw = false;
			return;
		}

		// 통과
		$('#chkPwSuccess').show();
		chkPw = true;

	});
}

// 이름 입력 확인
function checkName(){
	$("#name").keyup(function() {
		var name = $("#name").val();
		
		if (name.length > 0) {
			chkName = true;
		} else {
			chkName = false;
		}
	});
}

// 이메일 인증
function checkEmail() {
	var prefixEmail = $('#prefixEmail').val();
	var suffixEmail = $('#suffixEmail option:selected').val();
	var btn_name = $('#btn_chkEmail').html();

	$('#chkEmailNumErrorNull').hide();
	$('#chkEmailNumFail').hide();
	$('#chkEmailNumSuccess').hide();
	
	switch(btn_name) {
		case '인증하기':
			// 이메일을 입력하지 않고 인증하기 버튼 누름
			if (prefixEmail == "" || suffixEmail == "") {
				$('#chkEmailErrorNull').show();
			}
			// 정상수행 후 인증하기 버튼 누름
			if (prefixEmail != "" && suffixEmail != -1) {
				$('#chkEmailErrorNull').hide();
				$('#chkEmailNumBox').show(); // 인증번호 입력폼 등장
				// 이메일 입력칸 막음
				$('#prefixEmail').attr('readonly', true);
				$('#suffixEmail').attr('disabled', true);
				$('#btn_chkEmail').html('주소변경'); // 인증하기 버튼을 주소변경 버튼으로 바꿈
			}
			break;
		case '주소변경':
			// 주소변경 버튼을 눌렀을 때, 이메일 입력 활성화
			$('#btn_chkEmail').html('인증하기');
			$('#prefixEmail').attr('readonly', false);
			$('#suffixEmail').attr('disabled', false);
			$('#chkEmailNumBox').hide(); // 인증번호 입력폼 숨기기
			$('#chkEmailNum').val(''); // 입력된 인증번호 삭제
	}
	
	chkEmail = false;
}

// 이메일 인증확인
function checkEmailNum() {
	var chkEmailNum = $('#chkEmailNum').val();
	var sendEmailText = 'same'; // <<<<<<<<<<<<<<<<<<<<<<<<< 이메일 인증번호 가져오는 로직 추가해야 함

	$('#chkEmailErrorNull').hide();
	$('#chkEmailNumErrorNull').hide();
	$('#chkEmailNumFail').hide();
	$('#chkEmailNumSuccess').hide();
	
	// 인증성공
	if (chkEmailNum == sendEmailText) {
		$('#chkEmailNumSuccess').show();
		chkEmail = true;
		return;
	}
	
	// 인증실패
	if (chkEmailNum == "") { // 인증번호 기입하지 않고 확인 버튼 누름
		$('#chkEmailNumErrorNull').show();
	} else if (chkEmailNum != sendEmailText) { // 인증번호가 다름
		$('#chkEmailNumFail').show();
	}
	
	chkEmail = false;
	
}

// submit button 상태 변환
function submitVisiable() {
	// (아이디 중복X) && (비밀번호 입력 확인O) && (이름 입력 확인O) && (이메일 인증O)
	if (chkId && chkPw && chkName && chkEmail) {
		$('#btn_submit').attr('disabled', false);
	} else {
		$('#btn_submit').attr('disabled', true);
	}
}
