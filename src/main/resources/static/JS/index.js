$(function(){
	// recruitBoard
	ajaxRecruitBoard();
	
	// newsBoard
	ajaxNewsBoard();

	// fedBoard
	ajaxFedBoard();
	
	// qnaBoard
	ajaxQnaBoard();
});

// recruitBoard 에 표시할 데이터
function ajaxRecruitBoard() {
	$.ajax({
		url: "/indexAjax/",
		type: "POST",
		cache: false,
		success: function(data, status) {
			if (status == 'success') {
				// parseJson
				var info = data.dhsOpenEmpInfo;
				var info_len = info.length;
				var row = ""
				var cnt = 0; // 반복문 종료횟수 지정
				
				for (var i = 0; i < info_len; i++) {
					var dDay = to_dDay(info[i].empWantedEndt);
					
					if (dDay == "D-Day") { // 당일 마감되는 채용 공고만 view 에 출력
						row += "<tr>";
						// 기업명
						row += "<td>" + info[i].empBusiNm + "</td>";
						// 고용형태
						row += "<td>" + info[i].empWantedTypeNm + "</td>";
						// 채용제목
						row += "<td>" + info[i].empWantedTitle + "</td>";
						row += "</tr>";
						cnt++;
					}
					
					if(cnt == 6) { // 상위 6개만 view 에 출력
						break;
					}
				} // for문 종료
				
				$('#recruitBoard').html(row);
			} // parseJson 끝
		}
	}); // ajax 끝
}
function to_dDay(date_str) { // date.getTime() 으로 현재 날짜를 미리초로 반환하여 d-day 구함
	// 오늘 날짜의 getTime()
	var now = new Date().getTime();
	// 채용마감일의 getTime()
	var empWantedEndt = to_day(date_str).getTime();
	// 1일 밀리초
	var per_day = 1000 * 60 * 60 * 24;
	// D-day
	var d_day = Math.floor(now/per_day) - Math.floor(empWantedEndt/per_day) - 1;
	
	// 디데이가 0이면 D-Day 반환
	if (d_day == 0) {
		return "D-Day";
	}
	
	// 그게 아니면 기호(- 또는 +)와 함께 반환
	return d_day;
}
function to_day(date_str) { // string -> date
	var yyyyMMdd = String(date_str);
    var sYear = yyyyMMdd.substring(0,4);
    var sMonth = yyyyMMdd.substring(4,6);
    var sDate = yyyyMMdd.substring(6,8);

    return new Date(sYear, sMonth-1, sDate);
}

// newsBoard 에 표시할 데이터
function ajaxNewsBoard() {
	/*$.ajax({
		url: "https://openapi.naver.com/v1/search/news.xml?query=기업&display=10&start=1&sort=sim/",
		beforeSend : function(xhr){
            xhr.setRequestHeader("X-Naver-Client-Id", "2e512yp04pPGicjh6nRs");
            xhr.setRequestHeader("X-Naver-Client-Secret","XFrkzznL5S");
        },
		type: "GET",
		cache: false,
		success: function(data, status) {
			if (status == 'success') {
				// parseJson
				alert(data);
			} else {
				alert(234)
			}
		}
	}); // ajax 끝*/
}

// fedBoard 에 표시할 데이터
function ajaxFedBoard() {
	$.ajax({
		url: "/AjaxFedBoard/1/6",
		type: "GET",
		cache: false,
		success: function(data, status) {
			var item = data.data; // ajax 통신을 하면 data로 Json 객체를 받아옴, 그 안에 data라는 배열을 사용자 정의
			
			if (status == 'success') {
				// parseJson
				var row = "";
				var len = 6;
				
				for (var i = 0; i < len; i++) {
					row += "<tr>";
					// 제목
					row += "<td>" + item[i].intro.title  + "</td>";
					// 내용
					row += "<td>" + item[i].intro.title + "/" + item[i].intro.title + "</td>";
					row += "</tr>";
				}
				
				$('#fedBoard').html(row);
			} // parseJson 끝
		}
	}); // ajax 끝
}

// qnaBoard 에 표시할 데이터
function ajaxQnaBoard() {
	$.ajax({
		url: "/AjaxQnaBoard/1/6",
		type: "GET",
		cache: false,
		success: function(data, status) {
			var item = data.data; // ajax 통신을 하면 data로 Json 객체를 받아옴, 그 안에 data라는 배열을 사용자 정의
			
			if (status == 'success') {
				// parseJson
				var row = "";
				var len = 6;
				
				for (var i = 0; i < len; i++) {
					row += "<tr>";
					// 제목
					row += "<td>" + item[i].qdto.subject + "</td>";
					// 등록일
					row += "<td>" + item[i].qdto.regdate + "</td>";
					row += "</tr>";
				}
				
				$('#qnaBoard').html(row);
			} // parseJson 끝
		}
	}); // ajax 끝
}
