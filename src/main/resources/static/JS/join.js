var chkId = false; // 아이디 중복확인 상태 (true: 중복아님, false: 중복)
var chkPw = false; // 비밀번호 입력 확인 상태 (true: 확인O, false: 확인X)
var chkName = false; // 이름 입력 확인 상태 (true: 입력O, false: 입력X)
var chkEmail = false; // 이메일 인증 확인 상태 (true: 인증O, false: 인증X)

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
	$('#chkEmailNumBox').hide();         // 이메일 인증번호 입력칸
	$('#chkEmailErrorNull').hide();	     // 이메일 주소 채우지 않고 확인버튼 누른 오류
	$('#chkEmailNumErrorNull').hide();   // 이메일 인증 미완료(입력X)
	$('#chkEmailNumFail').hide();        // 이메일 인증 실패
	$('#chkEmailNumSuccess').hide();     // 이메일 인증 성공
	
	// 비밀번호 확인
	checkPassword();

	// 이름 입력 확인
	$("#name").keyup(function() {
		chkName = true;
	});
	
	// datepicker로 생년월일 표시하기
	$("#datepicker").datepicker({
       dateFormat: 'yy-mm-dd' //달력 날짜 형태
       ,showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
       ,showMonthAfterYear:true // 월- 년 순서가아닌 년도 - 월 순서
       ,changeYear: true //option값 년 선택 가능
       ,changeMonth: true //option값  월 선택 가능                
       ,showOn: "focus" //focus:버튼 표시 없고 포커스가 오면 달력 표시 ^ button:버튼을 표시하고,버튼을 눌러야만 달력 표시 ^ both:버튼을 표시하고,버튼을 누르거나 input을 클릭하면 달력 표시  
       ,yearSuffix: "년" //달력의 년도 부분 뒤 텍스트
       ,monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 텍스트
       ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 Tooltip
       ,dayNamesMin: ['일','월','화','수','목','금','토'] //달력의 요일 텍스트
       ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'] //달력의 요일 Tooltip
       ,minDate: "-120Y" //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
       ,maxDate: "-14Y" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)  
	});
	
	// 초기값 설정
	$('#datepicker').datepicker('setDate', 'today'); // (-1D:하루전, -1M:한달전, -1Y:일년전), (오늘, +1D:하루후, -1M:한달후, -1Y:일년후)

	// select option 년도 채우기
	insertYear();
	
	// select option 월 채우기
	insertMonth();
	
	// select option 일 채우기
	insertDate();
	
	// 중복확인 성공 후 중복확인이 안 된 아이디 입력을 막기 위해
	// 글자 입력이 발생할 때마다 중복확인 상태를 false로 전환
	$("#userid").keyup(function() {
		chkId = false;
		$('#chkIdErrorExist').hide();
		$('#chkIdErrorNull').hide();
		$('#chkIdSuccess').hide();
	});
	
	// submit button 상태 변환
	submitVisiable();

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
		url: "/checkUserAjax/checkId/" + userid,
		type: "GET",
		cache: false,
		async: false,
		success: function(data, status) {
			if (status == "success") {
				if (data) {
					// 동일한 아이디 존재X (true)
					$('#chkIdErrorExist').hide();
					$('#chkIdErrorNull').hide();
					$('#chkIdSuccess').show();
					chkId = true;
				} else {
					// 동일한 아이디 존재 (false)
					$('#chkIdErrorExist').show();
					$('#chkIdErrorNull').hide();
					$('#chkIdSuccess').hide();
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
			chkPw = true;
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
	
	$('#chkEmailNumErrorNull').hide();
	$('#chkEmailNumFail').hide();
	$('#chkEmailNumSuccess').hide();
		
	if(prefixEmail == "" || suffixEmail == "") { // 이메일을 입력하지 않고 인증하기 버튼 누름
		$('#chkEmailErrorNull').show();
	}
	if(prefixEmail != "" && suffixEmail != -1) { // 정상수행 후 인증하기 버튼 누름
		$('#chkEmailErrorNull').hide();
		$('#chkEmailNumBox').show(); // 인증번호 입력폼 등장
	}
}

// 이메일 인증확인
function checkEmailNum() {
	var chkEmailNum = $('#chkEmailNum').val();
	var sendEmailText = 'same'; // <<<<<<<<<<<<<<<<<<<<<<<<< 이메일 인증번호 가져오는 로직 추가해야 함
	
	$('#chkEmailErrorNull').hide();
	
	if(chkEmailNum == "") { // 인증번호 기입하지 않고 확인 버튼 누름
		$('#chkEmailNumErrorNull').show();
		$('#chkEmailNumFail').hide();
		$('#chkEmailNumSuccess').hide();
	} else if(chkEmailNum != sendEmailText) { // 인증번호가 다름
		$('#chkEmailNumErrorNull').hide();
		$('#chkEmailNumFail').show();
		$('#chkEmailNumSuccess').hide();
	} else if(chkEmailNum == sendEmailText) { // 인증성공
		$('#chkEmailNumErrorNull').hide();
		$('#chkEmailNumFail').hide();
		$('#chkEmailNumSuccess').show();
		chkEmail = true;
	}
}

// submit button 상태 변환
function submitVisiable() {
	$("#formJoin").keyup(function() {
		// (아이디 중복X) && (비밀번호 입력 확인O) && (이름 입력 확인O) && (이메일 인증O)
		if(chkId && chkPw && chkName && chkEmail) {
			$('#btn_submit').attr('disabled', false);
		}
	});
}