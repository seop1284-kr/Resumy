$(function(){
	// recruitBoard
	ajaxRecruitBoard();
	
	// newsBoard
	getKeyword();

	// fedBoard
	ajaxFedBoard();
	
	// qnaBoard
	ajaxQnaBoard();
	
	// 취업뉴스 버튼 누를 때 active
	$('#btn_news_it').click(function() {
		$('#btn_news_it').attr('class', 'btn btn-light active rounded-0 col');
		$('#btn_news_economy').attr('class', 'btn btn-light rounded-0 col');
		$('#btn_news_job').attr('class', 'btn btn-light rounded-0 col');
	});
	$('#btn_news_economy').click(function() {
		$('#btn_news_it').attr('class', 'btn btn-light rounded-0 col');
		$('#btn_news_economy').attr('class', 'btn btn-light active rounded-0 col');
		$('#btn_news_job').attr('class', 'btn btn-light rounded-0 col');
	});
	$('#btn_news_job').click(function() {
		$('#btn_news_it').attr('class', 'btn btn-light rounded-0 col');
		$('#btn_news_economy').attr('class', 'btn btn-light rounded-0 col');
		$('#btn_news_job').attr('class', 'btn btn-light active rounded-0 col');
	});
	
	// 취업뉴스 검색창 연결
	$('#btn_search').click(function() {
		$('#btn_news_it').toggle();
		$('#btn_news_economy').toggle();
		$('#btn_news_job').toggle();
		$('#search_wrap').toggle();
	});
	
	// 취업뉴스 엔터 입력시 검색
	$('#btn_news_input').keyup(function(key) {
		if (key.keyCode == 13) {
			$('#btn_news_search').click();
		}
	});
});

// ------------------------------------------------------------------
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
				var chkDday = false; // false : 오늘마감이 없음 true : 오늘마감이 있음
				
				for (var i = 0; i < info_len; i++) {
					var dDay = to_dDay(info[i].empWantedEndt);
					
					if (dDay == "D-Day") { // 당일 마감되는 채용 공고만 view 에 출력
						row += "<tr onclick='linkRecruitBoard(" + '"' + info[i].empWantedHomepgDetail + '"' + ")'>";
						// 기업명
						row += "<td><div class='ellipsis'>" + info[i].empBusiNm + "</div></td>";
						// 채용제목
						row += "<td><div class='ellipsis'>" + info[i].empWantedTitle + "</div></td>";
						row += "</tr>";
						cnt++;
						chkDday = true;
					}
					
					if (cnt == 6) { // 상위 6개만 view 에 출력
						break;
					}
				} // for문 종료
				
				// 오늘 마감이 없을 경우 없음 출력
				if (!chkDday) {
						row += "<tr rowspan='6'><td colspan='2' class='text-center'>없음</td></tr>";
					}
				
				$('#recruitBoard').html(row);
			} // parseJson 끝
		}
	}); // ajax 끝
}

function linkRecruitBoard(link) {
	window.open(link);
	return false;
}

function to_dDay(date_str) { // date.getTime() 으로 현재 날짜를 미리초로 반환하여 d-day 구함
	// 오늘 날짜의 getTime()
	var now = new Date().getTime();
	// 채용마감일의 getTime()
	var empWantedEndt = to_day(date_str).getTime();
	// 1일 밀리초
	var per_day = 1000 * 60 * 60 * 24;
	// D-day
	var d_day = Math.floor((now - empWantedEndt)/per_day) * -1;
	
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

// ------------------------------------------------------------------
// 검색할 키워드 가져오기
function getKeyword() {
	// 처음 load될 때 실행
	var keyword = $('#btn_news_it').data("keyword");;
	ajaxNewsBoard(keyword);
	
	// IT 이슈
	$('#btn_news_it').click(function() {
		keyword = $('#btn_news_it').data("keyword");
		ajaxNewsBoard(keyword);
	});
	// 경제 이슈
	$('#btn_news_economy').click(function() {
		keyword = $('#btn_news_economy').data("keyword");
		ajaxNewsBoard(keyword);
	});
	// 취업 소식
	$('#btn_news_job').click(function() {
		keyword = $('#btn_news_job').data("keyword");
		ajaxNewsBoard(keyword);
	});
	// 검색어 입력
	$('#btn_news_search').click(function() {
		keyword = $('#btn_news_input').val();
		ajaxNewsBoard(keyword + " 취업");
	});
}

// newsBoard 에 표시할 데이터
function ajaxNewsBoard(keyword) {
	$.ajax({
		url: "/indexAjax/news",
		type: "POST",
		cache: false,
		data: {"keyword": keyword},
		success: function(data, status) {
			if (status == 'success') {
				var item = data.channel.item;
				var row = "";
				var len = 6;
				
				// parseJson
				for (var i = 0; i < len; i++) {
					row += "<tr>";
					// 제목
					row += "<td><a href='" + item[i].link + "' target=_blank><div class='ellipsis'>" + item[i].title  + "</div></a></td>";
					// 날짜
					row += "<td><div class='ellipsis pl-2 text-right'>" + item[i].pubDate + "</div></td>";
					row += "</tr>";
				}
				$('#newsBoard').html(row);

			}
		}
	}); // ajax 끝
}

// ------------------------------------------------------------------
// fedBoard 에 표시할 데이터
function ajaxFedBoard() {
	$.ajax({
		url: "/AjaxFedBoard/1/6",
		type: "GET",
		cache: false,
		success: function(data, status) {
			
			if (status == 'success') {
				// parseJson
				var item = data.data; // ajax 통신을 하면 data로 Json 객체를 받아옴, 그 안에 data라는 배열을 사용자 정의
				var row = "";
				var len = item.length;
				
				if (len < 6) {
					// 데이터가 6개 미만 일 때 공백 tr 출력
					for (var i = 0; i < len; i++) {
						row += "<tr onclick='linkFedBoard(" + item[i].intro.id + ")'>";
						// 제목
						row += "<td><div class='ellipsis'>" + item[i].intro.title  + "</div></td>";
						// 내용
						row += "<td><div class='ellipsis'>" + item[i].conList[0].question + "</div></td>";
						row += "</tr>";
					}
					
					for (var i = 0; i < 6 - len; i++) {
						row += "<tr><td colspan='2' style='cusor: default;'>&nbsp;</td></tr>";
					}
				} else {
					for (var i = 0; i < 6; i++) {
						row += "<tr onclick='linkFedBoard(" + item[i].intro.id + ")'>";
						// 제목
						row += "<td><div class='ellipsis'>" + item[i].intro.title  + "</div></td>";
						// 내용
						row += "<td><div class='ellipsis'>" + item[i].conList[0].question + "</div></td>";
						row += "</tr>";
					}
				}
				
				
				
				$('#fedBoard').html(row);			
			} // parseJson 끝
		}
	}); // ajax 끝
}

function linkFedBoard(id) {
	location.href = "/fedView?id=" + id;
}


// ------------------------------------------------------------------
// qnaBoard 에 표시할 데이터
function ajaxQnaBoard() {		
	$.ajax({
		url: "/AjaxQnaBoard/1/7",
		type: "GET",
		cache: false,
		success: function(data, status) {
			// parseJson
			var item = data.data; // ajax 통신을 하면 data로 Json 객체를 받아옴, 그 안에 data라는 배열을 사용자 정의
			var row = "";
			var len = item.length;
			
			if (status == 'success') {
				// 데이터가 7개 미만 일 때 공백 tr 출력
				if (len < 7) {
					for (var i = 0; i < len; i++) {
						row += "<tr onclick='linkQnaBoard(" + item[i].qdto.id + ")'>";
						// 제목
						row += "<td><div class='ellipsis'>" + item[i].qdto.subject + "</div></td>";
						// 등록일
						row += "<td><div class='ellipsis text-right'>" + item[i].qdto.regdate + "</div></td>";
						row += "</tr>";
					}
					
					for (var i = 0; i < 7 - len; i++) {
						row += "<tr><td colspan='2' style='cusor: default;'>&nbsp;</td></tr>";
					}
				} else {
					for (var i = 0; i < 7; i++) {
						row += "<tr onclick='linkQnaBoard(" + item[i].qdto.id + ")'>";
						// 제목
						row += "<td><div class='ellipsis'>" + item[i].qdto.subject + "</div></td>";
						// 등록일
						row += "<td><div class='ellipsis text-right'>" + item[i].qdto.regdate + "</div></td>";
						row += "</tr>";
					}			
				}
								
				$('#qnaBoard').html(row);
			} // parseJson 끝
		}
	}); // ajax 끝
}

function linkQnaBoard(id) {
	location.href = "/main/qna/view.do?id=" + id;
}