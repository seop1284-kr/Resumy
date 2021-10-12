/*
공채 속보 API

★기업로고 <regLogImgNm>
★채용제목 <empWantedTitle>
★채용업체명 <empBusiNm>
★기업구분 <coClcdNm>
★고용형태 <empWantedTypeNm>
지원자격(경력) <empWantedCareerNm>
지원자격(학력) <empWantedEduNm>
근무지 <workRegionNm>
채용시작일 <empWantedStdt>
★채용마감일 <empWantedEndt> <- 이걸로 공고 종료일 d-day 구하기

★채용기업 홈페이지 <empWantedHomepg>
★채용사이트URL <empWantedHomepgDetail> - 현재 로고에 적용되어 있는 url


url: 
http://openapi.work.go.kr/opi/opi/opia/dhsOpenEmpInfoAPI.do?authKey=WNKTM9J2OCLG1EI81VOVZ2VR1HJ&callTp=L&returnType=XML&&startPage=1&display=100
*/

document.cookie = "safeCookie1=foo; SameSite=Lax";
document.cookie = "safeCookie2=foo";
document.cookie = "crossCookie=bar; SameSite=None; Secure";

$(document).ready(function() {
	$.ajax({
		url: "/indexAjax/",
		type: "POST",
		cache: false,
		success: function(data, status) {
			if (status == 'success') {
				parseJson(data);

			}
		}
	});
	
	
	$(window).scroll(function() {
        if ($(this).scrollTop() > 500) {
            $('#MOVE_TOP_BTN').fadeIn();
        } else {
            $('#MOVE_TOP_BTN').fadeOut();
        }
    });
    
    $("#MOVE_TOP_BTN").click(function() {
        $('html, body').animate({
            scrollTop : 0
        }, 400);
        return false;
    });
	
});

function parseJson(data) {

	var info = data.dhsOpenEmpInfo;
	var list = ""
	var now = new Date();

	for (var i = 0; i < info.length; i++) {
		list += "<a href='" + info[i].empWantedHomepgDetail + "'><li class='infoBox'>\n";
		list += "<div class='logo'><img src ='" + info[i].regLogImgNm + "' alt='" + info[i].empWantedTitle + "'></div>\n";
		list += "<span class='busiNm ellipsis'>" + info[i].empBusiNm + "</span><span class='clcdNm'>"+ info[i].coClcdNm+"</span>\n";
		list += "<span class='title ellipsis'>" + info[i].empWantedTitle + "</span>\n";
		list += "<span class='type ellipsis'>" + info[i].empWantedTypeNm.replaceAll("|", " / ") + "</span>\n";

		// yyyyMMdd 형식의 문자열을 Date 형식으로 바꾸는 함수
		function parse(str) {
			var y = str.substr(0, 4);
			var m = str.substr(4, 2);
			var d = str.substr(6, 2);
			return new Date(y, m - 1, d);
		}
		var date = parse(info[i].empWantedEndt);
		var now = new Date();
		var dday = now.getTime() - date.getTime();
		var result = (Math.floor(dday / (1000 * 60 * 60 * 24))) * -1;

		if (result < 0) {
			result = "종 료";
			list += "<div class='endt endtEx'>" + result + "</div>\n";
		} else if (result == 0) {
			result = "D-day";
			list += "<div class='endt blinking'>" + result + "</div>\n";
		} else if (0 < result && result < 4) {
			result = "D - " + (Math.floor(dday / (1000 * 60 * 60 * 24))) * -1;
			list += "<div class='endt endtUnder3'>" + result + "</div>\n";
		} else {
			result = "D - " + (Math.floor(dday / (1000 * 60 * 60 * 24))) * -1;
			list += "<div class='endt'>" + result + "</div>\n";
		}
		
		list += "</li></a>";
	}

	$('#infoList').html(list);

}

